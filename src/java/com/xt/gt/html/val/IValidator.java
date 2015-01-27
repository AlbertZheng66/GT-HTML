
package com.xt.gt.html.val;

import java.lang.annotation.Annotation;

/**
 * 此接口为声明接口,用于标明此类用于 HTML 校验器之用.
 * @author albert
 */
public interface IValidator {

    public void load(Annotation anno);

}
