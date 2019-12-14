package com.ruoyi.web.test;


import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class Demo
{
    public static void main(String[] args) throws Exception {
        generateSql("D:\\mapper", "D:\\sql\\exam\\train.sql");
    }
    /**
     * 生成sql
     * @param dirPath mapper.xml的父级文件夹
     * @param sqlFile 选择你将要生成sql的文件
     * @throws IOException
     */
    private static void generateSql(String dirPath,String sqlFile) throws IOException {
        FileWriter fw = null;
        try {
            File dir = new File(dirPath);
            File sql = new File(sqlFile);
            if (sql.exists()){
                sql.delete();
            }
            sql.createNewFile();
            fw = new FileWriter(sql);

            if (dir.exists() && dir.isDirectory()){
                File[] files = dir.listFiles();
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".xml")){
                        System.out.println(file.getName());
                        fw.append("\r\n");
                        fw.append(getSql(file));
                        fw.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (fw != null)
                fw.close();
        }
    }

    private static String getSql(File xmlfile) throws DocumentException {
        SAXReader saxReader= new SAXReader();
        Document document = saxReader.read(xmlfile);
        Element root = document.getRootElement();
        Element resultMap = root.element("resultMap");
        Tab tab = new Tab();
        tab.setTableName(getTableName(root));
        tab.setColumns(getColumns(resultMap));
        return tab.toString();
    }
    private static Map<String,String> getColumns(Element resultMap){
        List<Element> elements = resultMap.elements();
        Map<String,String> map = new LinkedHashMap<String, String>();
        for (Element element : elements) {
            map.put(element.attribute("column").getValue(), "int(11) NOT NULL COMMENT ' '");
        }
        return map;
    }
    private static String getTableName(Element root){
        Element selectByPrimaryKey = root.element("select");
        Element sqlByPrimaryKey = root.element("sql");
        String selectStr = selectByPrimaryKey.getTextTrim();
        String sqlStr = sqlByPrimaryKey.getTextTrim();
        String[] strs =  selectStr.split("from");
        String tableName = StringUtils.isEmpty(selectStr.split("from")[0].trim()) ?
                sqlStr.split("from")[1].trim().split(" ")[0].trim() : selectStr.split("from")[1].trim().split(" ")[0].trim();
        return tableName;
    }

}
