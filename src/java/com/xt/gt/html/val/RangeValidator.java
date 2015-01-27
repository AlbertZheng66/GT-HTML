package com.xt.gt.html.val;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * 用于确定数值类型的合法范围，即校验值必须位于最大和最小值的区间之内。
 * @author albert
 */
public class RangeValidator extends AbstractValidator {

    private int min = 0;
    private int max = Integer.MAX_VALUE;

    public RangeValidator() {
    }

    @Override
    public void load(Annotation anno) {
//        if (anno.annotationType() != Length.class) {
//            throw new ValidateException(String.format("校验类[%s]必须对应标注类型[%s]。", RangeValidator.class.getName(),
//                    Length.class));
//        }
//        Length length = (Length)anno;
//        this.message = length.message();
//        this.groups  = length.groups();
//        this.min     = length.min();
//        this.max     = length.max();
        Map<String, Serializable> params = loadCommon(anno);
        setMin((Integer) params.get("min"));
        setMax((Integer) params.get("max"));
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
