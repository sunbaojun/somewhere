package com.eya.tools.generator.util;

//数据库实体信息
public class RecordInfo {

	// 字段名称
	private String fieldName;

	// 实体类字段
	private String property;

	// 字段类型
	private String type;

	// 字段长度
	private int length;

	private String jdbcType;
	// 字段备注
	private String comment;

	// 主键
	private boolean pk;

	public String getFieldName() {
		return fieldName;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public Boolean getPk() {
		return pk;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getType() {
		return type;
	}

	public String getJdbcType() {
		if (type != null) {
			if (type.startsWith("varchar")) {
				return "VARCHAR";
			} else if (type.startsWith("int")) {
				return "INTEGER";
			} else if (type.startsWith("bigint")) {
				return "INTEGER";
			} else if (type.equals("date")) {
				return "DATE";
			} else if (type.equals("datetime")) {
				return "VARCHAR";
			} else if (type.equalsIgnoreCase("VARCHAR2")) {
				return "VARCHAR2";
			} else if (type.equalsIgnoreCase("XMLTYPE")) {
				return "CLOB";
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
		if (type != null && !"date".equals(type) && !"text".equals(type)
				&& !"datetime".equals(type)) {
			int first = type.indexOf("(");
			int last = type.lastIndexOf(")");
			length = Integer.parseInt(type.substring(first + 1, last));
		} else {
			length = 0;
		}

		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public RecordInfo(String fieldName, String type, String jdbcType,
			String comment, boolean pk) {
		super();
		this.fieldName = fieldName;
		this.type = type;
		this.comment = comment;
		this.pk = pk;
		this.jdbcType = jdbcType;
		this.property = getFieldName(fieldName);
	}

	public String getFieldName(String fieldName) {
		fieldName = fieldName.toLowerCase();
		fieldName = MyStringUtil.getEntityName(fieldName);
		char oldChar = fieldName.charAt(0);

		char newChar = (oldChar + "").toLowerCase().charAt(0);

		fieldName = fieldName.replace(oldChar, newChar);

		return fieldName;

	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = getFieldName(fieldName);
		;
	}

}
