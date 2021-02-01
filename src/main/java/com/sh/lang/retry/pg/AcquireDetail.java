package com.sh.lang.retry.pg;

import java.io.Serializable;
import java.util.Date;

public class AcquireDetail implements Serializable{
	
	private String eventid;
	private String merchantid;
	private String accesstype;
	private String transid;
	private String acsorderid;
	private String tradetype;
	private String siteid;
	private String registeruserid;
	private String transchannel;
	private String paymethod;
	private String terminaltype;
	private String origpaytype;
	private String origtransid;
	private String transtimeout;
	private String currencycode;
	private String currencyamount;
	private String amount;
	private String goodsname;
	private String goodsdesc;
	private String billaddress;
	private String billcity;
	private String billcountrycode;
	private String billemail;
	private String billfirstname;
	private String billlastname;
	private String billphonecity;
	private String billphonenumber;
	private String billpostalcode;
	private String billstate;
	private String cardemail;
	private String cardfirstname;
	private String cardlastname;
	private String cardno;
	private String cardnocountryname;
	private String cardphonenumber;
	private String cashierip;
	private String cashieripcountry;
	private String customerip;
	private String customeripcountry;
	private String fingerprintid;
	private String risklevel;
	private String shippingaddress;
	private String shippingcity;
	private String shippingcountrycode;
	private String shippingemail;
	private String shippingfirstname;
	private String shippinglastname;
	private String shippingphonenumber;
	private String shippingpostalcode;
	private String shippingstate;
	private String submittime;
	private String xddid;
	private String origacsorderid;
	private String cbkcode;
	private String cbkmsg;
	private String createtime;
	private String dealstatus;
	private String value2;
	private String decision;
	private String riskcode;
	private String riskmsg;
	private String cardorg;
	private String cardnocountrynamea2;
	private String riskruleid;
	private String riskruletype;
	private String shippingname;
	private String orderrespcode;
	private String orderrespmsg;
	private String orderstatus;
	private String orgcode;
	private String completetime;
	private String updatetime;
	private String riskassessmentonly;
	private String compensation;
	private String decisiondetail;
	private String maskcardno;
	private String repolabelmonitorblack;
	private String repolabelmonitorwhite;
	private String custlevel;
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public String getAccesstype() {
		return accesstype;
	}
	public void setAccesstype(String accesstype) {
		this.accesstype = accesstype;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getAcsorderid() {
		return acsorderid;
	}
	public void setAcsorderid(String acsorderid) {
		this.acsorderid = acsorderid;
	}
	public String getTradetype() {
		return tradetype;
	}
	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}
	public String getSiteid() {
		return siteid;
	}
	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}
	public String getRegisteruserid() {
		return registeruserid;
	}
	public void setRegisteruserid(String registeruserid) {
		this.registeruserid = registeruserid;
	}
	public String getTranschannel() {
		return transchannel;
	}
	public void setTranschannel(String transchannel) {
		this.transchannel = transchannel;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getTerminaltype() {
		return terminaltype;
	}
	public void setTerminaltype(String terminaltype) {
		this.terminaltype = terminaltype;
	}
	public String getOrigpaytype() {
		return origpaytype;
	}
	public void setOrigpaytype(String origpaytype) {
		this.origpaytype = origpaytype;
	}
	public String getOrigtransid() {
		return origtransid;
	}
	public void setOrigtransid(String origtransid) {
		this.origtransid = origtransid;
	}
	public String getTranstimeout() {
		return transtimeout;
	}
	public void setTranstimeout(String transtimeout) {
		this.transtimeout = transtimeout;
	}
	public String getCurrencycode() {
		return currencycode;
	}
	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}
	public String getCurrencyamount() {
		return currencyamount;
	}
	public void setCurrencyamount(String currencyamount) {
		this.currencyamount = currencyamount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getGoodsdesc() {
		return goodsdesc;
	}
	public void setGoodsdesc(String goodsdesc) {
		this.goodsdesc = goodsdesc;
	}
	public String getBilladdress() {
		return billaddress;
	}
	public void setBilladdress(String billaddress) {
		this.billaddress = billaddress;
	}
	public String getBillcity() {
		return billcity;
	}
	public void setBillcity(String billcity) {
		this.billcity = billcity;
	}
	public String getBillcountrycode() {
		return billcountrycode;
	}
	public void setBillcountrycode(String billcountrycode) {
		this.billcountrycode = billcountrycode;
	}
	public String getBillemail() {
		return billemail;
	}
	public void setBillemail(String billemail) {
		this.billemail = billemail;
	}
	public String getBillfirstname() {
		return billfirstname;
	}
	public void setBillfirstname(String billfirstname) {
		this.billfirstname = billfirstname;
	}
	public String getBilllastname() {
		return billlastname;
	}
	public void setBilllastname(String billlastname) {
		this.billlastname = billlastname;
	}
	public String getBillphonecity() {
		return billphonecity;
	}
	public void setBillphonecity(String billphonecity) {
		this.billphonecity = billphonecity;
	}
	public String getBillphonenumber() {
		return billphonenumber;
	}
	public void setBillphonenumber(String billphonenumber) {
		this.billphonenumber = billphonenumber;
	}
	public String getBillpostalcode() {
		return billpostalcode;
	}
	public void setBillpostalcode(String billpostalcode) {
		this.billpostalcode = billpostalcode;
	}
	public String getBillstate() {
		return billstate;
	}
	public void setBillstate(String billstate) {
		this.billstate = billstate;
	}
	public String getCardemail() {
		return cardemail;
	}
	public void setCardemail(String cardemail) {
		this.cardemail = cardemail;
	}
	public String getCardfirstname() {
		return cardfirstname;
	}
	public void setCardfirstname(String cardfirstname) {
		this.cardfirstname = cardfirstname;
	}
	public String getCardlastname() {
		return cardlastname;
	}
	public void setCardlastname(String cardlastname) {
		this.cardlastname = cardlastname;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCardnocountryname() {
		return cardnocountryname;
	}
	public void setCardnocountryname(String cardnocountryname) {
		this.cardnocountryname = cardnocountryname;
	}
	public String getCardphonenumber() {
		return cardphonenumber;
	}
	public void setCardphonenumber(String cardphonenumber) {
		this.cardphonenumber = cardphonenumber;
	}
	public String getCashierip() {
		return cashierip;
	}
	public void setCashierip(String cashierip) {
		this.cashierip = cashierip;
	}
	public String getCashieripcountry() {
		return cashieripcountry;
	}
	public void setCashieripcountry(String cashieripcountry) {
		this.cashieripcountry = cashieripcountry;
	}
	public String getCustomerip() {
		return customerip;
	}
	public void setCustomerip(String customerip) {
		this.customerip = customerip;
	}
	public String getCustomeripcountry() {
		return customeripcountry;
	}
	public void setCustomeripcountry(String customeripcountry) {
		this.customeripcountry = customeripcountry;
	}
	public String getFingerprintid() {
		return fingerprintid;
	}
	public void setFingerprintid(String fingerprintid) {
		this.fingerprintid = fingerprintid;
	}
	public String getRisklevel() {
		return risklevel;
	}
	public void setRisklevel(String risklevel) {
		this.risklevel = risklevel;
	}
	public String getShippingaddress() {
		return shippingaddress;
	}
	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}
	public String getShippingcity() {
		return shippingcity;
	}
	public void setShippingcity(String shippingcity) {
		this.shippingcity = shippingcity;
	}
	public String getShippingcountrycode() {
		return shippingcountrycode;
	}
	public void setShippingcountrycode(String shippingcountrycode) {
		this.shippingcountrycode = shippingcountrycode;
	}
	public String getShippingemail() {
		return shippingemail;
	}
	public void setShippingemail(String shippingemail) {
		this.shippingemail = shippingemail;
	}
	public String getShippingfirstname() {
		return shippingfirstname;
	}
	public void setShippingfirstname(String shippingfirstname) {
		this.shippingfirstname = shippingfirstname;
	}
	public String getShippinglastname() {
		return shippinglastname;
	}
	public void setShippinglastname(String shippinglastname) {
		this.shippinglastname = shippinglastname;
	}
	public String getShippingphonenumber() {
		return shippingphonenumber;
	}
	public void setShippingphonenumber(String shippingphonenumber) {
		this.shippingphonenumber = shippingphonenumber;
	}
	public String getShippingpostalcode() {
		return shippingpostalcode;
	}
	public void setShippingpostalcode(String shippingpostalcode) {
		this.shippingpostalcode = shippingpostalcode;
	}
	public String getShippingstate() {
		return shippingstate;
	}
	public void setShippingstate(String shippingstate) {
		this.shippingstate = shippingstate;
	}
	public String getSubmittime() {
		return submittime;
	}
	public void setSubmittime(String submittime) {
		this.submittime = submittime;
	}
	public String getXddid() {
		return xddid;
	}
	public void setXddid(String xddid) {
		this.xddid = xddid;
	}
	public String getOrigacsorderid() {
		return origacsorderid;
	}
	public void setOrigacsorderid(String origacsorderid) {
		this.origacsorderid = origacsorderid;
	}
	public String getCbkcode() {
		return cbkcode;
	}
	public void setCbkcode(String cbkcode) {
		this.cbkcode = cbkcode;
	}
	public String getCbkmsg() {
		return cbkmsg;
	}
	public void setCbkmsg(String cbkmsg) {
		this.cbkmsg = cbkmsg;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getDealstatus() {
		return dealstatus;
	}
	public void setDealstatus(String dealstatus) {
		this.dealstatus = dealstatus;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	public String getRiskcode() {
		return riskcode;
	}
	public void setRiskcode(String riskcode) {
		this.riskcode = riskcode;
	}
	public String getRiskmsg() {
		return riskmsg;
	}
	public void setRiskmsg(String riskmsg) {
		this.riskmsg = riskmsg;
	}
	public String getCardorg() {
		return cardorg;
	}
	public void setCardorg(String cardorg) {
		this.cardorg = cardorg;
	}
	public String getCardnocountrynamea2() {
		return cardnocountrynamea2;
	}
	public void setCardnocountrynamea2(String cardnocountrynamea2) {
		this.cardnocountrynamea2 = cardnocountrynamea2;
	}
	public String getRiskruleid() {
		return riskruleid;
	}
	public void setRiskruleid(String riskruleid) {
		this.riskruleid = riskruleid;
	}
	public String getRiskruletype() {
		return riskruletype;
	}
	public void setRiskruletype(String riskruletype) {
		this.riskruletype = riskruletype;
	}
	public String getShippingname() {
		return shippingname;
	}
	public void setShippingname(String shippingname) {
		this.shippingname = shippingname;
	}
	public String getOrderrespcode() {
		return orderrespcode;
	}
	public void setOrderrespcode(String orderrespcode) {
		this.orderrespcode = orderrespcode;
	}
	public String getOrderrespmsg() {
		return orderrespmsg;
	}
	public void setOrderrespmsg(String orderrespmsg) {
		this.orderrespmsg = orderrespmsg;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getCompletetime() {
		return completetime;
	}
	public void setCompletetime(String completetime) {
		this.completetime = completetime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getRiskassessmentonly() {
		return riskassessmentonly;
	}
	public void setRiskassessmentonly(String riskassessmentonly) {
		this.riskassessmentonly = riskassessmentonly;
	}
	public String getCompensation() {
		return compensation;
	}
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}
	public String getDecisiondetail() {
		return decisiondetail;
	}
	public void setDecisiondetail(String decisiondetail) {
		this.decisiondetail = decisiondetail;
	}
	public String getMaskcardno() {
		return maskcardno;
	}
	public void setMaskcardno(String maskcardno) {
		this.maskcardno = maskcardno;
	}
	public String getRepolabelmonitorblack() {
		return repolabelmonitorblack;
	}
	public void setRepolabelmonitorblack(String repolabelmonitorblack) {
		this.repolabelmonitorblack = repolabelmonitorblack;
	}
	public String getRepolabelmonitorwhite() {
		return repolabelmonitorwhite;
	}
	public void setRepolabelmonitorwhite(String repolabelmonitorwhite) {
		this.repolabelmonitorwhite = repolabelmonitorwhite;
	}
	public String getCustlevel() {
		return custlevel;
	}
	public void setCustlevel(String custlevel) {
		this.custlevel = custlevel;
	}
	
	
	

}
