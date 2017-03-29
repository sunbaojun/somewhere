/**
 * 
 */
package com.eya.tools.generator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 字段提取工具类
 * @author luolin
 *
 * @version $id:FieldsUtils.java,v 0.1 2015年12月14日 下午4:34:55 luolin Exp $
 */
public class FieldUtils {

    /**
     * 获得表的字段信息
     * @param tableName 数据表名
     * @param dbUrl 数据库连接
     * @param dbUsername 用户名
     * @param dbPwd 密码
     * @return 字段集合
     * @throws SQLException
     */
    public static List<FieldInfo> getOracleFieldInfo(String tableName, String dbUrl, String dbUsername, String dbPwd) throws SQLException {
        tableName = tableName.toUpperCase();
        List<FieldInfo> fields = new ArrayList<FieldInfo>();
        // oracle查询字段名、数据类型、数据长度的语句
        String sql = "select column_name,data_type,data_length from user_tab_columns where table_name='" + tableName
                     + "'";
        Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPwd);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String columnName = rs.getString("column_name");

            // 获取注释
            PreparedStatement pstmt2 = conn
                .prepareStatement("select comments from user_col_comments where table_name='" + tableName
                                  + "' and column_name='" + columnName + "'");
            ResultSet rs2 = pstmt2.executeQuery();
            String comments = null;
            while (rs2.next()) {
                comments = rs2.getString(1);
                break;
            }
            pstmt2.close();
            rs2.close();
            // 校验是否为主键
            PreparedStatement pstmt3 = conn
                .prepareStatement("select col.column_name from user_constraints con,user_cons_columns col where con.constraint_name=col.constraint_name and con.constraint_type='P' and col.column_name='"
                                  + columnName + "'  and col.table_name='" + tableName + "'");
            ResultSet rs3 = pstmt3.executeQuery();
            boolean flag = false;
            while (rs3.next()) {
                flag = true;
                break;
            }
            pstmt3.close();
            rs3.close();
            fields.add(new FieldInfo(columnName, rs.getString("data_type"), comments == null ? "无注释" : comments, flag));
        }
        pstmt.close();
        rs.close();
        conn.close();
        return fields;
    }

    /**
     * 获得表的字段信息
     * @param tableName 数据表名
     * @param dbUrl 数据库连接
     * @param dbUsername 用户名
     * @param dbPwd 密码
     * @return 字段集合
     * @throws SQLException
     */
    public static List<FieldInfo> getMysqlFieldInfo(String tableName, String dbUrl, String dbUsername, String dbPwd)
                                                                                                                    throws SQLException {
        tableName = tableName.toUpperCase();
        List<FieldInfo> fields = new ArrayList<FieldInfo>();
        // mysql 查询字段名、数据类型、数据长度的语句
        String sql = "show fields from " + tableName;
        Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPwd);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // 字段名称
            String columnName = rs.getString(1);
            // 查询字段的注释
            PreparedStatement pstmt2 = conn
                .prepareStatement("select column_comment from INFORMATION_SCHEMA.Columns where table_name='"
                                  + tableName + "' and column_name='" + columnName + "'");
            ResultSet rs2 = pstmt2.executeQuery();
            String comments = null;
            while (rs2.next()) {
                comments = rs2.getString(1);
                break;
            }
            pstmt2.close();
            rs2.close();

            // 校验是否为主键
            PreparedStatement pstmt3 = conn
                .prepareStatement("select column_key from information_schema.columns where table_name='" + tableName
                                  + "' and column_name='" + columnName + "'");
            ResultSet rs3 = pstmt3.executeQuery();
            boolean flag = false;
            while (rs3.next()) {
                flag = true;
                break;
            }
            pstmt3.close();
            rs3.close();

            fields.add(new FieldInfo(columnName, rs.getString(2), comments == null ? "无注释" : comments, flag));
        }
        pstmt.close();
        rs.close();
        conn.close();
        return fields;
    }

    /**
     * 获得表的字段信息
     * @param tableName 数据表名
     * @param dbUrl 数据库连接
     * @param dbUsername 用户名
     * @param dbPwd 密码
     * @return 字段集合
     * @throws SQLException
     */
    public static List<FieldInfo> getDB2FieldInfo(String tableName, String dbUrl, String dbUsername, String dbPwd)
                                                                                                                  throws SQLException {
        tableName = tableName.toUpperCase();
        List<FieldInfo> fields = new ArrayList<FieldInfo>();

        // mysql 查询字段名、数据类型、数据长度的语句
        String sql = "SELECT NAME,COLTYPE,REMARKS,KEYSEQ FROM SYSIBM.SYSCOLUMNS WHERE TBNAME = '" + tableName + "'";
        Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPwd);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            // 字段名称
            String columnName = rs.getString("NAME");
            // 注释
            String comments = rs.getString("REMARKS");

            // 校验是否为主键
            boolean flag = false;
            // 当KEYSEQ=1时为主键，0时为普通字段
            if (rs.getInt("KEYSEQ") == 1) {
                flag = true;
            }

            fields.add(new FieldInfo(columnName, rs.getString(2), comments == null ? "无注释" : comments, flag));
        }
        pstmt.close();
        rs.close();
        conn.close();
        return fields;
    }
}
