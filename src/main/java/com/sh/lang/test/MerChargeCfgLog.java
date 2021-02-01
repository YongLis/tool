package com.sh.lang.test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * tableName:T_CCS_MER_CHARGE_CFG_LOG
 * remarks:商户收费配置LOG
 */
public class MerChargeCfgLog {
    /**
     * columnName:LOG_ID
     * remarks:日志表ID, 自增id
     */
    private String logId;

    /**
     * columnName:CFG_ID
     * remarks:T_CCS_MER_CHARGE_CFG.ID
     */
    private String cfgId;

    /**
     * columnName:MERCHANT_ID
     * remarks:商户号
     */
    private String merchantId;

    /**
     * columnName:MERCHANT_NAME
     * remarks:商户名称, 校验商户合法时候默认查出
     */
    private String merchantName;

    /**
     * columnName:PROD_CODE
     * remarks:*收费产品 10-收单 11-收款 12-跨境贷 13-TW
     */
    private String prodCode;

    /**
     * columnName:CALC_ACCURACY
     * remarks:*计算精度 1-四舍五入, 默认该值
     */
    private String calcAccuracy;

    /**
     * columnName:CONTRACT
     * remarks:*协议状态 0-不可用 1-可用
     */
    private String contract;

    /**
     * columnName:EFFECTIVE_DATE
     * remarks:生效日期
     */
    private String effectiveDate;

    /**
     * columnName:EXPIRY_DATE
     * remarks:失效日期
     */
    private String expiryDate;

    /**
     * columnName:OPERATE_USER
     * remarks:经办人
     */
    private String operateUser;

    /**
     * columnName:CHECK_USER
     * remarks:复核人
     */
    private String checkUser;

    /**
     * columnName:RECORD_STATUS
     * remarks:01-新增待复核, 02-修改待复核, 03-删除待复核, 04-复核驳回, 05 -复核通过
     */
    private String recordStatus;

    /**
     * columnName:USEABLE
     * remarks:0-禁用, 1-启用
     */
    private String useable;

    /**
     * columnName:GMT_CREATE_TIME
     * remarks:创建日期
     */
    private Date gmtCreateTime;

    /**
     * columnName:GMT_UPDATE_TIME
     * remarks:修改日期
     */
    private Date gmtUpdateTime;

    public Date getGmtUpdateTime() {
		return gmtUpdateTime;
	}

	public void setGmtUpdateTime(Date gmtUpdateTime) {
		this.gmtUpdateTime = gmtUpdateTime;
	}

	public String getChgJson() {
		return chgJson;
	}

	public void setChgJson(String chgJson) {
		this.chgJson = chgJson;
	}

	public String getRejectionDealFeeType() {
		return rejectionDealFeeType;
	}

	public void setRejectionDealFeeType(String rejectionDealFeeType) {
		this.rejectionDealFeeType = rejectionDealFeeType;
	}

	/**
     * columnName:CHG_JSON
     * remarks:计费配置json
     */
    private String chgJson;

    private BigDecimal frdPayoutFeePct;
    private BigDecimal frdPayoutFeePctNotAcq;

    /**
     * 拒付处理费类型（S：单笔收取 M：月度收取）
     */
    private String rejectionDealFeeType;

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

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getCfgId() {
        return cfgId;
    }

    public void setCfgId(String cfgId) {
        this.cfgId = cfgId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode == null ? null : prodCode.trim();
    }

    public String getCalcAccuracy() {
        return calcAccuracy;
    }

    public void setCalcAccuracy(String calcAccuracy) {
        this.calcAccuracy = calcAccuracy == null ? null : calcAccuracy.trim();
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate == null ? null : expiryDate.trim();
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser == null ? null : operateUser.trim();
    }

    public String getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser == null ? null : checkUser.trim();
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus == null ? null : recordStatus.trim();
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
    }

    public Date getGmtCreateTime() {
        return gmtCreateTime;
    }

    public void setGmtCreateTime(Date gmtCreateTime) {
        this.gmtCreateTime = gmtCreateTime;
    }

}