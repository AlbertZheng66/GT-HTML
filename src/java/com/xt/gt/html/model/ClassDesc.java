
package com.xt.gt.html.model;

import com.xt.gt.html.model.impl.DefaultClassFilter;
import com.xt.gt.sys.SystemConfiguration;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author albert
 */
public class ClassDesc implements Serializable{
    
//    private String shortName;
//
//    private String packageName;

    private String className;

    private List<FieldDesc> fields;

    private List<MethodDesc> methods;
    
    private List<AnnotationDesc> annotations;

    /**
     * 类过滤器
     */
    private ClassFilter classFilter = (ClassFilter)SystemConfiguration.getInstance().readObject("classFilter", new DefaultClassFilter());

    public ClassDesc() {
    }

    public void load (Class clazz) {
//        this.shortName = clazz.getSimpleName();
//        this.packageName = clazz.getPackage() == null ? "" : clazz.getPackage().getName();

        this.className = clazz.getName();
        // 需要对装置的服务进行过滤，避免将系统类（如String）全部加载。
        if (!classFilter.isLoad(clazz)) {
            return;
        }
        
        // 装置类定义的域(TODO: 包括父类的方法)
        Field[] _fields = clazz.getDeclaredFields();
        if (_fields != null && _fields.length > 0) {
            fields = new ArrayList(_fields.length);
            for (Field field : _fields) {
                // 临时变量不复制
                if (Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                FieldDesc fieldDesc = new FieldDesc();
                fieldDesc.load(field);
                this.fields.add(fieldDesc);
            }
        } else {
            fields = Collections.EMPTY_LIST;
        }

        // 装置类方法(类的公共方法, 包括父类的方法)
        Method[] _methods = clazz.getMethods();
        if (_methods != null && _methods.length > 0) {
            methods = new ArrayList(_methods.length);
            for (Method method : _methods) {
                MethodDesc methodDesc = new MethodDesc();
                methodDesc.load(method);
                this.methods.add(methodDesc);
            }
        } else {
            methods = Collections.EMPTY_LIST;
        }

        // 装载类的标注
        Annotation[] annos = clazz.getDeclaredAnnotations();
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
    }

    public List<AnnotationDesc> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationDesc> annotations) {
        this.annotations = annotations;
    }

    public List<FieldDesc> getFields() {
        return fields;
    }

    public void setFields(List<FieldDesc> fields) {
        this.fields = fields;
    }

//    public String getPackageName() {
//        return packageName;
//    }
//
//    public void setPackageName(String packageName) {
//        this.packageName = packageName;
//    }
//
//    public String getShortName() {
//        return shortName;
//    }
//
//    public void setShortName(String shortName) {
//        this.shortName = shortName;
//    }

    public List<MethodDesc> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodDesc> methods) {
        this.methods = methods;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClassDesc other = (ClassDesc) obj;
        if ((this.className == null) ? (other.className != null) : !this.className.equals(other.className)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.className != null ? this.className.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    

}
