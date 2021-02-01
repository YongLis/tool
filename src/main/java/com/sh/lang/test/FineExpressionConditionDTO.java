package com.sh.lang.test;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
public class FineExpressionConditionDTO implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 表达式id
     */
    private String expressionId;

    /**
     * 月份阶梯（C: 当月; LOne: 前一月; LTwo: 前两月; LThree: 前三月; LFour: 前四月; LFive: 前五月）
     */
    private String monthStair;

    /**
     * 拒付率表达式（eq: =; lt: <; ltEq: <=; gt: >; gtEq: >=）
     */
    private String rejectionRateExpression;

    /**
     * 拒付率
     */
    private BigDecimal rejectionRate;

    /**
     * 拒付笔数表达式（eq: =; lt: <; ltEq: <=; gt: >; gtEq: >=）
     */
    private String rejectionCountExpression;

    /**
     * 拒付笔数
     */
    private Integer rejectionCount;

    /**
     * 备注
     */
    private String memo;

    /**
     * 创建时间
     */
    private Date gmtCreateTime;

    /**
     * 最后更新时间
     */
    private Date gmtUpdateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpressionId() {
		return expressionId;
	}

	public void setExpressionId(String expressionId) {
		this.expressionId = expressionId;
	}

	public String getMonthStair() {
		return monthStair;
	}

	public void setMonthStair(String monthStair) {
		this.monthStair = monthStair;
	}

	public String getRejectionRateExpression() {
		return rejectionRateExpression;
	}

	public void setRejectionRateExpression(String rejectionRateExpression) {
		this.rejectionRateExpression = rejectionRateExpression;
	}

	public BigDecimal getRejectionRate() {
		return rejectionRate;
	}

	public void setRejectionRate(BigDecimal rejectionRate) {
		this.rejectionRate = rejectionRate;
	}

	public String getRejectionCountExpression() {
		return rejectionCountExpression;
	}

	public void setRejectionCountExpression(String rejectionCountExpression) {
		this.rejectionCountExpression = rejectionCountExpression;
	}

	public Integer getRejectionCount() {
		return rejectionCount;
	}

	public void setRejectionCount(Integer rejectionCount) {
		this.rejectionCount = rejectionCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
    

}
