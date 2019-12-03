package com.ruoyi.web.test;

import java.util.Map;
import java.util.Set;

public class Tab {
    private String tableName;

    private String idName;

    private String idType;

    private Map<String,String> columns;

    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getIdName() {
        return idName;
    }
    public void setIdName(String idName) {
        this.idName = idName;
    }
    public String getIdType() {
        return idType;
    }
    public void setIdType(String idType) {
        this.idType = idType;
    }
    public Map<String, String> getColumns() {
        return columns;
    }
    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("DROP TABLE  IF EXISTS "+tableName+";");
        sb.append("\r\n");
        sb.append("CREATE TABLE ");
        sb.append(tableName);
        sb.append("(");
        sb.append("\r\n");
        Set<Map.Entry<String,String>> set = columns.entrySet();
        int size = set.size();
        int i = 0;
        for (Map.Entry<String,String> entry : set) {
            i++;
            sb.append("\t"+entry.getKey()+" "+format(entry.getValue()));
            if (size != i){
                sb.append(" ,");
                sb.append("\r\n");
            }

        }
        sb.append("\r\n");
        sb.append(");");
        sb.append("\r\n");
        return sb.toString();

    }
    private String format(String val){
        if (val.equals("VARCHAR")){
            return "VARCHAR(255)";
        }
        return val;
    }
}
