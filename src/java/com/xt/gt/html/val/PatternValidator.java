package com.xt.gt.html.val;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 *
 * @author albert
 */
abstract public class PatternValidator extends AbstractValidator {


    public PatternValidator() {
    }

    @Override
    public void load(Annotation anno) {
        Map<String, Serializable> params = loadCommon(anno);
        setPattern((String) params.get("pattern"));
    }

}
