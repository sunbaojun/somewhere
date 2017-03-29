/**
 * 
 */
package com.eya.tools.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Administrator
 *
 */
public class Test {
    private static String db_user      = "db2inst1";
    private static String db_pwd       = "op400230";
    private static String db_url       = "jdbc:oracle:thin:@192.168.1.133:1521";
    private static String db_url2      = "jdbc:oracle:thin:@192.168.1.124:1521:ORCLBAOTOU";
    private static String db_url_mysql = "jdbc:mysql://localhost:3306/test";
    private static String db_url_db2   = "jdbc:db2://192.168.110.68:50000/HPE0313";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String sql = "SELECT NAME,COLTYPE,REMARKS,KEYSEQ FROM SYSIBM.SYSCOLUMNS WHERE TBNAME ='BSS_ASSET'";
        Connection conn;
        try {
            conn = DriverManager.getConnection(db_url_db2, db_user, db_pwd);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getInt("KEYSEQ") == 1);
                System.out.println("******************");
//                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
