
package com.xt.gt.html.parse.impl;

import com.xt.core.exception.SystemException;
import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author albert
 */
public class EnumParserTest extends TestCase {
    
    public EnumParserTest(String testName) {
        super(testName);
    }

    public void testParse() {
        // {"__className":"java.lang.Enum","name":"REGISTERED","ordinal":0,"title":"REGISTERED","className":"com.xt.bcloud.app.AppVersionState"}
        String className = "com.xt.gt.html.parse.impl.EnumForParserTest";

        if (StringUtils.isEmpty(className)) {
            throw new SystemException(String.format("枚举类型[%s]的属性[className]不能为空。"));
        }
        Class _enum;
        try {
            _enum = Class.forName(className);
            String name = "REGISTERED";
            if (StringUtils.isEmpty(name)) {
                return;
            }
            System.out.println("Enum.valueOf(_enum, name)=" + Enum.valueOf(_enum, name));
        } catch (ClassNotFoundException ex) {
            throw new SystemException(String.format("未找到枚举类型[%s]的定义。", className), ex);
        }
    }

}
