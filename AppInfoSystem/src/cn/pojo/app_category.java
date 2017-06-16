package cn.pojo;

import java.util.Date;

public class app_category {
	private int id;					//主键id
	private String xcategoryCode;	//分类编码
	private String categoryName;	//分类名称
	private int parentId;			//父节点ID
	private int createdBy;			//创建者
	private Date creationTime;		//创建时间
	private int modifyBy;			//更新者
	private Date modifyDate;		//最新更新时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getXcategoryCode() {
		return xcategoryCode;
	}
	public void setXcategoryCode(String xcategoryCode) {
		this.xcategoryCode = xcategoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
