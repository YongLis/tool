package com.dataeden;

import java.io.Serializable;

public class Order implements Serializable {
	private String source; // 订单来源： •desktop_web:PC web； •mobile_web:手机 WEB;
							// •mobile_app:移动手机 APP； •web:来自于 web,但不能区分终端类型；
							// •chat:来源于社交网络； •third_party:来源于第三方； •phone:电话预定；
							// •shopify:shopify; •other:其他来源; （使用“赟盾”产品时必填）
	private String createTime; // 订单创建时间，yyyyMMddHHmmss，eg:20170607125959（使用“赟盾”产品时必填）
	private String updateTime; // 订单最后修改时间，yyyyMMddHHmmss， eg:20170607125959
								// （使用“赟盾”产品时必填）
	private String payProcessor; // 处理商户交易的第三方支付公司/银行的名称 （使用“赟盾”产品时必填）
	private String totalDiscounts; // 订单折扣总金额，金额需精确到最小金额单位， 详见附录<金额处理与货币代码>
									// （使用“赟盾”产品时必填）
	private String discounts; // 订单折扣明细，金额需精确到最小金额单位， 详见附录<金额处理与货币代码> 多个折扣时
								// "discounts":[{"amount":"30","code":"123"},
								// {"amount":"50","code":"234"}] （使用“赟盾”产品时必填）

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getPayProcessor() {
		return payProcessor;
	}

	public void setPayProcessor(String payProcessor) {
		this.payProcessor = payProcessor;
	}

	public String getTotalDiscounts() {
		return totalDiscounts;
	}

	public void setTotalDiscounts(String totalDiscounts) {
		this.totalDiscounts = totalDiscounts;
	}

	public String getDiscounts() {
		return discounts;
	}

	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}

}
