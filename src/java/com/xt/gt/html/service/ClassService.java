
package com.xt.gt.html.service;

import com.xt.core.exception.ServiceException;
import com.xt.core.log.LogWriter;
import com.xt.core.service.IService;
import com.xt.core.service.ServerMethod;
import com.xt.core.utils.ClassHelper;
import com.xt.gt.html.model.ClassDesc;
import com.xt.gt.html.model.ClassPool;
import com.xt.gt.html.model.MethodDesc;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author albert
 */

public class ClassService implements IService {

    public ClassService() {
    }

    /**
     * 装载一个类。
     * @param fullName
     */
    public ClassDesc loadClass (String fullName) {
        if (StringUtils.isEmpty(fullName)) {
            throw new ServiceException("类名称不能为空。");
        }

        Class expectedClass = null;
        try {
            expectedClass = Class.forName(fullName);
            return ClassPool.getInstance().getClassDesc(expectedClass);
        } catch (ClassNotFoundException ex) {
            throw new ServiceException(String.format("类[%s]不存在。", fullName), ex);
        }
    }

    /**
     * 返回类的所有服务方法。这些方法只包括公共的方法。
     * @param className 服务类名称。
     * @return
     */
    public List<MethodDesc> getServiceMethods(String className) {
        if (StringUtils.isEmpty(className)) {
            throw new ServiceException("类名称不能为空。");
        }
        List<MethodDesc> methods = new ArrayList();
        Class serviceClass = ClassHelper.getClass(className);
        Method[] _methods = serviceClass.getMethods();
        if (_methods != null && _methods.length > 0) {
            methods = new ArrayList(_methods.length);
            for (Method method : _methods) {
                // System.out.println("method=" + method.getName());
                if (!Modifier.isPublic(method.getModifiers())) {
                    continue;
                }

                if (null != method.getAnnotation(ServerMethod.class)) {
                    continue;
                }

                MethodDesc methodDesc = new MethodDesc();
                methodDesc.load(method);
                methods.add(methodDesc);
            }
        } else {
             methods = Collections.emptyList();
        }
        return methods;
    }

}
