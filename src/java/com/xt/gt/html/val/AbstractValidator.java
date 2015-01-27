package com.xt.gt.html.val;

import com.xt.gt.html.model.AnnotationDesc;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 *
 * @author albert
 */
abstract public class AbstractValidator implements Serializable, IValidator {

    /**
     * 校验的模式：RegExp, class
     */
    protected Schema schema = Schema.REG_EXP;

    /**
     * 模式或者类名称
     */
    protected String pattern;

    /**
     * 错误信息
     */
    protected String message;

    /**
     * 校验所在的分组
     */
    protected String[] groups;

    public AbstractValidator() {
    }

    protected Map<String, Serializable> loadCommon(Annotation anno) {
        AnnotationDesc ad = new AnnotationDesc();
        ad.load(anno);
        Map<String, Serializable> params = ad.getParams();
        this.groups  = (String[]) params.get("groups");
        this.message = (String) params.get("message");
        return params;
    }

    public String[] getGroups() {
        return groups;
    }

    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }


    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    
}
