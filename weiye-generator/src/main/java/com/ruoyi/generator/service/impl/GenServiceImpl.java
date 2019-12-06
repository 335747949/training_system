package com.ruoyi.generator.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.generator.domain.ColumnInfo;
import com.ruoyi.generator.domain.TableInfo;
import com.ruoyi.generator.mapper.GenMapper;
import com.ruoyi.generator.service.IGenService;
import com.ruoyi.generator.util.GenUtils;
import com.ruoyi.generator.util.VelocityInitializer;

/**
 * 代码生成 服务层处理
 *
 * @author ruoyi
 */
@Service
public class GenServiceImpl extends AbstractBaseServiceImpl<GenMapper, TableInfo> implements IGenService {
    private static final Logger log = LoggerFactory.getLogger( GenServiceImpl.class );


    /**
     * 查询ry数据库表信息
     *
     * @param tableInfo 表信息
     * @return 数据库表列表
     */
    @Override
    public List<TableInfo> selectTableList(TableInfo tableInfo) {
        startPage();
        return mapper.selectTableList( tableInfo );
    }

    /**
     * 生成代码
     *
     * @param tableName 表名称
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String tableName) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream( outputStream );
        // 查询表信息
        TableInfo table = mapper.selectTableByName( tableName );
        // 查询列信息
        List<ColumnInfo> columns = mapper.selectTableColumnsByName( tableName );
        // 生成代码
        generatorCode( table, columns, zip );
        IOUtils.closeQuietly( zip );
        return outputStream.toByteArray();
    }

    /**
     * 批量生成代码
     *
     * @param tableNames 表数组
     * @return 数据
     */
    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream( outputStream );
        for (String tableName : tableNames) {
            // 查询表信息
            TableInfo table = mapper.selectTableByName( tableName );
            // 查询列信息
            List<ColumnInfo> columns = mapper.selectTableColumnsByName( tableName );
            // 生成代码
            generatorCode( table, columns, zip );
        }
        IOUtils.closeQuietly( zip );
        return outputStream.toByteArray();
    }

    /**
     * 生成代码
     */
    public void generatorCode(TableInfo table, List<ColumnInfo> columns, ZipOutputStream zip) {
        // 表名转换成Java属性名
        String className = GenUtils.tableToJava( table.getTableName() );
        table.setClassName( className );
        table.setClassname( StringUtils.uncapitalize( className ) );
        // 列信息
        table.setColumns( GenUtils.transColums( columns ) );
        // 设置主键
        table.setPrimaryKey( table.getColumnsLast() );

        VelocityInitializer.initVelocity();

        String packageName = Global.getPackageName();
        String moduleName = GenUtils.getModuleName( packageName );

        VelocityContext context = GenUtils.getVelocityContext( table );

        // 获取模板列表
        List<String> templates = GenUtils.getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate( template, Constants.UTF8 );
            tpl.merge( context, sw );
            try {
                // 添加到zip
                zip.putNextEntry( new ZipEntry( GenUtils.getFileName( template, table, moduleName ) ) );
                IOUtils.write( sw.toString(), zip, Constants.UTF8 );
                IOUtils.closeQuietly( sw );
                zip.closeEntry();
            } catch (IOException e) {
                log.error( "渲染模板失败，表名：" + table.getTableName(), e );
            }
        }
    }
}
