package com.dataeden;

import java.io.Serializable;

public class Bill implements Serializable{
	private String addressNum; // 收货人住宅号码或者住宅名称（使用“赟盾”产品时或 threeDSType 为 2.0必填）
	private String street; // 账单街道（使用“赟盾”产品时或 threeDSType 为 2.0必填）
	private String postalCode; // 账单邮政编码（使用“赟盾”产品时或 threeDSType 为 2.0必填）
	private String city; // 账单城市（使用“赟盾”产品时或 threeDSType 为 2.0必填）
	private String state; // 账单省份（如果 threeDSType 为 2.0 必填）。
	private String country; // 账单国家英文二字码，需要大写，详见<国家 或地区代码> （使用“赟盾”产品时或 threeDSType 为 2.0 必填）
	private String houseNumberOrName; // 门牌号
	public String getAddressNum() {
		return addressNum;
	}
	public void setAddressNum(String addressNum) {
		this.addressNum = addressNum;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHouseNumberOrName() {
		return houseNumberOrName;
	}
	public void setHouseNumberOrName(String houseNumberOrName) {
		this.houseNumberOrName = houseNumberOrName;
	}
	

}
