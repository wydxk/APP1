package cn.appinfodb.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1579115687601929546L;
	private int id;
	private BigInteger appId;
	private String adPicPath;
	private BigInteger adPV;
	private int carouselPosition;
	private Date startTime;
	private Date endTime;
	private BigInteger createdBy;
	private Date creationDate;
	private BigInteger modifyBy;
	private Date modifyDate;
	
	public Promotion(){}
	public Promotion(int id, BigInteger appId, String adPicPath,
			BigInteger adPV, int carouselPosition, Date startTime,
			Date endTime, BigInteger createdBy, Date creationDate,
			BigInteger modifyBy, Date modifyDate) {
		super();
		this.id = id;
		this.appId = appId;
		this.adPicPath = adPicPath;
		this.adPV = adPV;
		this.carouselPosition = carouselPosition;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigInteger getAppId() {
		return appId;
	}
	public void setAppId(BigInteger appId) {
		this.appId = appId;
	}
	public String getAdPicPath() {
		return adPicPath;
	}
	public void setAdPicPath(String adPicPath) {
		this.adPicPath = adPicPath;
	}
	public BigInteger getAdPV() {
		return adPV;
	}
	public void setAdPV(BigInteger adPV) {
		this.adPV = adPV;
	}
	public int getCarouselPosition() {
		return carouselPosition;
	}
	public void setCarouselPosition(int carouselPosition) {
		this.carouselPosition = carouselPosition;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public BigInteger getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(BigInteger createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
