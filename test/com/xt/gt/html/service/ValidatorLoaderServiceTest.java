/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xt.gt.html.service;

import com.xt.gt.html.model.TestBean;
import java.util.Map;
import junit.framework.TestCase;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author albert
 */
public class ValidatorLoaderServiceTest extends TestCase {
    
    public ValidatorLoaderServiceTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getValidators method, of class ValidatorLoaderService.
     */
    public void testGetValidators() {
        System.out.println("getValidators");
        Class clazz = TestBean.class;
        ValidatorLoaderService instance = new ValidatorLoaderService();
        Map result = instance.getValidators(clazz);
        System.out.println("result=" + ToStringBuilder.reflectionToString(result));
    }

}
