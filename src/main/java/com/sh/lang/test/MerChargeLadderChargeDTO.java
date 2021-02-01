package com.sh.lang.test;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 阶梯收费
 */
public class MerChargeLadderChargeDTO implements Serializable {

    /**
     * columnName:STRATEGY_ID
     * remarks:策略ID
     */
    private String strategyId;

    /**
     * columnName:VALUE_FIELD
     * remarks:取值字段
     */
    private String valueField;

    /**
     * columnName:L_VALUE_SYMBOL
     * remarks:左值符号，<， <=， =，>=，>
     */
    private String lValueSymbol;

    /**
     * columnName:L_VALUE
     * remarks:左值
     */
    private BigDecimal lValue;

    /**
     * columnName:LRVALUE_RELATION
     * remarks:左值和右值关系，AND、OR
     */
    private String lrvalueRelation;

    /**
     * columnName:R_VALUE_SYMBOL
     * remarks:右值符号，<， <=， =，>=，>
     */
    private String rValueSymbol;

    /**
     * columnName:R_VALUE
     * remarks:右值
     */
    private BigDecimal rValue;

    /**
     * columnName:USEABLE
     * remarks:是否启用 Y-是 ，N-否
     */
    private String useable;

    /**
     * columnName:STRATEGY_DESC
     * remarks:计算策略描述
     */
    private String strategyDesc;

    //固定费
    private BigDecimal fixedFeeAmount;
    private String fixedFeeCurrency;
    //比例费
    private BigDecimal pctPercent;
    //最低费
    private BigDecimal mPctFeeAmount;
    private String mPctFeeCurrency;

    private Integer sort = 0;

	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getlValueSymbol() {
		return lValueSymbol;
	}

	public void setlValueSymbol(String lValueSymbol) {
		this.lValueSymbol = lValueSymbol;
	}

	public BigDecimal getlValue() {
		return lValue;
	}

	public void setlValue(BigDecimal lValue) {
		this.lValue = lValue;
	}

	public String getLrvalueRelation() {
		return lrvalueRelation;
	}

	public void setLrvalueRelation(String lrvalueRelation) {
		this.lrvalueRelation = lrvalueRelation;
	}

	public String getrValueSymbol() {
		return rValueSymbol;
	}

	public void setrValueSymbol(String rValueSymbol) {
		this.rValueSymbol = rValueSymbol;
	}

	public BigDecimal getrValue() {
		return rValue;
	}

	public void setrValue(BigDecimal rValue) {
		this.rValue = rValue;
	}

	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public String getStrategyDesc() {
		return strategyDesc;
	}

	public void setStrategyDesc(String strategyDesc) {
		this.strategyDesc = strategyDesc;
	}

	public BigDecimal getFixedFeeAmount() {
		return fixedFeeAmount;
	}

	public void setFixedFeeAmount(BigDecimal fixedFeeAmount) {
		this.fixedFeeAmount = fixedFeeAmount;
	}

	public String getFixedFeeCurrency() {
		return fixedFeeCurrency;
	}

	public void setFixedFeeCurrency(String fixedFeeCurrency) {
		this.fixedFeeCurrency = fixedFeeCurrency;
	}

	public BigDecimal getPctPercent() {
		return pctPercent;
	}

	public void setPctPercent(BigDecimal pctPercent) {
		this.pctPercent = pctPercent;
	}

	public BigDecimal getmPctFeeAmount() {
		return mPctFeeAmount;
	}

	public void setmPctFeeAmount(BigDecimal mPctFeeAmount) {
		this.mPctFeeAmount = mPctFeeAmount;
	}

	public String getmPctFeeCurrency() {
		return mPctFeeCurrency;
	}

	public void setmPctFeeCurrency(String mPctFeeCurrency) {
		this.mPctFeeCurrency = mPctFeeCurrency;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
}


