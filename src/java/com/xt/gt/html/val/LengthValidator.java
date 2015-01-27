
package com.xt.gt.html.val;

import java.lang.annotation.Annotation;

/**
 * 用于校验字符串类型的长度。
 * @author albert
 */
public class LengthValidator extends AbstractValidator {

    private int length;

    public LengthValidator() {
        this.message = "此输入域的输入长度超长[length]。";
    }




    @Override
    public void load(Annotation anno) {
       
    }

    public LengthValidator(int length) {
        this.length = length;
    }
    
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
