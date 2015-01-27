package com.xt.gt.html.val;

import com.xt.core.validation.decl.Chinese;
import com.xt.core.validation.decl.Email;
import com.xt.core.validation.decl.HTTP;
import com.xt.core.validation.decl.Mobile;
import com.xt.core.validation.decl.Phone;
import com.xt.core.validation.decl.PositiveInteger;
import com.xt.core.validation.decl.SSN;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Length;
import javax.validation.NotEmpty;
import javax.validation.Pattern;

/**
 *
 * @author albert
 */
public class BeanValidatorFactory {

    private static final BeanValidatorFactory instance = new BeanValidatorFactory();

    // 此属性定义了标注和校验器之间的映射关系
    private Map<Class, Class<? extends IValidator>> constraintValidators = new HashMap();
    // 此属性定义了标注和校验器之间的映射关系
    private Map<Class, Class<? extends IValidator>> typeValidators = new HashMap();

    private BeanValidatorFactory() {
        // 默认的注册器
        constraintValidators.put(Length.class, RangeValidator.class);
        constraintValidators.put(NotEmpty.class, RequiredValidator.class);
        constraintValidators.put(Pattern.class, RegExpValidator.class);
        constraintValidators.put(Email.class, EmailValidator.class);
        constraintValidators.put(HTTP.class, HTTPValidator.class);
        constraintValidators.put(Phone.class, PhoneValidator.class);
        constraintValidators.put(Mobile.class, MobileValidator.class);
        constraintValidators.put(SSN.class, SSNValidator.class);
        constraintValidators.put(Chinese.class, ChineseValidator.class);
        constraintValidators.put(com.xt.core.validation.decl.Integer.class, IntegerValidator.class);
        constraintValidators.put(com.xt.core.validation.decl.Long.class, LongValidator.class);
        constraintValidators.put(PositiveInteger.class, PositiveIntegerValidator.class);
        constraintValidators.put(com.xt.core.validation.decl.Float.class, FloatValidator.class);
        constraintValidators.put(com.xt.core.validation.decl.Double.class, DoubleValidator.class);

        // 类型校验器
        typeValidators.put(int.class,     IntegerValidator.class);
        typeValidators.put(Integer.class, IntegerValidator.class);
        typeValidators.put(long.class,    LongValidator.class);
        typeValidators.put(Long.class,    LongValidator.class);
        typeValidators.put(float.class,   FloatValidator.class);
        typeValidators.put(Float.class,   FloatValidator.class);
        typeValidators.put(double.class,  DoubleValidator.class);
        typeValidators.put(Double.class,  DoubleValidator.class);
    }

    public static BeanValidatorFactory getInstance() {
        return instance;
    }

    synchronized public void registerConstraint(Class annoType, Class<? extends IValidator> validatorClass) {
        if (annoType != null && validatorClass != null) {
            this.constraintValidators.put(annoType, validatorClass);
        }
    }

    synchronized public void registerType(Class type, Class<? extends IValidator> validatorClass) {
        if (type != null && validatorClass != null) {
            this.typeValidators.put(type, validatorClass);
        }
    }


    synchronized public Class<? extends IValidator> get(Class annoType) {
        return this.constraintValidators.get(annoType);
    }
}
