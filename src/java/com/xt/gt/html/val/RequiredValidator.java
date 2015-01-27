package com.xt.gt.html.val;

import java.lang.annotation.Annotation;

/**
 *
 * @author albert
 */
public class RequiredValidator extends AbstractValidator {

    public RequiredValidator() {
        message = "此输入域为必填项。";
    }

    @Override
    public void load(Annotation anno) {
//        if (anno.annotationType() != NotEmpty.class) {
//            throw new ValidateException(String.format("校验类[%s]必须对应标注类型[%s]。",
//                    this.getClass().getName(),
//                    NotEmpty.class));
//        }
//        Length length = (Length) anno;
//        this.message = length.message();
//        this.groups = length.groups();
        loadCommon(anno);
    }
}
