package com.sh.lang.test;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RejectionFineTemplateDTO implements Serializable {

    private static final long serialVersionUID = 375151883954917985L;

    /**
     * 主键
     */
    private String id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则描述
     */
    private String ruleDesc;

    /**
     * 卡组织维度
     */
    private String cardOrgDimension;

    /**
     * 罚金规则详情
     */
    private String fineRuleDetails;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 状态（0：禁用 1：启用）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date gmtCreateTime;

    /**
     * 最后更新时间
     */
    private Date gmtUpdateTime;


    /**
     * 关联商户号
     */
    private String merchantId;



    /**
     * 关联表达式列表
     */
    private List<FineExpressionDTO> expressionDTOList;
    /**
     * 规则类型（F：罚金 D：处理费）
     */
    private String ruleType;

    private String ruleTypeStr;
    /**
     * 计算方式（A：全额累进 M：超额累进）
     */
    private String calMode;

    private String calModeStr;
    /**
     * 基准收费金额
     */
    private BigDecimal rebaseAmount;

    /**
     * 基准收费币种
     */
    private String rebaseCurrencyCode;

    private String bizType;

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getCalMode() {
        return calMode;
    }

    public void setCalMode(String calMode) {
        this.calMode = calMode;
    }

    public BigDecimal getRebaseAmount() {
        return rebaseAmount;
    }

    public void setRebaseAmount(BigDecimal rebaseAmount) {
        this.rebaseAmount = rebaseAmount;
    }

    public String getRebaseCurrencyCode() {
        return rebaseCurrencyCode;
    }

    public void setRebaseCurrencyCode(String rebaseCurrencyCode) {
        this.rebaseCurrencyCode = rebaseCurrencyCode;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public String getCardOrgDimension() {
        return cardOrgDimension;
    }

    public void setCardOrgDimension(String cardOrgDimension) {
        this.cardOrgDimension = cardOrgDimension;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<FineExpressionDTO> getExpressionDTOList() {
        return expressionDTOList;
    }

    public void setExpressionDTOList(List<FineExpressionDTO> expressionDTOList) {
        this.expressionDTOList = expressionDTOList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getFineRuleDetails() {
        return fineRuleDetails;
    }

    public void setFineRuleDetails(String fineRuleDetails) {
        this.fineRuleDetails = fineRuleDetails;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
