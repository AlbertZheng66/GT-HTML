
package com.xt.gt.html.service;

import com.xt.core.service.IService;
import com.xt.core.utils.ClassHelper;
import com.xt.gt.ui.table.ColumnInfoHelper;
import com.xt.gt.ui.table.TableColumnInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author albert
 */
public class TableService implements IService {

    private final Logger logger = Logger.getLogger(TableService.class);

    public TableService() {
    }
    
    /**
     * 返回指定类的已经进行标注的属性信息。
     * @param className
     * @return
     */
    public List<TableColumnInfoEx> getAnnotatedColumnInfos (String className) {
        Class tableClass =  ClassHelper.getClass(className);
        List<TableColumnInfo> columnInfos = ColumnInfoHelper.createColumnsFromAnnotation(tableClass, null, null);

        logger.debug("22222222222 getAnnotatedColumnInfos columnInfos=" + columnInfos);
        return convert(columnInfos);
    }

     /**
     * 返回指定类的所有属性信息。
     * @param className
     * @return
     */
    public List<TableColumnInfoEx> getColumnInfos (String className) {
        Class tableClass =  ClassHelper.getClass(className);
        List<TableColumnInfo> columnInfos = ColumnInfoHelper.createColumnsFromProperties(tableClass, null, null);
        logger.debug("getColumnInfos columnInfos=" + columnInfos);
        return convert(columnInfos);
    }

    private List<TableColumnInfoEx> convert(List<TableColumnInfo> tcis) {
        List<TableColumnInfoEx> tices = new ArrayList(tcis.size());
        for(Iterator<TableColumnInfo> iter = tcis.iterator(); iter.hasNext(); ) {
            TableColumnInfoEx tcie = new TableColumnInfoEx();
            tcie.load(iter.next());
            tices.add(tcie);
        }
        return tices;
    }
}
