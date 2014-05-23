package com.offerme.server.database.model;

import java.sql.Timestamp;


/**
 * 用户
 * @author Edouard.Zhang
 *
 */
public class User {
	
	public User()
	{
		userId = -1;
		name = "";
		nameFamily = "";
		city = "";
		country ="";
		company ="";
		nickName= "";
		email ="";
		phone ="";
		psw ="";
		portrait = null;
		signUpDate = new Timestamp(System.currentTimeMillis());
		signInTime =1;
		signLastDate= new Timestamp(System.currentTimeMillis());
		isEnable = 1;
	}
	
	/**
	 * USER_ID:INT
	 */
	private int userId;
	/**
	 * NAME:VARCHAR(20) 名
	 */
	private String name;
	/**
	 * NAME_FAMILY:VARCHAR(20) 姓
	 */
	private String nameFamily;
	/**
	 * CITY:VARCHAR(50) 城市
	 */
	private String city;
	/**
	 * COUNTRY:VARCHAR(50) 国家
	 */
	private String country;
	/**
	 * COMPANY:VARCHAR(50) 公司
	 */
	private String company;
	/**
	 * NICKNAME:VARCHAR(50) 昵称/用户名
	 */
	private String nickName;
	/**
	 * EMAIL:VARCHAR(50) 邮箱
	 */
	private String email;
	/**
	 * PHONE:VARCHAR(20) 手机
	 */
	private String phone;
	/**
	 * PSW:VARCHAR(32) 密码
	 */
	private String psw;
	/**
	 * PORTRAIT:BLOB 头像
	 */
	private byte[] portrait;
	/**
	 * SIGNUPDATE:DATETIME 注册时间
	 */
	private Timestamp signUpDate;
	/**
	 * SIGNINTIME:INT 登录次数
	 */
	private int signInTime;
	/**
	 * SIGNLASTDATE:DATE 上次登录时间
	 */
	private Timestamp signLastDate;
	/**
	 * ISENABLE: INT 是否有效
	 */
	private int isEnable;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameFamily() {
		return nameFamily;
	}
	public void setNameFamily(String nameFamily) {
		this.nameFamily = nameFamily;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public byte[] getPortrait() {
		return portrait;
	}
	public void setPortrait(byte[] portrait) {
		this.portrait = portrait;
	}
	public Timestamp getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(Timestamp signUpDate) {
		this.signUpDate = signUpDate;
	}
	public int getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(int signInTime) {
		this.signInTime = signInTime;
	}
	public Timestamp getSignLastDate() {
		return signLastDate;
	}
	public void setSignLastDate(Timestamp signLastDate) {
		this.signLastDate = signLastDate;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
