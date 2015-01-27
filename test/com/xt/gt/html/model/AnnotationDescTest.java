

package com.xt.gt.html.model;

import com.xt.gt.html.model.*;
import com.xt.gt.ui.table.ColumnInfo;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author albert
 */
public class AnnotationDescTest extends TestCase {
    
    public AnnotationDescTest(String testName) {
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
     * Test of load method, of class AnnotationDesc.
     */
    public void testLoad() throws NoSuchFieldException {
        System.out.println("load");
        Annotation annotation = TestBean.class.getField("string2").getAnnotation(ColumnInfo.class);
        AnnotationDesc instance = new AnnotationDesc();
        instance.load(annotation);
        // TODO review the generated test code and remove the default call to fail.
    }

}
