
package com.xt.gt.html.model.impl;

import com.xt.gt.html.model.ClassFilter;
import com.xt.gt.sys.SystemConfiguration;

/**
 *
 * @author albert
 */
public class DefaultClassFilter implements ClassFilter {

    private final String prefix = SystemConfiguration.getInstance().readString("classFilterPrefix", "com.xt");

    public boolean isLoad(Class loadingClass) {
        if (loadingClass == null) {
            return false;
        }
        return loadingClass.getName().startsWith(prefix);
    }

}
