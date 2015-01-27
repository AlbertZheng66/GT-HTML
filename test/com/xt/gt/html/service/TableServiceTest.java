
package com.xt.gt.html.service;

import com.xt.gt.html.model.TestBean;
import com.xt.gt.ui.table.TableColumnInfo;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author albert
 */
public class TableServiceTest extends TestCase {
    
    public TableServiceTest(String testName) {
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
     * Test of getTableColumnInfos method, of class TableService.
     */
    public void testGetTableColumnInfos() {
        System.out.println("getTableColumnInfos");
        String className = TestBean.class.getName();
        TableService instance = new TableService();
//        List<TableColumnInfo> expResult = null;
//        List<TableColumnInfo> result = instance.getColumnInfos(className);
    }

}
