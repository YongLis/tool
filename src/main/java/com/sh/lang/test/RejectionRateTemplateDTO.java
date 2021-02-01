package com.sh.lang.test;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 拒付率算法模板model
 *
 * @author manyi.lu
 */
public class RejectionRateTemplateDTO implements Serializable {


    private static final long serialVersionUID = -9150919285320378460L;

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
     * 操作者
     */
    private String operator;


    /**
     * 状态（0：禁用 1：启用）
     */
    private String status;

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


    /**
     * 关联商户号
     */
    private String merchantId;


    /**
     * 配置项list
     */
    private List<RejectionRateTemplateItemDTO> itemDTOList;

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<RejectionRateTemplateItemDTO> getItemDTOList() {
        return itemDTOList;
    }

    public void setItemDTOList(List<RejectionRateTemplateItemDTO> itemDTOList) {
        this.itemDTOList = itemDTOList;
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

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
