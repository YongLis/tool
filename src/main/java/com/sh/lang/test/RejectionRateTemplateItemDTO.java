package com.sh.lang.test;



import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 拒付率算法计算项配置DTO
 *
 * @author manyi.lu
 */
public class RejectionRateTemplateItemDTO implements Serializable {


    private static final long serialVersionUID = 4592856297194928568L;
    /**
     * 主键
     */
    private String id;


    /**
     * 拒付率算法模板id
     */
    private String rateTemplateId;


    /**
     * 卡组织统计维度
     */
    private String cardOrgDimension;

    /**
     * 计算分子（C：当月拒付笔数）
     */
    private String calMolecular;

    /**
     * 计算分母（C：当月成功交易笔数  L：上月成功交易笔数）
     */
    private String calDenominator;


    public String getCardOrgDimension() {
        return cardOrgDimension;
    }

    public void setCardOrgDimension(String cardOrgDimension) {
        this.cardOrgDimension = cardOrgDimension;
    }

    public String getCalMolecular() {
        return calMolecular;
    }

    public void setCalMolecular(String calMolecular) {
        this.calMolecular = calMolecular;
    }

    public String getCalDenominator() {
        return calDenominator;
    }

    public void setCalDenominator(String calDenominator) {
        this.calDenominator = calDenominator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRateTemplateId() {
        return rateTemplateId;
    }

    public void setRateTemplateId(String rateTemplateId) {
        this.rateTemplateId = rateTemplateId;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
