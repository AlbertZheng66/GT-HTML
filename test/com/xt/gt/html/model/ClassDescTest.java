
package com.xt.gt.html.model;

import com.xt.gt.html.model.*;
import junit.framework.TestCase;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author albert
 */
public class ClassDescTest extends TestCase {
    
    public ClassDescTest(String testName) {
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
     * Test of load method, of class ClassDesc.
     */
    public void testLoad() {
        System.out.println("load");
        ClassDesc instance = new ClassDesc();
        instance.load(TestBean.class);
        System.out.println("instance=" + ToStringBuilder.reflectionToString(instance));
        // TODO review the generated test code and remove the default call to fail.
    }

   

}
