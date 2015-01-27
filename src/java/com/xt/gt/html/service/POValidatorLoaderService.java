package com.xt.gt.html.service;

import com.xt.gt.html.val.RequiredValidator;
import com.xt.core.db.meta.Column;
import com.xt.core.db.meta.Database;
import com.xt.core.db.meta.Table;
import com.xt.core.db.pm.IPersistence;
import com.xt.core.log.LogWriter;
import com.xt.core.map.DBMapping;
import com.xt.core.service.IService;
import com.xt.core.utils.ClassHelper;
import com.xt.gt.html.val.LengthValidator;
import com.xt.core.db.po.mapping.SimpleDBMapping;
import com.xt.core.exception.ServiceException;
import com.xt.core.validation.EditMode;
import com.xt.core.validation.ValidatorConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * 用于装载类的指定校验类型。
 * @author albert
 */
public class POValidatorLoaderService implements IService {

    private static final Logger logger = Logger.getLogger(POValidatorLoaderService.class);

    public POValidatorLoaderService() {
    }

    /**
     * 返回类的所有可校验器。
     * @param clazz 类实例
     * @return 类经常及其校验器。
     */
    public Map<String, List<Serializable>> getValidators(Class clazz/*, ValidatorConfig validatorConfig*/) {
        if (clazz == null || !IPersistence.class.isAssignableFrom(clazz)) {
            throw new ServiceException(String.format("类不能为空，且必须实现接口[%s]。", IPersistence.class.getName()));
        }
        Table table = getTable(clazz);
        if (table == null) {
            LogWriter.warn(logger, String.format("类[%s]没有相对应的表。", clazz.getName()));
            return Collections.EMPTY_MAP;
        }
        Map<String, List<Serializable>> validators = new HashMap();
        String[] propertyNames = ClassHelper.getPropertyNames(clazz);
        for (String name : propertyNames) {
            List<Serializable> validatables = load(table, new ValidatorConfig(), clazz, name);
            if (validatables != null) {
                validators.put(name, validatables);
            }
        }
        return validators;
    }

    private List load(Table table, ValidatorConfig validatorConfig, Class validatedClass, String propertyName) {
        DBMapping mapping = new SimpleDBMapping();
        String columnName = mapping.getColumnName(validatedClass, propertyName);
        Column column = table.find(columnName);
        if (column == null) {
            LogWriter.warn(logger, String.format("在表[%s]中的未找到与属性[%s]相对应的字段[%s]。",
                    table.getName(), propertyName, columnName));
            return null;
        }
        List<Serializable> validatables = new ArrayList();
        // 增加一个非空校验
        if (!column.isNullable() && validatorConfig.getEditMode() != EditMode.QUERY) {
            validatables.add(new RequiredValidator());
        }
        // 增加一个类型校验
        if (!isVarchar(column.getSqlType())) {
            // validatables.add(new SqlTypeValidator(column.getSqlType()));
        }
        // 增加一个长度校验
        if (isVarchar(column.getSqlType())) {
            validatables.add(new LengthValidator(column.getWidth()));
        }
        return validatables;
    }

    /**
     * 是否是字符相关的类型。
     * @param sqlType
     * @return
     */
    private boolean isVarchar(int sqlType) {
        return (sqlType == java.sql.Types.NVARCHAR 
                || sqlType == java.sql.Types.VARCHAR
                || sqlType == java.sql.Types.CHAR
                || sqlType == java.sql.Types.VARCHAR
                || sqlType == java.sql.Types.NCHAR);
    }

    private Table getTable(Class validatedClass) {
        DBMapping mapping = new SimpleDBMapping();
        String tableName = mapping.getTableName(validatedClass);
        Table table = Database.getInstance().find(null, tableName);
        return table;
    }
}
