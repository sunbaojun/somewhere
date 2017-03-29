package com.eya.tools.generator.util;

public class MyStringUtil {

	// 处理数据库表名到Entity表名的转换,有下划线的处理掉
	public static String getEntityName(String tableName) {

		String temp = "";

		if (tableName.contains("_")) {

			String[] tnames = tableName.split("_");

			for (String tname : tnames) {

				char oldChar = tname.charAt(0);

				char newChar = (oldChar + "").toUpperCase().charAt(0);

				temp += tname.replaceFirst(oldChar+"", newChar+"");

			}
		} else {

			char oldChar = tableName.charAt(0);

			char newChar = (oldChar + "").toUpperCase().charAt(0);

			temp = tableName.replaceFirst(oldChar+"", newChar+"");

		}
		return temp;
	}
	
	
	
	
}
