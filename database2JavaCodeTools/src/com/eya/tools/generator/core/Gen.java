package com.eya.tools.generator.core;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;

import com.eya.tools.generator.Test;
import com.eya.tools.generator.util.EntityTpl;
import com.eya.tools.generator.util.FieldInfo;
import com.eya.tools.generator.util.MyFile;
import com.eya.tools.generator.util.MyStringUtil;
import com.eya.tools.generator.util.RecordInfo;
import com.eya.tools.generator.util.TableInfo;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 代码生生成器核心工具类
 * @author luolin
 *
 * @version $id:Gen.java,v 0.1 2015年12月15日 上午10:47:57 luolin Exp $
 */
public class Gen {

    /**
     * 装载实体类模板对象
     * @param tableInfo {@link TableInfo}
     * @param className 
     * @param topLevel 顶层包名
     * @param moduleName 模块名称
     * @return {@link EntityTpl}
     * @throws Exception
     */
    public EntityTpl getTblInfo(TableInfo tableInfo, String className, String topLevel, String moduleName)
                                                                                                          throws Exception {
        EntityTpl tpl = new EntityTpl();
        tpl.setPkColumn(tableInfo.getPkColumn());// 主键
        tpl.setTablecomment(tableInfo.getComment());// 注释
        tpl.setClassName(className);// 实体类的类名
        tpl.setTablename(tableInfo.getName());// 数据表名
        tpl.setPackageName(topLevel + "." + moduleName + ".model");// model包全名
        tpl.setDaoPackage(topLevel + "." + moduleName + ".mapper");// dao包全名
        tpl.setServicePackage(topLevel + "." + moduleName + ".service");// sevice包全名
        tpl.setActionPackage(topLevel + "." + moduleName + ".controller");// action包全名
        //tpl.setSqlLitePackageName("com.yogcn.electricity.security.dao.entity");
        // 循环表的字段，封装Entity对应的字段信息
        for (FieldInfo fi : tableInfo.getFields()) {
            tpl.addRecord(new RecordInfo(fi.getFieldName(), fi.getType(), fi.getJdbcType(), fi.getComment(), fi.getPk()));
        }
        return tpl;
    }

    private Configuration cfg;

    /**
     * 初始化，载入存放模板的文件夹
     */
    public void init() {
        cfg = new Configuration();
        // 这里需要使用一个和template同级目录的JAVA类的class去加载template文件夹路径
        URL url = Test.class.getResource("template");
        cfg.setDefaultEncoding("UTF-8");
        File tplPath = new File(url.getFile());
        try {
            cfg.setDirectoryForTemplateLoading(tplPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 生成代码
     * @param tables 要自动映射处理的表的集合
     * @param myfile 
     * @throws Exception
     */
    public void gen(List<TableInfo> tables, MyFile myfile, String topLevel, String moduleName) throws Exception {
        // 初始化
        init();

        for (TableInfo table : tables) {
            // 装载实体类模板对象
            EntityTpl entity = getTblInfo(table, MyStringUtil.getEntityName(table.getName()), topLevel, moduleName);
            // 获得主键
            entity.getPkRecords();
            // 载入实体类模板
            Template entityTpl = cfg.getTemplate("/Entity.tpl");

            StringWriter entityWriter = new StringWriter();
            // 将实体类模板和预先装载好的实体类模板对象进行组装，然后存入到StringWriter中
            entityTpl.process(entity, entityWriter);

            // 写JAVA文件
            myfile.writeFile(entityWriter.toString(), MyStringUtil.getEntityName(table.getName()), "entity",
                entity.getPackageName());

            // ------------------------- 数据访问层mapper或者dao（类似实体类的生成步骤）
            // ----------------------------
            Template daoTpl = cfg.getTemplate("/Dao.tpl");

            StringWriter daoWriter = new StringWriter();

            daoTpl.process(entity, daoWriter);

            myfile.writeFile(daoWriter.toString(), MyStringUtil.getEntityName(table.getName()) + "Mapper", "dao",
                entity.getDaoPackage());

            // ------------------------- 映射文件Mapper.xml（类似实体类的生成步骤）
            // ----------------------------

            Template maperTpl = cfg.getTemplate("/Mapper.tpl");

            StringWriter mapperWriter = new StringWriter();

            maperTpl.process(entity, mapperWriter);

            myfile.writeFile(mapperWriter.toString(), MyStringUtil.getEntityName(table.getName()), "mapper",
                entity.getPackageName());

            // ------------------------- Service（类似实体类的生成步骤） ----------------------------

            Template serviceTpl = cfg.getTemplate("/Service.tpl");

            StringWriter serviceWriter = new StringWriter();

            serviceTpl.process(entity, serviceWriter);

            myfile.writeFile(serviceWriter.toString(), MyStringUtil.getEntityName(table.getName()) + "Service",
                "service", entity.getServicePackage());

            // ------------------------- ServiceImpl（类似实体类的生成步骤）

            Template serviceImplTpl = cfg.getTemplate("/ServiceImpl.tpl");

            StringWriter serviceImplWriter = new StringWriter();

            // 鐠囪褰囧Ο鈩冩緲娣団剝浼�
            serviceImplTpl.process(entity, serviceImplWriter);

            myfile.writeFile(serviceImplWriter.toString(), MyStringUtil.getEntityName(table.getName()) + "ServiceImpl",
                "serviceimpl", entity.getServicePackage());

            // ------------------------- Action（类似实体类的生成步骤） ----------------------------
            Template actionTpl = cfg.getTemplate("/Action.tpl");

            StringWriter actionWriter = new StringWriter();

            actionTpl.process(entity, actionWriter);

            myfile.writeFile(actionWriter.toString(), MyStringUtil.getEntityName(table.getName()) + "Ctrl", "action",
                entity.getActionPackage());

            //            Template inputTpl = cfg.getTemplate(module + "/jsp/input.tpl");
            //            StringWriter inputWriter = new StringWriter();
            //            inputTpl.process(entity, inputWriter);
            //            myfile.writeFile(inputWriter.toString(), MyStringUtil.getEntityName(table.getName())
            //                .toLowerCase(), "input.jsp", null);
            //
            //            Template listTpl = cfg.getTemplate(module + "/jsp/list.tpl");
            //            StringWriter listWriter = new StringWriter();
            //            listTpl.process(entity, listWriter);
            //            myfile.writeFile(listWriter.toString(), MyStringUtil.getEntityName(table.getName())
            //                .toLowerCase(), "list.jsp", null);
        }
    }
}
