package com.dataeden;

import java.io.Serializable;

public class Cust implements Serializable {
	private String registrationTime; // 用户的注册时间，需折算为 UTC
										// 时间，yyyyMMddHHmmss，eg:20170607125959（使用“赟盾”产品时必填）
	private String pastTransactions; // 该会员/交易卡号，在商户端所有交易次数(包含成功、失败)（使用“赟盾”产品时必填）
	private String phoneNum; // 用户移动电话号码，不包含国际代码；（使用“赟盾”产品时必填）
	private String phoneVerified; // true:手机号码已验证;false:手机号码未验证;（使用“赟盾”产品时必填）
	private String phoneVerifiedTime; // 手机验证时间（使用“赟盾”产品时必填）
	private String emailVerified; // true:邮箱已验证;false:邮箱未验证;（使用“赟盾”产品时必填）
	private String emailVerifiedTime; // 邮箱验证时间（使用“赟盾”产品时必填）
	private String dateOfBirth; // 用户的生日，yyyyMMdd，eg:20170607（使用“赟盾”产品时必填）
	private String gender; // 用户性别：male: 男性；emale: 女性；（使用“赟盾”产品时必填）
	private String firstShoppingTime; // 首次消费时间，yyyyMMddHHmmss，eg:20170607125959（使用“赟盾”产品时必填）
	private String registerUserId; // 用户在商户的会员 id（使用“赟盾”产品时必填）
	private String ip; // 用户的下单地址，支持 IPv4 格式和 IPv6 格式（使用“赟盾”产品时必填）
	private String email; // 用户的联系邮箱（使用“赟盾”产品时或 threeDSType 为 2.0必填）
	private String level; // 用户标识，数值越大会员等级越高：一级会员：1;一级会员：2;三级会员：3；...九级会员：9；
	private String lastShoppingTime; // 上次消费时间，yyyyMMddHHmmss，ege:20170607125959

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLastShoppingTime() {
		return lastShoppingTime;
	}

	public void setLastShoppingTime(String lastShoppingTime) {
		this.lastShoppingTime = lastShoppingTime;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getPastTransactions() {
		return pastTransactions;
	}

	public void setPastTransactions(String pastTransactions) {
		this.pastTransactions = pastTransactions;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(String phoneVerified) {
		this.phoneVerified = phoneVerified;
	}

	public String getPhoneVerifiedTime() {
		return phoneVerifiedTime;
	}

	public void setPhoneVerifiedTime(String phoneVerifiedTime) {
		this.phoneVerifiedTime = phoneVerifiedTime;
	}

	public String getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(String emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getEmailVerifiedTime() {
		return emailVerifiedTime;
	}

	public void setEmailVerifiedTime(String emailVerifiedTime) {
		this.emailVerifiedTime = emailVerifiedTime;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstShoppingTime() {
		return firstShoppingTime;
	}

	public void setFirstShoppingTime(String firstShoppingTime) {
		this.firstShoppingTime = firstShoppingTime;
	}

	public String getRegisterUserId() {
		return registerUserId;
	}

	public void setRegisterUserId(String registerUserId) {
		this.registerUserId = registerUserId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
