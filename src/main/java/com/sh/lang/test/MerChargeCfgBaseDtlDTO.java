package com.sh.lang.test;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class MerChargeCfgBaseDtlDTO implements Serializable {

    private String id;
    private String baseId;
    private String region;
    private String issueCurrency;
    private String countryCode;
    private String cardOrg;
    private String transactionMode;
    private String channelCode;
    private String subChannelCode;
    private List<Map<String,String>> subChannelCodeMap;
    private BigDecimal fixedFeeAmt;
    private String fixedFeeCurrency;
    private BigDecimal percent;
    private BigDecimal pctMinFee;
    private String pctFeeCurrency;
    private int priority;
    private int index;
    //阶梯收费描述
    private String conditionDesc;
    /**
     * 收费依据 01-正向交易金额
     */
    private String billingBasis;

    /**
     * 计费周期-月度-M，季度-Q
     */
    private String billingCycle;

    /**
     * 计费币种
     */
    private String billingCurrency;
    /**
     * 计费币种的内容
     */
    private String conditionListStr;
    //阶梯收费的明细
    List<MerChargeLadderChargeDTO> merChargeLadderChargeDTOList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getIssueCurrency() {
		return issueCurrency;
	}
	public void setIssueCurrency(String issueCurrency) {
		this.issueCurrency = issueCurrency;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCardOrg() {
		return cardOrg;
	}
	public void setCardOrg(String cardOrg) {
		this.cardOrg = cardOrg;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getSubChannelCode() {
		return subChannelCode;
	}
	public void setSubChannelCode(String subChannelCode) {
		this.subChannelCode = subChannelCode;
	}
	public List<Map<String, String>> getSubChannelCodeMap() {
		return subChannelCodeMap;
	}
	public void setSubChannelCodeMap(List<Map<String, String>> subChannelCodeMap) {
		this.subChannelCodeMap = subChannelCodeMap;
	}
	public BigDecimal getFixedFeeAmt() {
		return fixedFeeAmt;
	}
	public void setFixedFeeAmt(BigDecimal fixedFeeAmt) {
		this.fixedFeeAmt = fixedFeeAmt;
	}
	public String getFixedFeeCurrency() {
		return fixedFeeCurrency;
	}
	public void setFixedFeeCurrency(String fixedFeeCurrency) {
		this.fixedFeeCurrency = fixedFeeCurrency;
	}
	public BigDecimal getPercent() {
		return percent;
	}
	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	public BigDecimal getPctMinFee() {
		return pctMinFee;
	}
	public void setPctMinFee(BigDecimal pctMinFee) {
		this.pctMinFee = pctMinFee;
	}
	public String getPctFeeCurrency() {
		return pctFeeCurrency;
	}
	public void setPctFeeCurrency(String pctFeeCurrency) {
		this.pctFeeCurrency = pctFeeCurrency;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getConditionDesc() {
		return conditionDesc;
	}
	public void setConditionDesc(String conditionDesc) {
		this.conditionDesc = conditionDesc;
	}
	public String getBillingBasis() {
		return billingBasis;
	}
	public void setBillingBasis(String billingBasis) {
		this.billingBasis = billingBasis;
	}
	public String getBillingCycle() {
		return billingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		this.billingCycle = billingCycle;
	}
	public String getBillingCurrency() {
		return billingCurrency;
	}
	public void setBillingCurrency(String billingCurrency) {
		this.billingCurrency = billingCurrency;
	}
	public String getConditionListStr() {
		return conditionListStr;
	}
	public void setConditionListStr(String conditionListStr) {
		this.conditionListStr = conditionListStr;
	}
	public List<MerChargeLadderChargeDTO> getMerChargeLadderChargeDTOList() {
		return merChargeLadderChargeDTOList;
	}
	public void setMerChargeLadderChargeDTOList(
			List<MerChargeLadderChargeDTO> merChargeLadderChargeDTOList) {
		this.merChargeLadderChargeDTOList = merChargeLadderChargeDTOList;
	}
    
    
}



