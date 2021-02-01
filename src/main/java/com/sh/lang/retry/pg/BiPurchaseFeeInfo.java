package com.sh.lang.retry.pg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BiPurchaseFeeInfo implements Serializable{
	public String MerchantOrderId;
	public String AcsOrderId;
	public String MerchantId;
	public String OrderCurrency;
	public BigDecimal OrderAmount;
	public Date GmtCreateTime;
	public Date GmtCompleteTime;
	public String SettlementCurrency;
	public String SettlementCycle;
	public BigDecimal MarginRatio;
	public String MarginSettlementCycle;
	public BigDecimal SettlementRate;
	public BigDecimal FeePercentage;
	public BigDecimal FixedFee;
	public String FixedFeeCurrency;
	public BigDecimal FixedFeeRate;
	public BigDecimal SettlementAmount;
	public BigDecimal TradeFee;
	public BigDecimal SingleTradeFee;
	public BigDecimal RecordedAmount;
	public Date TradeCompleteTime;
	public Date SettlementDate;
	public BigDecimal TradeDateMarginFee;
	public BigDecimal SettlementDateMarginFee;
	public Date MarginFeeCycleEndDate;
	public BigDecimal BackMarginFee;
	public BigDecimal Amt101005;
	public BigDecimal Amt101006;
	public BigDecimal Amt101007;
	public BigDecimal MarginAmtTradeDay;
	public BigDecimal MarginAmtSettDay;
	public BigDecimal RestituteMarginAmt;
	public String OrgCode;
	public String OuterStatus;
	public String OrderType;
	public String ChannelCode;
	public String TransactionMode;
	public String Region;
	public String Issuer;
	public String CardOrg;
	public String CountryCode3;
	public String ChargeType;
	public String CardBin;
	public String getMerchantOrderId() {
		return MerchantOrderId;
	}
	public void setMerchantOrderId(String merchantOrderId) {
		MerchantOrderId = merchantOrderId;
	}
	public String getAcsOrderId() {
		return AcsOrderId;
	}
	public void setAcsOrderId(String acsOrderId) {
		AcsOrderId = acsOrderId;
	}
	public String getMerchantId() {
		return MerchantId;
	}
	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}
	public String getOrderCurrency() {
		return OrderCurrency;
	}
	public void setOrderCurrency(String orderCurrency) {
		OrderCurrency = orderCurrency;
	}
	public BigDecimal getOrderAmount() {
		return OrderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		OrderAmount = orderAmount;
	}
	public Date getGmtCreateTime() {
		return GmtCreateTime;
	}
	public void setGmtCreateTime(Date gmtCreateTime) {
		GmtCreateTime = gmtCreateTime;
	}
	public Date getGmtCompleteTime() {
		return GmtCompleteTime;
	}
	public void setGmtCompleteTime(Date gmtCompleteTime) {
		GmtCompleteTime = gmtCompleteTime;
	}
	public String getSettlementCurrency() {
		return SettlementCurrency;
	}
	public void setSettlementCurrency(String settlementCurrency) {
		SettlementCurrency = settlementCurrency;
	}
	public String getSettlementCycle() {
		return SettlementCycle;
	}
	public void setSettlementCycle(String settlementCycle) {
		SettlementCycle = settlementCycle;
	}
	public BigDecimal getMarginRatio() {
		return MarginRatio;
	}
	public void setMarginRatio(BigDecimal marginRatio) {
		MarginRatio = marginRatio;
	}
	public String getMarginSettlementCycle() {
		return MarginSettlementCycle;
	}
	public void setMarginSettlementCycle(String marginSettlementCycle) {
		MarginSettlementCycle = marginSettlementCycle;
	}
	public BigDecimal getSettlementRate() {
		return SettlementRate;
	}
	public void setSettlementRate(BigDecimal settlementRate) {
		SettlementRate = settlementRate;
	}
	public BigDecimal getFeePercentage() {
		return FeePercentage;
	}
	public void setFeePercentage(BigDecimal feePercentage) {
		FeePercentage = feePercentage;
	}
	public BigDecimal getFixedFee() {
		return FixedFee;
	}
	public void setFixedFee(BigDecimal fixedFee) {
		FixedFee = fixedFee;
	}
	public String getFixedFeeCurrency() {
		return FixedFeeCurrency;
	}
	public void setFixedFeeCurrency(String fixedFeeCurrency) {
		FixedFeeCurrency = fixedFeeCurrency;
	}
	public BigDecimal getFixedFeeRate() {
		return FixedFeeRate;
	}
	public void setFixedFeeRate(BigDecimal fixedFeeRate) {
		FixedFeeRate = fixedFeeRate;
	}
	public BigDecimal getSettlementAmount() {
		return SettlementAmount;
	}
	public void setSettlementAmount(BigDecimal settlementAmount) {
		SettlementAmount = settlementAmount;
	}
	public BigDecimal getTradeFee() {
		return TradeFee;
	}
	public void setTradeFee(BigDecimal tradeFee) {
		TradeFee = tradeFee;
	}
	public BigDecimal getSingleTradeFee() {
		return SingleTradeFee;
	}
	public void setSingleTradeFee(BigDecimal singleTradeFee) {
		SingleTradeFee = singleTradeFee;
	}
	public BigDecimal getRecordedAmount() {
		return RecordedAmount;
	}
	public void setRecordedAmount(BigDecimal recordedAmount) {
		RecordedAmount = recordedAmount;
	}
	public Date getTradeCompleteTime() {
		return TradeCompleteTime;
	}
	public void setTradeCompleteTime(Date tradeCompleteTime) {
		TradeCompleteTime = tradeCompleteTime;
	}
	public Date getSettlementDate() {
		return SettlementDate;
	}
	public void setSettlementDate(Date settlementDate) {
		SettlementDate = settlementDate;
	}
	public BigDecimal getTradeDateMarginFee() {
		return TradeDateMarginFee;
	}
	public void setTradeDateMarginFee(BigDecimal tradeDateMarginFee) {
		TradeDateMarginFee = tradeDateMarginFee;
	}
	public BigDecimal getSettlementDateMarginFee() {
		return SettlementDateMarginFee;
	}
	public void setSettlementDateMarginFee(BigDecimal settlementDateMarginFee) {
		SettlementDateMarginFee = settlementDateMarginFee;
	}
	public Date getMarginFeeCycleEndDate() {
		return MarginFeeCycleEndDate;
	}
	public void setMarginFeeCycleEndDate(Date marginFeeCycleEndDate) {
		MarginFeeCycleEndDate = marginFeeCycleEndDate;
	}
	public BigDecimal getBackMarginFee() {
		return BackMarginFee;
	}
	public void setBackMarginFee(BigDecimal backMarginFee) {
		BackMarginFee = backMarginFee;
	}
	public BigDecimal getAmt101005() {
		return Amt101005;
	}
	public void setAmt101005(BigDecimal amt101005) {
		Amt101005 = amt101005;
	}
	public BigDecimal getAmt101006() {
		return Amt101006;
	}
	public void setAmt101006(BigDecimal amt101006) {
		Amt101006 = amt101006;
	}
	public BigDecimal getAmt101007() {
		return Amt101007;
	}
	public void setAmt101007(BigDecimal amt101007) {
		Amt101007 = amt101007;
	}
	public BigDecimal getMarginAmtTradeDay() {
		return MarginAmtTradeDay;
	}
	public void setMarginAmtTradeDay(BigDecimal marginAmtTradeDay) {
		MarginAmtTradeDay = marginAmtTradeDay;
	}
	public BigDecimal getMarginAmtSettDay() {
		return MarginAmtSettDay;
	}
	public void setMarginAmtSettDay(BigDecimal marginAmtSettDay) {
		MarginAmtSettDay = marginAmtSettDay;
	}
	public BigDecimal getRestituteMarginAmt() {
		return RestituteMarginAmt;
	}
	public void setRestituteMarginAmt(BigDecimal restituteMarginAmt) {
		RestituteMarginAmt = restituteMarginAmt;
	}
	public String getOrgCode() {
		return OrgCode;
	}
	public void setOrgCode(String orgCode) {
		OrgCode = orgCode;
	}
	public String getOuterStatus() {
		return OuterStatus;
	}
	public void setOuterStatus(String outerStatus) {
		OuterStatus = outerStatus;
	}
	public String getOrderType() {
		return OrderType;
	}
	public void setOrderType(String orderType) {
		OrderType = orderType;
	}
	public String getChannelCode() {
		return ChannelCode;
	}
	public void setChannelCode(String channelCode) {
		ChannelCode = channelCode;
	}
	public String getTransactionMode() {
		return TransactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		TransactionMode = transactionMode;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getIssuer() {
		return Issuer;
	}
	public void setIssuer(String issuer) {
		Issuer = issuer;
	}
	public String getCardOrg() {
		return CardOrg;
	}
	public void setCardOrg(String cardOrg) {
		CardOrg = cardOrg;
	}
	public String getCountryCode3() {
		return CountryCode3;
	}
	public void setCountryCode3(String countryCode3) {
		CountryCode3 = countryCode3;
	}
	public String getChargeType() {
		return ChargeType;
	}
	public void setChargeType(String chargeType) {
		ChargeType = chargeType;
	}
	public String getCardBin() {
		return CardBin;
	}
	public void setCardBin(String cardBin) {
		CardBin = cardBin;
	}


}
