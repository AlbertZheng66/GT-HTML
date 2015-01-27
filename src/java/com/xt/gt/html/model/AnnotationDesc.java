package com.xt.gt.html.model;

import com.xt.core.exception.ServiceException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

/**
 *
 * @author albert
 */
public class AnnotationDesc implements Serializable {

    private final Logger logger = Logger.getLogger(AnnotationDesc.class);

    /**
     * 不需要读取标注的方法
     */
    private final Set<Method> exculudeMethods = new HashSet();

    private String name;

    private Map<String, Serializable> params = new HashMap();

    public AnnotationDesc() {
        for (Method method : Annotation.class.getMethods()) {
            exculudeMethods.add(method);
        }
        for (Method method : Object.class.getMethods()) {
            exculudeMethods.add(method);
        }
    }

    public void load(Annotation annotation) {
        this.name = annotation.annotationType().getName();
        Method[] methods = annotation.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (exculudeMethods.contains(method) || method.getParameterTypes().length != 0) {
                continue;
            }
            try {
                Object value = method.invoke(annotation, new Object[]{});
                if (value != null && value instanceof Serializable) {
                    params.put(method.getName(), (Serializable) value);
                } else {
                    logger.warn(String.format("标注[%s]的值[%s]为空，或者不可序列化。", name, value));
                }
            } catch (Exception ex) {
                throw new ServiceException(String.format("读取方法[%s]时出错", method.getName()), ex);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Serializable> getParams() {
        return Collections.unmodifiableMap(params);
    }

    public void setParams(Map<String, Serializable> params) {
        if (params != null) {
            this.params.putAll(params);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnnotationDesc other = (AnnotationDesc) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
