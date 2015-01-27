
package com.xt.gt.html.model;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author albert
 */
public class MethodDesc implements Serializable {

    /**
     * 方法名称
     */
    private String name;

    /**
     * 返回的类型(类名称)
     */
//    private ClassDesc returnType;
    private String returnType;

    /**
     * 定义在此方法之上的标注（暂时不支持）
     */
    private List<AnnotationDesc> annotations;

    /**
     * 方法的参数类型(类名称)
     */
//    private List<ClassDesc> parameterTypes;
    private List<String> parameterTypes;

    public MethodDesc() {
    }

    public void load (Method method) {
        this.name = method.getName();

        Class _returnType = method.getReturnType();
        // this.returnType = ClassPool.getInstance().getClassDesc(_returnType);
        this.returnType = _returnType.getName();

        // 装载类的标注
        Annotation[] annos = method.getDeclaredAnnotations();
        if (annos != null && annos.length > 0) {
            annotations = new ArrayList(annos.length);
            for (int i = 0; i < annos.length; i++) {
                Annotation annotation = annos[i];
                AnnotationDesc ad = new AnnotationDesc();
                ad.load(annotation);
                annotations.add(ad);
            }
        } else {
            annotations = Collections.EMPTY_LIST;
        }

        // 装置参数类型
        Class<?>[] types = method.getParameterTypes();
        if (types != null && types.length > 0) {
            parameterTypes = new ArrayList(types.length);
            for (Class<?> type : types) {
//                ClassDesc paramType = ClassPool.getInstance().getClassDesc(type);
//                parameterTypes.add(paramType);
                parameterTypes.add(type.getName());
            }
        } else{
            parameterTypes = Collections.EMPTY_LIST;
        }
        
        
    }

    public List<AnnotationDesc> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationDesc> annotations) {
        this.annotations = annotations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MethodDesc other = (MethodDesc) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

//    public List<ClassDesc> getParameterTypes() {
//        return parameterTypes;
//    }
//
//    public void setParameterTypes(List<ClassDesc> parameterTypes) {
//        this.parameterTypes = parameterTypes;
//    }
//
//    public ClassDesc getReturnType() {
//        return returnType;
//    }
//
//    public void setReturnType(ClassDesc returnType) {
//        this.returnType = returnType;
//    }
}
