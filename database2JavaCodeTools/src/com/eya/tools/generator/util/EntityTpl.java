package com.eya.tools.generator.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Entity信息
public class EntityTpl {
	
	private String tablename; //数据表名
	
	private String pkColumn ; 
	private String tablecomment; //数据表注释
	
	private String className; // Entity Class Name
	
	private String folderName;//className toLower
	
	private String classObjectName; //EntityClass名称的第一个字母小写
	
	private String sqlLitePackageName;//生成sqlLite 实体类的包路径
	
	private String daoPackage; // Entity对应的Dao包名
	
	private String servicePackage; // Entity对应的Service包名

	private String actionPackage; //Entity对应的Action包名
	
	private Date date = new Date(); // Java文件创建的时间

	private String mapperPackage; // Entity对应的Mapper包名

	private String packageName; // Entity对应的包名

	private List<RecordInfo> records; // Entity对应的字段信息

	private List<RecordInfo> pkRecords; // Entity对应的主键信息

	/**
	 * 获得主键
	 * @return
	 */
	public List<RecordInfo> getPkRecords() {
		if (pkRecords == null && records != null) {
			pkRecords = new ArrayList<RecordInfo>();
			for (RecordInfo r : records) {
				if (r.getPk()) {
					pkRecords.add(r);
				}
			}
		}
		return pkRecords;
	}

	public void addRecord(RecordInfo record) {
		if (records == null) {
			records = new ArrayList<RecordInfo>();
		}
		records.add(record);
	}

	// 获得系统用户信息(文件作者)
	public String getAuthor() {
		return "自动生成";//System.getProperty("user.name");
	}

	public String getClassName() {
		return className;
	}

	public String getClassObjectName() {
		if(className!=null)
		{
			char oldChar = className.charAt(0);

			char newChar = (oldChar + "").toLowerCase().charAt(0);

			classObjectName = className.replace(oldChar, newChar);
		}
		return classObjectName;
	}


	public String getDaoPackage() {
		return daoPackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public Date getDate() {
		return this.date;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public String getActionPackage() {
		return actionPackage;
	}

	public void setActionPackage(String actionPackage) {
		this.actionPackage = actionPackage;
	}

	public String getPackageName() {
		return packageName;
	}

	public List<RecordInfo> getRecords() {
		return records;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public void setMapperPackage(String mapperPackage) {
		this.mapperPackage = mapperPackage;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void setRecords(List<RecordInfo> records) {
		this.records = records;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTablecomment() {
		return tablecomment;
	}

	public void setTablecomment(String tablecomment) {
		this.tablecomment = tablecomment;
	}

	public String getFolderName() {
		return this.className.toLowerCase();
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getSqlLitePackageName() {
		return sqlLitePackageName;
	}

	public void setSqlLitePackageName(String sqlLitePackageName) {
		this.sqlLitePackageName = sqlLitePackageName;
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
