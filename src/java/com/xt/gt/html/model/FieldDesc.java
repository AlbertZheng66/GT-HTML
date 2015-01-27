package com.xt.gt.html.model;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author albert
 */
public class FieldDesc implements Serializable {

    /**
     * 域名称
     */
    private String name;

//    /**
//     * 域的类型
//     */
//    private ClassDesc classDesc;

    /**
     * 域的类型
     */
    private String type;

    /**
     * 定义在此域之上的标注
     */
    private List<AnnotationDesc> annotations;

    public FieldDesc() {
    }
    
    public void load (Field field) {
        this.name = field.getName();

        Class fieldClass = field.getType();
//        this.classDesc = ClassPool.getInstance().getClassDesc(fieldClass);
        type = fieldClass.getName();

        // 装载类的标注
        Annotation[] annos = field.getDeclaredAnnotations();
        if (annos != null && annos.length > 0) {
            annotations = new ArrayList(annos.length);
            for (int i = 0; i < annos.length; i++) {
                Annotation annotation = annos[i];
                AnnotationDesc ad = new AnnotationDesc();
                ad.load(annotation);
                annotations.add(ad);
            }
        }
    }

    public List<AnnotationDesc> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationDesc> annotations) {
        this.annotations = annotations;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public ClassDesc getClassDesc() {
//        return classDesc;
//    }
//
//    public void setClassDesc(ClassDesc classDesc) {
//        this.classDesc = classDesc;
//    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FieldDesc other = (FieldDesc) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
