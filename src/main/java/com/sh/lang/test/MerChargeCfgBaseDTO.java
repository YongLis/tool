package com.sh.lang.test;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class MerChargeCfgBaseDTO implements Serializable {

    private static final long serialVersionUID = -4475735581695664841L;
    private String id;


    private String chargeId;


    private String chargeType;


    private String chargeMode;
    private String chargeModeStr;

    private String pctFeeRefundType;


    private String fixedFeeRefundType;

    private String objectOfCharge;

    private List<MerChargeCfgBaseDtlDTO> merChargeCfgBaseDtlDTOList;

    private BigDecimal refundProcFee;
    private String refundProcFeeCy;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getChargeMode() {
		return chargeMode;
	}
	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}
	public String getChargeModeStr() {
		return chargeModeStr;
	}
	public void setChargeModeStr(String chargeModeStr) {
		this.chargeModeStr = chargeModeStr;
	}
	public String getPctFeeRefundType() {
		return pctFeeRefundType;
	}
	public void setPctFeeRefundType(String pctFeeRefundType) {
		this.pctFeeRefundType = pctFeeRefundType;
	}
	public String getFixedFeeRefundType() {
		return fixedFeeRefundType;
	}
	public void setFixedFeeRefundType(String fixedFeeRefundType) {
		this.fixedFeeRefundType = fixedFeeRefundType;
	}
	public String getObjectOfCharge() {
		return objectOfCharge;
	}
	public void setObjectOfCharge(String objectOfCharge) {
		this.objectOfCharge = objectOfCharge;
	}
	public List<MerChargeCfgBaseDtlDTO> getMerChargeCfgBaseDtlDTOList() {
		return merChargeCfgBaseDtlDTOList;
	}
	public void setMerChargeCfgBaseDtlDTOList(
			List<MerChargeCfgBaseDtlDTO> merChargeCfgBaseDtlDTOList) {
		this.merChargeCfgBaseDtlDTOList = merChargeCfgBaseDtlDTOList;
	}
	public BigDecimal getRefundProcFee() {
		return refundProcFee;
	}
	public void setRefundProcFee(BigDecimal refundProcFee) {
		this.refundProcFee = refundProcFee;
	}
	public String getRefundProcFeeCy() {
		return refundProcFeeCy;
	}
	public void setRefundProcFeeCy(String refundProcFeeCy) {
		this.refundProcFeeCy = refundProcFeeCy;
	}
    
    
}