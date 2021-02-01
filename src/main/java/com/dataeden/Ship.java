package com.dataeden;

import java.io.Serializable;

public class Ship implements Serializable{
	private String price; // 运输费用（使用“赟盾”产品时必填）
	private String title; // 运输方式（使用“赟盾”产品时必填）
	private String addressIndicator; // 收货地址标识： ship_to_billing_address:发货到账单地址； ship_to_verified_address:发货到一个验证过的 地址； ship_to_new_address:发货到一个全新的地址， 和账单地址不符； ship_to_store: “送货到店”/在当地商店取货（商 店地址填写在送货地址栏中） digital_goods:数码商品，（包括在线服务，电 子礼品卡和兑换码）； goods_not_shipped:旅游及活动门票等无需邮 寄的商品; other:其他（例如，游戏，未发运的数字服务， emedia 订阅等） （使用“赟盾”产品时必填）
	private String phoneNo; // 收货联系电话（使用“赟盾”产品时必填）
	private String firstName; // 收货人名	（使用“赟盾”产品时必填）
	private String lastName; // 收货人姓	（使用“赟盾”产品时必填）
	private String street; // 收货人街道 （使用“赟盾”产品时或 threeDSType 为 2.0 必填）
	private String postalCode; // 收货人邮政编码 （使用“赟盾”产品时或 threeDSType 为 2.0 必填）
	private String city; // 收货人城市（使用“赟盾”产品时或 threeDSType 为 2.0必填）
	private String state; // 收货人省份（使用“赟盾”产品时或 threeDSType 为 2.0必填）
	private String country; // 收货人国家英文二字码，需要大写，详见<国家或地区代码>（如果 threeDSType 为 2.0 必填）。
	private String addressLastModifyTime; // 收货地址最后修改时间,yyyyMMddHHmmss，eg:20170607125959
	private String phoneLastModifyTime; // 收货联系电话最后修改时间,yyyyMMddHHmmss，eg:20170607125959
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddressIndicator() {
		return addressIndicator;
	}
	public void setAddressIndicator(String addressIndicator) {
		this.addressIndicator = addressIndicator;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getAddressLastModifyTime() {
		return addressLastModifyTime;
	}
	public void setAddressLastModifyTime(String addressLastModifyTime) {
		this.addressLastModifyTime = addressLastModifyTime;
	}
	public String getPhoneLastModifyTime() {
		return phoneLastModifyTime;
	}
	public void setPhoneLastModifyTime(String phoneLastModifyTime) {
		this.phoneLastModifyTime = phoneLastModifyTime;
	}
	
}
