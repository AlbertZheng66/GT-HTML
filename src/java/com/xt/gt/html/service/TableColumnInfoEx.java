
package com.xt.gt.html.service;

import com.xt.gt.ui.table.TableColumnInfo;


/**
 * 对表格的列信息进行扩展，便于 JavaScript 的使用。
 * @author albert
 */
public class TableColumnInfoEx extends TableColumnInfo {

    private boolean enumType;

    public TableColumnInfoEx() {
    }

    public void load (TableColumnInfo tci) {
        if (tci == null) {
            return;
        }
        this.setAlignment(tci.getAlignment());
        this.setCellEditor(tci.getCellEditor());
        this.setCellRenderer(tci.getCellRenderer());
        this.setDictionaryInfo(tci.getDictionaryInfo());
        this.setDisable(tci.isDisable());
        this.setPropertyClassName(tci.getPropertyClassName());
        this.setPropertyClass(tci.getPropertyClass());
        this.setPropertyName(tci.getPropertyName());
        this.setReadonly(tci.isReadonly());
        this.setResizable(tci.isReadonly());
        this.setSeq(tci.getSeq());
        this.setTitle(tci.getTitle());
        this.setVisiable(tci.isVisiable());
        this.setWidth(tci.getWidth());
        enumType = tci.getPropertyClass().isEnum();
    }

    public boolean isEnumType() {
        return enumType;
    }

    public boolean getEnumType() {
        return enumType;
    }

    public void setEnumType(boolean enumType) {
        this.enumType = enumType;
    }

}
