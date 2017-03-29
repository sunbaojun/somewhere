package com.eya.tools.generator.util;

import java.sql.Timestamp;
 
public class FieldInfo {

	// 字段名称
	private String fieldName;

	// 字段类型
	private String type;

	// 字段长度
	private int length;
 

	// 字段备注
	private String comment;

	// 主键
	private Boolean pk;

	public String getFieldName() {
		return fieldName;
	}
 

	public Boolean getPk() {
		return pk;
	}


	public void setPk(Boolean pk) {
		this.pk = pk;
	}


	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getType() {
		if (type != null) {
			if (type.startsWith("varchar")) {
				return "String";
			} else if (type.startsWith("int")) {
				return "Integer";
			} else if (type.startsWith("bigint")) {
				return "Long";
			} else if (type.startsWith("date")) {
				return "Date";
			} else if(type.equalsIgnoreCase("VARCHAR2")||type.equalsIgnoreCase("char")){
				return "String";
			}else if(type.equalsIgnoreCase("TIMESTAMP(6)")){
				return "Timestamp";
			}else if(type.equalsIgnoreCase("XMLTYPE")){
				return "String";
			}else {
				return "nontype";
			}
		} else {
			return "nontype";
		}
	}

	public String getJdbcType() {
		if (type != null) {
			if (type.startsWith("varchar")) {
				return "VARCHAR";
			} else if (type.startsWith("int")) {
				return "INTEGER";
			} else if (type.startsWith("bigint")) {
				return "INTEGER";
			} else if (type.startsWith("date")) {
				return "DATE";
			} else if(type.equalsIgnoreCase("VARCHAR2")){
				return "VARCHAR2";
			}else if(type.equalsIgnoreCase("TIMESTAMP")){
				return "TIMESTAMP";
			}else if(type.equalsIgnoreCase("XMLTYPE")){
				return "XMLTYPE";
			}else  {
				return "NONJDBC";
			}
		} else {
			return "NONJDBC";
		}
	}
	
	public String getSqlite2Type() {
		if (type != null) {
			if (type.startsWith("varchar")) {
				return "VARCHAR";
			} else if (type.startsWith("int")) {
				return "int";
			} else if (type.startsWith("bigint")) {
				return "int";
			} else if (type.startsWith("date")) {
				return "date";
			}else if (type.startsWith("text")) {
				return "text";
			} else {
				return "NONJDBC";
			}
		} else {
			return "NONJDBC";
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLength() {
		length = 1;
		if (type != null && !type.startsWith("text")) {
			int first = type.indexOf("(");
			int last = type.lastIndexOf(")");
			length = Integer.parseInt(type.substring(first + 1, last));
		}

		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	 

	public FieldInfo(String fieldName, String type, 
			String comment, boolean pk) {
		super();
		this.fieldName = fieldName;
		this.type = type;
		this.comment = comment;
		this.pk = pk;
		 
	}

}
