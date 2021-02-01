package com.sh.lang.test;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MerChargeCfgDetailDTO implements Serializable {
    private static final long serialVersionUID = -6638996308077956461L;
    private String id;
    private String merchantId;
    private String merchantName;
    private String prodCode;
    private String calcAccuracy;
    private String contract;
    private String effectiveDate;
    private String expiryDate;
    private BigDecimal sysProcFeeFail;
    private BigDecimal sysProcFee;
    private BigDecimal cnyWithdrawFee;
    private BigDecimal forgnCyWithdrawFee;
    private BigDecimal threeDProcFee;
    private BigDecimal threeDJudgFee;
    private BigDecimal refundProcFee;
    private BigDecimal accOpenFee;
    private BigDecimal annualTechMtcFee;
    private BigDecimal fixedMargin;
    private String operateUser;
    private String checkUser;
    private String recordStatus;
    private String useable;
    private Date gmtCreateTime;
    private Date gmtUpdateTime;
    private BigDecimal frdPayoutFeePct;
    private BigDecimal frdPayoutFeePctNotAcq;
    private String fraudFeeRefundTypeStr;
    private String fraudFeeRefundType;
    private String sysProcFeeFailCy;

    private String sysProcFeeCy;


    private String cnyWithdrawFeeCy;


    private String forgnCyWithdrawFeeCy;

    private String threeDProcFeeCy;

    private String threeDJudgFeeCy;


    private String refundProcFeeCy;


    private String accOpenFeeCy;


    private String annualTechMtcFeeCy;


    private String fixedMarginCy;


    private List<MerChargeCfgBaseDTO> merChargeCfgBaseDTOList;
    //拒付罚金规则
    private List<RejectionFineTemplateDTO> rejectionFineTemplateDTO;
    //拒付处理规则
    private List<RejectionFineTemplateDTO> rejectionDealFeeTemplateDTO;
    //拒付算法
    private RejectionRateTemplateDTO rejectionRateTemplateDTO;
    //拒付出来币种
    private String rejectionDealFeeCurrency;
    //拒付处理金额
    private String rejectionDealFee;
    //处理费类型
    private String rejectionDealFeeType;
    //数据库里面最大的失效时间
    private Date lastExpireDate;
    //固定保证金收取日期
    private String fixedMarginCollDate;
    //开户费收取日期
    private String accOpenFeeCollDate;
    //年技术收取日期
    private String annualTechMtcCollDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getCalcAccuracy() {
		return calcAccuracy;
	}
	public void setCalcAccuracy(String calcAccuracy) {
		this.calcAccuracy = calcAccuracy;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public BigDecimal getSysProcFeeFail() {
		return sysProcFeeFail;
	}
	public void setSysProcFeeFail(BigDecimal sysProcFeeFail) {
		this.sysProcFeeFail = sysProcFeeFail;
	}
	public BigDecimal getSysProcFee() {
		return sysProcFee;
	}
	public void setSysProcFee(BigDecimal sysProcFee) {
		this.sysProcFee = sysProcFee;
	}
	public BigDecimal getCnyWithdrawFee() {
		return cnyWithdrawFee;
	}
	public void setCnyWithdrawFee(BigDecimal cnyWithdrawFee) {
		this.cnyWithdrawFee = cnyWithdrawFee;
	}
	public BigDecimal getForgnCyWithdrawFee() {
		return forgnCyWithdrawFee;
	}
	public void setForgnCyWithdrawFee(BigDecimal forgnCyWithdrawFee) {
		this.forgnCyWithdrawFee = forgnCyWithdrawFee;
	}
	public BigDecimal getThreeDProcFee() {
		return threeDProcFee;
	}
	public void setThreeDProcFee(BigDecimal threeDProcFee) {
		this.threeDProcFee = threeDProcFee;
	}
	public BigDecimal getThreeDJudgFee() {
		return threeDJudgFee;
	}
	public void setThreeDJudgFee(BigDecimal threeDJudgFee) {
		this.threeDJudgFee = threeDJudgFee;
	}
	public BigDecimal getRefundProcFee() {
		return refundProcFee;
	}
	public void setRefundProcFee(BigDecimal refundProcFee) {
		this.refundProcFee = refundProcFee;
	}
	public BigDecimal getAccOpenFee() {
		return accOpenFee;
	}
	public void setAccOpenFee(BigDecimal accOpenFee) {
		this.accOpenFee = accOpenFee;
	}
	public BigDecimal getAnnualTechMtcFee() {
		return annualTechMtcFee;
	}
	public void setAnnualTechMtcFee(BigDecimal annualTechMtcFee) {
		this.annualTechMtcFee = annualTechMtcFee;
	}
	public BigDecimal getFixedMargin() {
		return fixedMargin;
	}
	public void setFixedMargin(BigDecimal fixedMargin) {
		this.fixedMargin = fixedMargin;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getUseable() {
		return useable;
	}
	public void setUseable(String useable) {
		this.useable = useable;
	}
	public Date getGmtCreateTime() {
		return gmtCreateTime;
	}
	public void setGmtCreateTime(Date gmtCreateTime) {
		this.gmtCreateTime = gmtCreateTime;
	}
	public Date getGmtUpdateTime() {
		return gmtUpdateTime;
	}
	public void setGmtUpdateTime(Date gmtUpdateTime) {
		this.gmtUpdateTime = gmtUpdateTime;
	}
	public BigDecimal getFrdPayoutFeePct() {
		return frdPayoutFeePct;
	}
	public void setFrdPayoutFeePct(BigDecimal frdPayoutFeePct) {
		this.frdPayoutFeePct = frdPayoutFeePct;
	}
	public BigDecimal getFrdPayoutFeePctNotAcq() {
		return frdPayoutFeePctNotAcq;
	}
	public void setFrdPayoutFeePctNotAcq(BigDecimal frdPayoutFeePctNotAcq) {
		this.frdPayoutFeePctNotAcq = frdPayoutFeePctNotAcq;
	}
	public String getFraudFeeRefundTypeStr() {
		return fraudFeeRefundTypeStr;
	}
	public void setFraudFeeRefundTypeStr(String fraudFeeRefundTypeStr) {
		this.fraudFeeRefundTypeStr = fraudFeeRefundTypeStr;
	}
	public String getFraudFeeRefundType() {
		return fraudFeeRefundType;
	}
	public void setFraudFeeRefundType(String fraudFeeRefundType) {
		this.fraudFeeRefundType = fraudFeeRefundType;
	}
	public String getSysProcFeeFailCy() {
		return sysProcFeeFailCy;
	}
	public void setSysProcFeeFailCy(String sysProcFeeFailCy) {
		this.sysProcFeeFailCy = sysProcFeeFailCy;
	}
	public String getSysProcFeeCy() {
		return sysProcFeeCy;
	}
	public void setSysProcFeeCy(String sysProcFeeCy) {
		this.sysProcFeeCy = sysProcFeeCy;
	}
	public String getCnyWithdrawFeeCy() {
		return cnyWithdrawFeeCy;
	}
	public void setCnyWithdrawFeeCy(String cnyWithdrawFeeCy) {
		this.cnyWithdrawFeeCy = cnyWithdrawFeeCy;
	}
	public String getForgnCyWithdrawFeeCy() {
		return forgnCyWithdrawFeeCy;
	}
	public void setForgnCyWithdrawFeeCy(String forgnCyWithdrawFeeCy) {
		this.forgnCyWithdrawFeeCy = forgnCyWithdrawFeeCy;
	}
	public String getThreeDProcFeeCy() {
		return threeDProcFeeCy;
	}
	public void setThreeDProcFeeCy(String threeDProcFeeCy) {
		this.threeDProcFeeCy = threeDProcFeeCy;
	}
	public String getThreeDJudgFeeCy() {
		return threeDJudgFeeCy;
	}
	public void setThreeDJudgFeeCy(String threeDJudgFeeCy) {
		this.threeDJudgFeeCy = threeDJudgFeeCy;
	}
	public String getRefundProcFeeCy() {
		return refundProcFeeCy;
	}
	public void setRefundProcFeeCy(String refundProcFeeCy) {
		this.refundProcFeeCy = refundProcFeeCy;
	}
	public String getAccOpenFeeCy() {
		return accOpenFeeCy;
	}
	public void setAccOpenFeeCy(String accOpenFeeCy) {
		this.accOpenFeeCy = accOpenFeeCy;
	}
	public String getAnnualTechMtcFeeCy() {
		return annualTechMtcFeeCy;
	}
	public void setAnnualTechMtcFeeCy(String annualTechMtcFeeCy) {
		this.annualTechMtcFeeCy = annualTechMtcFeeCy;
	}
	public String getFixedMarginCy() {
		return fixedMarginCy;
	}
	public void setFixedMarginCy(String fixedMarginCy) {
		this.fixedMarginCy = fixedMarginCy;
	}
	public List<MerChargeCfgBaseDTO> getMerChargeCfgBaseDTOList() {
		return merChargeCfgBaseDTOList;
	}
	public void setMerChargeCfgBaseDTOList(
			List<MerChargeCfgBaseDTO> merChargeCfgBaseDTOList) {
		this.merChargeCfgBaseDTOList = merChargeCfgBaseDTOList;
	}
	public List<RejectionFineTemplateDTO> getRejectionFineTemplateDTO() {
		return rejectionFineTemplateDTO;
	}
	public void setRejectionFineTemplateDTO(
			List<RejectionFineTemplateDTO> rejectionFineTemplateDTO) {
		this.rejectionFineTemplateDTO = rejectionFineTemplateDTO;
	}
	public List<RejectionFineTemplateDTO> getRejectionDealFeeTemplateDTO() {
		return rejectionDealFeeTemplateDTO;
	}
	public void setRejectionDealFeeTemplateDTO(
			List<RejectionFineTemplateDTO> rejectionDealFeeTemplateDTO) {
		this.rejectionDealFeeTemplateDTO = rejectionDealFeeTemplateDTO;
	}
	public RejectionRateTemplateDTO getRejectionRateTemplateDTO() {
		return rejectionRateTemplateDTO;
	}
	public void setRejectionRateTemplateDTO(
			RejectionRateTemplateDTO rejectionRateTemplateDTO) {
		this.rejectionRateTemplateDTO = rejectionRateTemplateDTO;
	}
	public String getRejectionDealFeeCurrency() {
		return rejectionDealFeeCurrency;
	}
	public void setRejectionDealFeeCurrency(String rejectionDealFeeCurrency) {
		this.rejectionDealFeeCurrency = rejectionDealFeeCurrency;
	}
	public String getRejectionDealFee() {
		return rejectionDealFee;
	}
	public void setRejectionDealFee(String rejectionDealFee) {
		this.rejectionDealFee = rejectionDealFee;
	}
	public String getRejectionDealFeeType() {
		return rejectionDealFeeType;
	}
	public void setRejectionDealFeeType(String rejectionDealFeeType) {
		this.rejectionDealFeeType = rejectionDealFeeType;
	}
	public Date getLastExpireDate() {
		return lastExpireDate;
	}
	public void setLastExpireDate(Date lastExpireDate) {
		this.lastExpireDate = lastExpireDate;
	}
	public String getFixedMarginCollDate() {
		return fixedMarginCollDate;
	}
	public void setFixedMarginCollDate(String fixedMarginCollDate) {
		this.fixedMarginCollDate = fixedMarginCollDate;
	}
	public String getAccOpenFeeCollDate() {
		return accOpenFeeCollDate;
	}
	public void setAccOpenFeeCollDate(String accOpenFeeCollDate) {
		this.accOpenFeeCollDate = accOpenFeeCollDate;
	}
	public String getAnnualTechMtcCollDate() {
		return annualTechMtcCollDate;
	}
	public void setAnnualTechMtcCollDate(String annualTechMtcCollDate) {
		this.annualTechMtcCollDate = annualTechMtcCollDate;
	}
    
    
}