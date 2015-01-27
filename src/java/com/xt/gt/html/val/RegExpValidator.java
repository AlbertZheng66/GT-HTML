package com.xt.gt.html.val;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 用于校验的正则表达式的校验器，很多格式校验可转换为正则表达式校验，比如：电话、邮件等等。
 * @author albert
 */
public class RegExpValidator extends AbstractValidator {

    private String pattern;

    public RegExpValidator() {
    }

    @Override
    public void load(Annotation anno) {
        Map<String, Serializable> params = loadCommon(anno);
        setPattern((String) params.get("regex"));
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
