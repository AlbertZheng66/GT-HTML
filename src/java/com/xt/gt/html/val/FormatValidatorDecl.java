
package com.xt.gt.html.val;

/**
 *
 * @author albert
 * @deprecated 
 */
public @interface FormatValidatorDecl {

    /**
     * 校验时使用的模式。
     * @return
     */
    String pattern();

    /**
     * 错误消息
     * @return
     */
    String message();

    /**
     * 校验所在的分组
     * @return
     */
    String[] groups() default {};

}
