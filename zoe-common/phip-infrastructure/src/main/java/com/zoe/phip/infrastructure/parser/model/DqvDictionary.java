package com.zoe.phip.infrastructure.parser.model;

/**
 * Created by zhangwenbin on 2016/3/2.
 */
public class DqvDictionary extends AbstractDataQualityValue {

    private String key;
    private String codeColumn;
    private String keyColumn;
    private String tableName;
    private String dictId;

    public DqvDictionary() {

        super(DqvType.Dictionary);
    }

    public final String getKey() {
        return key;
    }

    public final void setKey(String value) {
        key = value;
    }

    public final String getCodeColumn() {
        return codeColumn;
    }

    public final void setCodeColumn(String value) {
        codeColumn = value;
    }

    public final String getKeyColumn() {
        return keyColumn;
    }

    public final void setKeyColumn(String value) {
        keyColumn = value;
    }

    public final String getTableName() {
        return tableName;
    }

    public final void setTableName(String value) {
        tableName = value;
    }

    public final String getDictId() {
        return dictId;
    }

    public final void setDictId(String value) {

        dictId = value;
    }
}