package com.eya.tools.generator.util;

import java.util.List;
 
public class TableInfo {
 
	private String name;
 
	private String pkColumn ; 
	private String comment;

	private List<FieldInfo> fields;
	 

	public TableInfo() {

	}

	public TableInfo( String name, String comment, List<FieldInfo> fields) {
		this.name = name;
		this.comment = comment;
		this.fields = fields;
		 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<FieldInfo> getFields() {
		return fields;
	}

	public void setFields(List<FieldInfo> fields) {
		this.fields = fields;
	}

	/** 
	 * @return
	 */
	public String getField() {
		String fieldStr = "";
		for (FieldInfo ri : this.fields) {
			if(!ri.getFieldName().equals("dlt")){
				if("date".equals(ri.getSqlite2Type())){
					System.out.println("-----------");
				}
				if(ri.getPk()){
					fieldStr += ri.getFieldName()+" "+ "INTEGER";
					if(!"date".equals(ri.getSqlite2Type())){
						 
						fieldStr+="  PRIMARY KEY AUTOINCREMENT ,";
					}else{
						fieldStr+=",";
					}
				}else{
					fieldStr += ri.getFieldName()+" "+ ri.getSqlite2Type() ;
					if(!"date".equals(ri.getSqlite2Type())){
						fieldStr+="("+ri.getLength()+"),";
					}else{
						fieldStr+=",";
					}
				}
				
				
				
			}
		}
		System.out.println(fieldStr);
		return fieldStr.substring(0, fieldStr.length() - 1);
	}
	
	/** 
	 * @return
	 */
	public String getFieldOnlyFieldName() {
		String fieldStr = "";
		for (FieldInfo ri : this.fields) {
			if(!ri.getFieldName().equals("dlt")){
				fieldStr += ri.getFieldName() + ",";
			}
		}
		return fieldStr.substring(0, fieldStr.length() - 1);
	}

    /**
     * Getter method for property <tt>pkColumn</tt>.
     * 
     * @return property value of pkColumn
     */
    public String getPkColumn() {
        return pkColumn;
    }

    /**
     * Setter method for property <tt>pkColumn</tt>.
     * 
     * @param pkColumn value to be assigned to property pkColumn
     */
    public void setPkColumn(String pkColumn) {
        this.pkColumn = pkColumn;
    }

}
