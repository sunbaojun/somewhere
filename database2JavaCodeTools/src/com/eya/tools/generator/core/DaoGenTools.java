package com.eya.tools.generator.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 读代码知道大概意思可能是数据迁移（mssql -> mysql的一个例子，数据迁移建议使用kettle）
 * @author luolin
 *
 * @version $id:DaoGenTools.java,v 0.1 2015年12月14日 下午5:22:12 luolin Exp $
 */
public class DaoGenTools {

    static {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //	public EntityTpl getTblInfo(String tableName) throws Exception {
    //		String sql = "show full fields from " + tableName;
    //		Connection conn = DriverManager .getConnection( "jdbc:mysql://localhost:3306/mchis?useUnicode=true&amp;characterEncoding=utf-8", "root", "root");
    //		PreparedStatement pstmt = conn.prepareStatement(sql);
    //		ResultSet rs = pstmt.executeQuery();
    //
    //		EntityTpl tpl = new EntityTpl();
    //		tpl.setTablename(tableName);
    //		while (rs.next()) {
    //			tpl.addRecord(new RecordInfo(rs.getString(1), rs.getString(2),rs.getString(4), rs.getString(9), rs.getString(5)));
    //		}
    //		rs.close();
    //		conn.close();
    //		return tpl;
    //	}

    public void init() {
    }

    public void gen(String[] tables) throws Exception {
        //Sqlite Connection
        Connection sqliteConn = DriverManager.getConnection("jdbc:sqlite:mchis.db");
        Statement sqliteStat = sqliteConn.createStatement();

        //Mysql Connection
        Connection mysqlConn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mchis?useUnicode=true&amp;characterEncoding=utf-8", "root", "root");
        Statement mysqlStmt = mysqlConn.createStatement();
        for (String table : tables) {

            sqliteStat.executeUpdate("drop table if exists " + table + ";");
            sqliteStat.executeUpdate("create table " + table + " (" + "" + ");");

            ResultSet rs = mysqlStmt.executeQuery("select * from " + table);
            while (rs.next()) {
                String values = "insert into " + table + " values(";
                for (int i = 1; i <= 5; i++) {
                    values += "'" + rs.getString(i) + "',";
                }
                values = values.substring(0, values.length() - 1);
                values += ");";

                System.out.println(values);
            }
        }

    }

    public static void main(String[] args) {

        String[] tables = new String[] { "org", "org_type", "org_res", "orgtype_res", "region", "users", "users_res" };

        DaoGenTools tool = new DaoGenTools();

        try {
            tool.gen(tables);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
