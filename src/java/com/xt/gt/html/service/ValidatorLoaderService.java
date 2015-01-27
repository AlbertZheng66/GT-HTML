package com.xt.gt.html.service;

import com.xt.core.db.pm.IPersistence;
import com.xt.core.exception.ServiceException;
import com.xt.core.service.IService;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 用于装载所有的校验的类型。
 * @author albert
 */
public class ValidatorLoaderService  implements IService{

    private static final Logger logger = Logger.getLogger(ValidatorLoaderService.class);

    public ValidatorLoaderService() {
    }

    /**
     * 返回类的所有可校验器。
     * @param clazz 类实例
     * @return 类经常及其校验器。
     */
    public Map getValidators(Class clazz/*, ValidatorConfig validatorConfig*/) {
        if (clazz == null) {
            throw new ServiceException("类不能为空。");
        }
        Map<String, List<Serializable>> validators = new HashMap();

        // 从数据库装载某些校验相关的信息
        if (IPersistence.class.isAssignableFrom(clazz)) {
            POValidatorLoaderService povService = new POValidatorLoaderService();
            Map<String, List<Serializable>> vals = povService.getValidators(clazz);
            if (vals != null) {
                validators.putAll(vals);
            }
        }

        BeanValidatorLoaderService bvlService = new BeanValidatorLoaderService();
        Map<String, List<Serializable>> vals = bvlService.getValidators(clazz);
        if (vals != null) {
            for(Map.Entry<String, List<Serializable>> entry : vals.entrySet()) {
                String name = entry.getKey();
                List<Serializable> _validators = entry.getValue();
                // 合并已有校验
                if (validators.containsKey(name)) {
                    validators.get(name).addAll(_validators);
                } else {
                    validators.put(name, _validators);
                }
            }
            validators.putAll(vals);
        }

        // TODO: 剔除重复或者矛盾的校验定义
        return validators;
    }
}
