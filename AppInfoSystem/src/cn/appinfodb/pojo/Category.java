package cn.appinfodb.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;

public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7187009828703274172L;
	private int id;
	/**
	 * 分类编码
	 */
	private String categoryCode;
	/**
	 * 分类名称
	 */
	private String categoryName;
	/**
	 * 父级节点id
	 */
	private BigInteger parentId;
	/**
	 * 创建者
	 */
	private BigInteger createdBy;
	/**
	 * 创建时间
	 */
	private Date creationTime;
	/**
	 * 更新者
	 */
	private BigInteger modifyBy;
	/**
	 * 最新更新时间
	 */
	private Date modifyDate;
	
	public Category(){}
	public Category(int id, String categoryCode, String categoryName,
			BigInteger parentId, BigInteger createdBy, Date creationTime,
			BigInteger modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.parentId = parentId;
		this.createdBy = createdBy;
		this.creationTime = creationTime;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public BigInteger getParentId() {
		return parentId;
	}
	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}
	public BigInteger getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public BigInteger getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(BigInteger modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
