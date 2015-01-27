package com.xt.gt.html.service;

import com.xt.core.exception.ServiceException;
import com.xt.core.log.LogWriter;
import com.xt.core.service.IService;
import com.xt.core.utils.ClassHelper;
import com.xt.core.val.ValidatorException;
import com.xt.gt.html.val.BeanValidatorFactory;
import com.xt.gt.html.val.IValidator;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.ConstraintValidator;
import org.apache.log4j.Logger;

/**
 *
 * @author albert
 */
public class BeanValidatorLoaderService implements IService {

    private static final Logger logger = Logger.getLogger(BeanValidatorLoaderService.class);

    public BeanValidatorLoaderService() {
    }

    /**
     * 返回类的所有可校验器。
     * 本方法使用继承方式递归读取校验标注。
     * TODO: 缓存！！！
     * @param clazz 类实例
     * @return 类经常及其校验器。
     */
    public HashMap<String, List<Serializable>> getValidators(Class clazz/*, ValidatorConfig validatorConfig*/) {
        if (clazz == null) {
            throw new ServiceException("类不能为空。");
        }
        HashMap<String, List<Serializable>>  validators = new HashMap();
        Class parentClass = clazz;
        // 递归查找
        do {
            createValidator(clazz, validators);
            parentClass = parentClass.getSuperclass();
        } while (parentClass != null
                && parentClass != Object.class);
        return validators;
    }

    private void createValidator(Class clazz, HashMap<String, List<Serializable>> validators) throws SecurityException {
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            List validatabls = new ArrayList(1);
            Field field = fields[i];
            // 根据属性类型加载校验信息, 类型校验只在默认的情况下起作用，
            // 即其上未定义任何其他的校验规则
            Class type = field.getType();
            Class<? extends IValidator> valClass = BeanValidatorFactory.getInstance().get(type);
            if (valClass != null && !hasConstraint(field)) {
                IValidator validator = ClassHelper.newInstance(valClass);
                validatabls.add(validator);
            }
            // 从属性的标注上加载校验信息
            Annotation[] annos = field.getAnnotations();
            LogWriter.debug(logger, "loading field =", field.getName());
            for (int j = 0; j < annos.length; j++) {
                Annotation anno = annos[j];
                if (anno.annotationType().getAnnotation(ConstraintValidator.class) != null) {
                    Object _validatable = getValidator(anno);
                    if (_validatable != null) {
                        validatabls.add(_validatable);
                    }
                }
            }
            if (!validatabls.isEmpty()) {
                validators.put(field.getName(), validatabls);
            }
        }
    }

    /**
     * 判断一个域上是否定义了约束相关的校验。
     * @param field
     * @return
     */
    private boolean hasConstraint(Field field) {
        Annotation[] annos = field.getAnnotations();
        for (int j = 0; j < annos.length; j++) {
            Annotation anno = annos[j];
            if (anno.annotationType().getAnnotation(ConstraintValidator.class) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据指定的标注返回相应的校验器。
     * @param anno 标注对象。
     * @return 标注对应的校验器实例。如果标注为空，返回空；如果约束类型的校验器未定义，抛出校验异常。
     */
    private IValidator getValidator(Annotation anno) {
        if (anno == null) {
            return null;
        }
        Class<? extends IValidator> valClass =
                BeanValidatorFactory.getInstance().get(anno.annotationType());
        if (valClass == null) {
            throw new ValidatorException(String.format("约束类型[%s]为定义相应的处理类。",
                    anno.annotationType()));
        }
        IValidator validator = ClassHelper.newInstance(valClass);
        validator.load(anno);
        return validator;
    }
}
