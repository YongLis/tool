package com.dataeden;

import java.io.Serializable;

public class Goods implements Serializable{
	private String  id; // 单项商品 ID（使用“赟盾”产品时必填）
	private String  title; // 单项商品标签（使用“赟盾”产品时必填）
	private String  category; // 单项商品类型（使用“赟盾”产品时必填）
	private String  subCategory; // 商品二级类别
	private String  thirdCategory; // 商品三级类别
	private String  brand; // 单项商品品牌（使用“赟盾”产品时必填）
	private String  itemName; // 单项商品名称	（使用“赟盾”产品时必填）
	private String  itemPrice; // 商品平均单价，符合 ISO 4217，详见附录<金额 处理与货币代码> （使用“赟盾”产品时必填）
	private String  deliveryTimeFrame; // 指定商品的交付时间范围： electronic_delivery:电子交付 same_day_shipping:当天发货； overnight_shipping:隔夜送货； two_or_more_days_shipping:两天或更长时间的 运输； （使用“赟盾”产品时必填）
	
	private String  deliveryDate; // 商品发货日期，yyyyMMdd， eg:20170607 （使用“赟盾”产品时必填）
	private String  url; // 商品 URL 地址（使用“赟盾”产品时必填）
	private String  sku; // 产品 sku（使用“赟盾”产品时必填）
	private String  quantity; // 商品数量 （使用“赟盾”产品时必填）
	private String  isGift; // 是礼品：yes 不是礼品：no/或者不传值
	private String  isVirtual; // 是虚拟商品：yes 不是虚拟商品：no/或者不传值
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getThirdCategory() {
		return thirdCategory;
	}
	public void setThirdCategory(String thirdCategory) {
		this.thirdCategory = thirdCategory;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getDeliveryTimeFrame() {
		return deliveryTimeFrame;
	}
	public void setDeliveryTimeFrame(String deliveryTimeFrame) {
		this.deliveryTimeFrame = deliveryTimeFrame;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getIsGift() {
		return isGift;
	}
	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}
	public String getIsVirtual() {
		return isVirtual;
	}
	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}


}
