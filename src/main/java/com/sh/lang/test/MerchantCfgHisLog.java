package com.sh.lang.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantCfgHisLog implements Serializable{
	private String batchNo;
	private String Id;
	private String MerchantId;
	private String MerchantName;
	private String ProdCode;
	private String CalcAccuracy;
	private String Contract;
	private BigDecimal SysProcFeeFail;
	private String SysProcFeeFailCy;
	private BigDecimal SysProcFee;
	private String SysProcFeeCy;
	private BigDecimal CnyWithdrawFee;
	private String CnyWithdrawFeeCy;
	private BigDecimal ForgnCyWithdrawFee;
	private String ForgnCyWithdrawFeeCy;
	private BigDecimal ThreeDProcFee;
	private String ThreeDProcFeeCy;
	private BigDecimal ThreeDJudgFee;
	private String ThreeDJudgFeeCy;
	private BigDecimal RefundProcFee;
	private String RefundProcFeeCy;
	private BigDecimal AccOpenFee;
	private String AccOpenFeeCy;
	private BigDecimal AnnualTechMtcFee;
	private String AnnualTechMtcFeeCy;
	private BigDecimal FixedMargin;
	private String FixedMarginCy;
	private String OperateUser;
	private String CheckUser;
	private String RecordStatus;
	private String Useable;
	private Date GmtCreateTime;
	private Date GmtUpdateTime;
	private Date EffectiveTime;
	private Date ExpireTime;
	private BigDecimal FrdPayoutFeePct;
	private BigDecimal FrdPayoutFeePctNotAcq;
	private String FrdFeeRefundType;
	private BigDecimal RejectionDealFee;
	private String RejectionDealFeeCurrency;
	private String RejectionDealFeeType;
	private String AnnualTechMtcCollDate;
	private String FixedMarginCollDate;
	private String AccOpenFeeCollDate;
	private long BaseId;
	private String ChargeType;
	private String ChargeMode;
	private String PctFeeRefundType;
	private String FixedFeeRefundType;
	private int dtlId;
	private String ObjectOfCharge;
	private String Region;
	private String CountryCode;
	private String CardOrg;
	private String TransactionMode;
	private String ChannelCode;
	private String SubChannelCode;
	private BigDecimal FixedFeeAmt;
	private String FixedFeeCurrency;
	private BigDecimal Percent;
	private BigDecimal PctMinFee;
	private String PctFeeCurrency;
	private int Priority;
	private String IssueCurrency;
	private String BillingBasis;
	private String BillingCycle;
	private String BillingCurrency;
	public String getId() {
		return Id;
	}
	
	public String getBatchNo() {
		return batchNo;
	}

	public int getDtlId() {
		return dtlId;
	}

	public void setDtlId(int dtlId) {
		this.dtlId = dtlId;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public void setId(String id) {
		Id = id;
	}
	public String getMerchantId() {
		return MerchantId;
	}
	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}
	public String getMerchantName() {
		return MerchantName;
	}
	public void setMerchantName(String merchantName) {
		MerchantName = merchantName;
	}
	public String getProdCode() {
		return ProdCode;
	}
	public void setProdCode(String prodCode) {
		ProdCode = prodCode;
	}
	public String getCalcAccuracy() {
		return CalcAccuracy;
	}
	public void setCalcAccuracy(String calcAccuracy) {
		CalcAccuracy = calcAccuracy;
	}
	public String getContract() {
		return Contract;
	}
	public void setContract(String contract) {
		Contract = contract;
	}
	public BigDecimal getSysProcFeeFail() {
		return SysProcFeeFail;
	}
	public void setSysProcFeeFail(BigDecimal sysProcFeeFail) {
		SysProcFeeFail = sysProcFeeFail;
	}
	public String getSysProcFeeFailCy() {
		return SysProcFeeFailCy;
	}
	public void setSysProcFeeFailCy(String sysProcFeeFailCy) {
		SysProcFeeFailCy = sysProcFeeFailCy;
	}
	public BigDecimal getSysProcFee() {
		return SysProcFee;
	}
	public void setSysProcFee(BigDecimal sysProcFee) {
		SysProcFee = sysProcFee;
	}
	public String getSysProcFeeCy() {
		return SysProcFeeCy;
	}
	public void setSysProcFeeCy(String sysProcFeeCy) {
		SysProcFeeCy = sysProcFeeCy;
	}
	public BigDecimal getCnyWithdrawFee() {
		return CnyWithdrawFee;
	}
	public void setCnyWithdrawFee(BigDecimal cnyWithdrawFee) {
		CnyWithdrawFee = cnyWithdrawFee;
	}
	public String getCnyWithdrawFeeCy() {
		return CnyWithdrawFeeCy;
	}
	public void setCnyWithdrawFeeCy(String cnyWithdrawFeeCy) {
		CnyWithdrawFeeCy = cnyWithdrawFeeCy;
	}
	public BigDecimal getForgnCyWithdrawFee() {
		return ForgnCyWithdrawFee;
	}
	public void setForgnCyWithdrawFee(BigDecimal forgnCyWithdrawFee) {
		ForgnCyWithdrawFee = forgnCyWithdrawFee;
	}
	public String getForgnCyWithdrawFeeCy() {
		return ForgnCyWithdrawFeeCy;
	}
	public void setForgnCyWithdrawFeeCy(String forgnCyWithdrawFeeCy) {
		ForgnCyWithdrawFeeCy = forgnCyWithdrawFeeCy;
	}
	public BigDecimal getThreeDProcFee() {
		return ThreeDProcFee;
	}
	public void setThreeDProcFee(BigDecimal threeDProcFee) {
		ThreeDProcFee = threeDProcFee;
	}
	public String getThreeDProcFeeCy() {
		return ThreeDProcFeeCy;
	}
	public void setThreeDProcFeeCy(String threeDProcFeeCy) {
		ThreeDProcFeeCy = threeDProcFeeCy;
	}
	public BigDecimal getThreeDJudgFee() {
		return ThreeDJudgFee;
	}
	public void setThreeDJudgFee(BigDecimal threeDJudgFee) {
		ThreeDJudgFee = threeDJudgFee;
	}
	public String getThreeDJudgFeeCy() {
		return ThreeDJudgFeeCy;
	}
	public void setThreeDJudgFeeCy(String threeDJudgFeeCy) {
		ThreeDJudgFeeCy = threeDJudgFeeCy;
	}
	public BigDecimal getRefundProcFee() {
		return RefundProcFee;
	}
	public void setRefundProcFee(BigDecimal refundProcFee) {
		RefundProcFee = refundProcFee;
	}
	public String getRefundProcFeeCy() {
		return RefundProcFeeCy;
	}
	public void setRefundProcFeeCy(String refundProcFeeCy) {
		RefundProcFeeCy = refundProcFeeCy;
	}
	public BigDecimal getAccOpenFee() {
		return AccOpenFee;
	}
	public void setAccOpenFee(BigDecimal accOpenFee) {
		AccOpenFee = accOpenFee;
	}
	public String getAccOpenFeeCy() {
		return AccOpenFeeCy;
	}
	public void setAccOpenFeeCy(String accOpenFeeCy) {
		AccOpenFeeCy = accOpenFeeCy;
	}
	public BigDecimal getAnnualTechMtcFee() {
		return AnnualTechMtcFee;
	}
	public void setAnnualTechMtcFee(BigDecimal annualTechMtcFee) {
		AnnualTechMtcFee = annualTechMtcFee;
	}
	public String getAnnualTechMtcFeeCy() {
		return AnnualTechMtcFeeCy;
	}
	public void setAnnualTechMtcFeeCy(String annualTechMtcFeeCy) {
		AnnualTechMtcFeeCy = annualTechMtcFeeCy;
	}
	public BigDecimal getFixedMargin() {
		return FixedMargin;
	}
	public void setFixedMargin(BigDecimal fixedMargin) {
		FixedMargin = fixedMargin;
	}
	public String getFixedMarginCy() {
		return FixedMarginCy;
	}
	public void setFixedMarginCy(String fixedMarginCy) {
		FixedMarginCy = fixedMarginCy;
	}
	public String getOperateUser() {
		return OperateUser;
	}
	public void setOperateUser(String operateUser) {
		OperateUser = operateUser;
	}
	public String getCheckUser() {
		return CheckUser;
	}
	public void setCheckUser(String checkUser) {
		CheckUser = checkUser;
	}
	public String getRecordStatus() {
		return RecordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		RecordStatus = recordStatus;
	}
	public String getUseable() {
		return Useable;
	}
	public void setUseable(String useable) {
		Useable = useable;
	}
	public Date getGmtCreateTime() {
		return GmtCreateTime;
	}
	public void setGmtCreateTime(Date gmtCreateTime) {
		GmtCreateTime = gmtCreateTime;
	}
	public Date getGmtUpdateTime() {
		return GmtUpdateTime;
	}
	public void setGmtUpdateTime(Date gmtUpdateTime) {
		GmtUpdateTime = gmtUpdateTime;
	}
	public Date getEffectiveTime() {
		return EffectiveTime;
	}
	public void setEffectiveTime(Date effectiveTime) {
		EffectiveTime = effectiveTime;
	}
	public Date getExpireTime() {
		return ExpireTime;
	}
	public void setExpireTime(Date expireTime) {
		ExpireTime = expireTime;
	}
	public BigDecimal getFrdPayoutFeePct() {
		return FrdPayoutFeePct;
	}
	public void setFrdPayoutFeePct(BigDecimal frdPayoutFeePct) {
		FrdPayoutFeePct = frdPayoutFeePct;
	}
	public BigDecimal getFrdPayoutFeePctNotAcq() {
		return FrdPayoutFeePctNotAcq;
	}
	public void setFrdPayoutFeePctNotAcq(BigDecimal frdPayoutFeePctNotAcq) {
		FrdPayoutFeePctNotAcq = frdPayoutFeePctNotAcq;
	}
	public String getFrdFeeRefundType() {
		return FrdFeeRefundType;
	}
	public void setFrdFeeRefundType(String frdFeeRefundType) {
		FrdFeeRefundType = frdFeeRefundType;
	}
	public BigDecimal getRejectionDealFee() {
		return RejectionDealFee;
	}
	public void setRejectionDealFee(BigDecimal rejectionDealFee) {
		RejectionDealFee = rejectionDealFee;
	}
	public String getRejectionDealFeeCurrency() {
		return RejectionDealFeeCurrency;
	}
	public void setRejectionDealFeeCurrency(String rejectionDealFeeCurrency) {
		RejectionDealFeeCurrency = rejectionDealFeeCurrency;
	}
	public String getRejectionDealFeeType() {
		return RejectionDealFeeType;
	}
	public void setRejectionDealFeeType(String rejectionDealFeeType) {
		RejectionDealFeeType = rejectionDealFeeType;
	}
	public String getAnnualTechMtcCollDate() {
		return AnnualTechMtcCollDate;
	}
	public void setAnnualTechMtcCollDate(String annualTechMtcCollDate) {
		AnnualTechMtcCollDate = annualTechMtcCollDate;
	}
	public String getFixedMarginCollDate() {
		return FixedMarginCollDate;
	}
	public void setFixedMarginCollDate(String fixedMarginCollDate) {
		FixedMarginCollDate = fixedMarginCollDate;
	}
	public String getAccOpenFeeCollDate() {
		return AccOpenFeeCollDate;
	}
	public void setAccOpenFeeCollDate(String accOpenFeeCollDate) {
		AccOpenFeeCollDate = accOpenFeeCollDate;
	}
	public long getBaseId() {
		return BaseId;
	}
	public void setBaseId(long baseId) {
		BaseId = baseId;
	}
	public String getChargeType() {
		return ChargeType;
	}
	public void setChargeType(String chargeType) {
		ChargeType = chargeType;
	}
	public String getChargeMode() {
		return ChargeMode;
	}
	public void setChargeMode(String chargeMode) {
		ChargeMode = chargeMode;
	}
	public String getPctFeeRefundType() {
		return PctFeeRefundType;
	}
	public void setPctFeeRefundType(String pctFeeRefundType) {
		PctFeeRefundType = pctFeeRefundType;
	}
	public String getFixedFeeRefundType() {
		return FixedFeeRefundType;
	}
	public void setFixedFeeRefundType(String fixedFeeRefundType) {
		FixedFeeRefundType = fixedFeeRefundType;
	}
	public String getObjectOfCharge() {
		return ObjectOfCharge;
	}
	public void setObjectOfCharge(String objectOfCharge) {
		ObjectOfCharge = objectOfCharge;
	}
	public String getRegion() {
		return Region;
	}
	public void setRegion(String region) {
		Region = region;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getCardOrg() {
		return CardOrg;
	}
	public void setCardOrg(String cardOrg) {
		CardOrg = cardOrg;
	}
	public String getTransactionMode() {
		return TransactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		TransactionMode = transactionMode;
	}
	public String getChannelCode() {
		return ChannelCode;
	}
	public void setChannelCode(String channelCode) {
		ChannelCode = channelCode;
	}
	public String getSubChannelCode() {
		return SubChannelCode;
	}
	public void setSubChannelCode(String subChannelCode) {
		SubChannelCode = subChannelCode;
	}
	public BigDecimal getFixedFeeAmt() {
		return FixedFeeAmt;
	}
	public void setFixedFeeAmt(BigDecimal fixedFeeAmt) {
		FixedFeeAmt = fixedFeeAmt;
	}
	public String getFixedFeeCurrency() {
		return FixedFeeCurrency;
	}
	public void setFixedFeeCurrency(String fixedFeeCurrency) {
		FixedFeeCurrency = fixedFeeCurrency;
	}
	public BigDecimal getPercent() {
		return Percent;
	}
	public void setPercent(BigDecimal percent) {
		Percent = percent;
	}
	public BigDecimal getPctMinFee() {
		return PctMinFee;
	}
	public void setPctMinFee(BigDecimal pctMinFee) {
		PctMinFee = pctMinFee;
	}
	public String getPctFeeCurrency() {
		return PctFeeCurrency;
	}
	public void setPctFeeCurrency(String pctFeeCurrency) {
		PctFeeCurrency = pctFeeCurrency;
	}
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		Priority = priority;
	}
	public String getIssueCurrency() {
		return IssueCurrency;
	}
	public void setIssueCurrency(String issueCurrency) {
		IssueCurrency = issueCurrency;
	}
	public String getBillingBasis() {
		return BillingBasis;
	}
	public void setBillingBasis(String billingBasis) {
		BillingBasis = billingBasis;
	}
	public String getBillingCycle() {
		return BillingCycle;
	}
	public void setBillingCycle(String billingCycle) {
		BillingCycle = billingCycle;
	}
	public String getBillingCurrency() {
		return BillingCurrency;
	}
	public void setBillingCurrency(String billingCurrency) {
		BillingCurrency = billingCurrency;
	}

}
