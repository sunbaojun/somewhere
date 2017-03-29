/**
 * 
 */
package com.eya.tools.generator.executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eya.tools.generator.core.Gen;
import com.eya.tools.generator.util.FieldUtils;
import com.eya.tools.generator.util.MyFile;
import com.eya.tools.generator.util.TableInfo;

/**
 * mysql数据库映射生成JAVA代码
 * @author luolin
 *
 * @version $id:MysqlGenerator.java,v 0.1 2015年12月15日 上午10:20:12 luolin Exp $
 */
public class MysqlGenerator {

    private static final String DB_URL      = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USERL    = "root";
    private static final String DB_PWD      = "root";

    // 顶层包名
    private static final String TOP_LEVEL   = "com.eya";
    // 模块（加上顶层包名，最后生成如：com.eya.test）
    private static final String MODULE_NAME = "test";

    /** 存放生成的JAVA文件的目录 */
    private static final String SRC_FOLDER  = "E:\\workspace\\test\\src\\main\\java\\";
    /** 存放生成的jsp文件的目录 */
    private static final String WEB_FOLDER  = "E:\\workspace\\test\\src\\main\\webapp\\WEB-INF";

    /**
     * 获得表信息
     * @param tableNames 表集合
     * @return
     * @throws SQLException
     */
    private static List<TableInfo> getTableInfo(Map<String, String> tableNames) throws SQLException {
        List<TableInfo> tables = new ArrayList<TableInfo>();
        for (String key : tableNames.keySet()) {
            TableInfo ti = new TableInfo(key, tableNames.get(key), FieldUtils.getMysqlFieldInfo(key, DB_URL, DB_USERL,
                DB_PWD));
            tables.add(ti);
        }
        return tables;
    }

    public static void main(String[] args) {
        Gen tool = new Gen();
        try {
            // 用于建立目录和写文件
            MyFile myfile = new MyFile(SRC_FOLDER, WEB_FOLDER);

            // 将需要添加的表存入map中，key为表名，value为生成的实体类的注释
            Map<String, String> map = new HashMap<String, String>();
            map.put("user".toLowerCase(), "用户表");

            //准备表对象
            List<TableInfo> tables = getTableInfo(map);
            tool.gen(tables, myfile,TOP_LEVEL,MODULE_NAME);
            System.out.println("=======共计生成表：========" + tables.size() + "张");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
