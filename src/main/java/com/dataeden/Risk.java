package com.dataeden;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import scala.reflect.internal.Trees.New;

import com.google.gson.Gson;
import com.sh.lang.utils.DateUtil;

public class Risk implements Serializable {
	private String adjustmentFactor; // 风险分数调整，可为负数。分数为整数
	private String retryNum; // 该订单在所有渠道已重复的总次数
	private Trade trade; // 贸易信息
	private Device device; // 设备指纹标识，使用 iPayLinks 设备指纹时传送
	private Cust cust; // 用户参数
	private Order order;
	private List<Goods> goods;
	private List<Buried> buried; // 埋点
	private Ship ship; // 收货地址
	private Bill bill; // 账单地址

	public String getAdjustmentFactor() {
		return adjustmentFactor;
	}

	public void setAdjustmentFactor(String adjustmentFactor) {
		this.adjustmentFactor = adjustmentFactor;
	}

	public String getRetryNum() {
		return retryNum;
	}

	public void setRetryNum(String retryNum) {
		this.retryNum = retryNum;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Cust getCust() {
		return cust;
	}

	public void setCust(Cust cust) {
		this.cust = cust;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public List<Buried> getBuried() {
		return buried;
	}

	public void setBuried(List<Buried> buried) {
		this.buried = buried;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public static void main(String[] args) {
		Risk risk = new Risk();
		risk.setRetryNum("0");
		risk.setTrade(new Trade("5999", "default"));
		Cust cust = new Cust();
		cust.setEmail("test@approve.com");
		cust.setIp("1.193.64.123");
		cust.setRegistrationTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSS));
//		cust.setPastTransactions("10");
		cust.setPhoneNum("317-465-7752");
		cust.setPhoneVerified("true");
		
//		cust.setPhoneVerifiedTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSS));
		
//		cust.setEmailVerifiedTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSS));
//		cust.setEmailVerified("true");
//		cust.setDateOfBirth("19900902");
		cust.setGender("male");
		cust.setFirstShoppingTime("20100907122344");
		cust.setRegisterUserId("10000009824");
		
		cust.setRegistrationTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSS));
		
		risk.setCust(cust);
		Order order = new Order();
		order.setSource("mobile_web");
		order.setCreateTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSS));
		order.setUpdateTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDDHHMMSS));
//		order.setPayProcessor("XXX");
		order.setTotalDiscounts("67.4");
		risk.setOrder(order);
		
		
		Goods goods = new Goods();
		goods.setId(Math.abs(UUID.randomUUID().hashCode())+"");
		goods.setTitle("The cherry trees");
		goods.setCategory("botany");
		goods.setBrand("black pearl");
		goods.setItemName("The cherry trees");
		goods.setItemPrice("2.37");
		goods.setDeliveryTimeFrame("two_or_more_days_shipping");
		goods.setDeliveryDate(DateUtil.date2String(new Date(), DateUtil.YYYYMMDD));
		goods.setUrl("www.nadula.com");
		goods.setSku("18W-WIG11-N-22");
		goods.setQuantity("10");
		goods.setIsGift("no");
		goods.setIsVirtual("no");
		
		Goods goods2 = new Goods();
		goods2.setId(Math.abs(UUID.randomUUID().hashCode())+"");
		goods2.setTitle("The apple trees");
		goods2.setCategory("botany");
		goods2.setBrand("fuji apple");
		goods2.setItemName("The apple trees");
		goods2.setItemPrice("4.37");
		goods2.setDeliveryTimeFrame("two_or_more_days_shipping");
		goods2.setDeliveryDate(DateUtil.date2String(new Date(), DateUtil.YYYYMMDD));
		goods2.setUrl("www.nadula.com");
		goods2.setSku("18W-WIG11-N-21");
		goods2.setQuantity("10");
		goods2.setIsGift("no");
		goods2.setIsVirtual("no");
		
		List<Goods> list = new ArrayList<Goods>();
		list.add(goods);
		list.add(goods2);
		risk.setGoods(list);
		// 收货地址
		Ship ship = new Ship();
//		ship.setPrice("25");
//		ship.setTitle("物流运输");
//		ship.setAddressIndicator("ship_to_billing_address");
		ship.setPhoneNo("317-465-7752");
		ship.setFirstName("Katherine");
		ship.setLastName("McCane");
		ship.setStreet("1593 Clay Street");
		ship.setPostalCode("46220");
		ship.setCity("Indianapolis");
		ship.setState("Indiana");
		ship.setCountry("US");
//		ship.setAddressLastModifyTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDD));
//		ship.setPhoneLastModifyTime(DateUtil.date2String(new Date(), DateUtil.YYYYMMDD));
		
		risk.setShip(ship);
		
		Bill bill = new Bill();
//		bill.setAddressNum("浦东新区265号");
		bill.setStreet("1593 Clay Street");
		bill.setPostalCode("46220");
		bill.setCity("Indianapolis");
		bill.setState("Indiana");
		bill.setCountry("US");
//		bill.setHouseNumberOrName("18F502");
		risk.setBill(bill);
		
		System.out.println(new Gson().toJson(risk));
	}

}
