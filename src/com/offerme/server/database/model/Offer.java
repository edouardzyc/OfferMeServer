package com.offerme.server.database.model;

import java.sql.Timestamp;

/**
 * 
 * @author Edouard.Zhang
 *
 */
public class Offer {
	
	/**
	 * OFFER_ID: INT
	 */
	private int offerId;
	/**
	 * USER_ID: INT 发内推用户ID
	 */
	private int userId;
	/**
	 * TITLE: VARCHAR(50) 标题
	 */
	private String title;
	/**
	 * CONTENT: TEXT 内容
	 */
	private String content;
	/**
	 * COMPANY:VARCHAR(50) 公司
	 */
	private String company;
	/**
	 * CITY: VARCHAR(50) 地区
	 */
	private String city;
	/**
	 * COUNTRY:VARCHAR(50) 国家
	 */
	private String country;
	/**
	 * ADDRESS:VARCHAR(255) 地址
	 */
	private String address;
	/**
	 * 发布Offer的用户对象
	 */
	private User offerUser;
	/**
	 * DATE:DATE 发布日期
	 */
	private Timestamp date;
	/**
	 * STATUS:VARCHAR 状态
	 */
	private String status;
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public User getOfferUser() {
		return offerUser;
	}
	public void setOfferUser(User offerUser) {
		this.offerUser = offerUser;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	


}
