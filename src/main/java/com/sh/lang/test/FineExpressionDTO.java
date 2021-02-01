package com.sh.lang.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class FineExpressionDTO implements Serializable {
	
    private String id;
    private String fineTemplateId;



    /**
     * 拒付率表达式（eq：= ; lt：< ; ltEq：<= ; gt：> ; gtEq：>=）
     */
    private String rejectionRateExpression;
    private String rejectionRateExpressionStr = "-";
    /**
     * 拒付率
     */
    private BigDecimal rejectionRate;


    /**
     * 拒付笔数表达式（eq：= ; lt：< ; ltEq：<= ; gt：> ; gtEq：>=）
     */
    private String rejectionCountExpression;
    private String rejectionCountExpressionStr = "-";

    /**
     * 拒付笔数
     */
    private Integer rejectionCount;

    /**
     * 拒付类型（S：服务类型  F：欺诈类型）
     */
    private String rejectionType;

    private String rejectionTypeStr;
    /**
     * 收费范围（A：全部笔数  M：超额笔数）
     */
    private String chargeScope;

    private String chargeScopeStr;

    /**
     * 标准收费金额
     */
    private BigDecimal standardChargeAmount;

    /**
     * 标准收费币种
     */
    private String standardChargeCurrencyCode;

    /**
     * 收取条件
     */
    private String expressionConditionDesc;

    /**
     * 收取条件详情
     */
    private List<FineExpressionConditionDTO> fineExpressionConditionDTOS;

    /**
     * 拒付率开始值
     */
    private BigDecimal beginRate;
    /**
     * 拒付率结束值
     */
    private BigDecimal endRate;
    /**
     * 拒付率开始值是否包含等于
     */
    private String beginRateContainsEqual;
    /**
     * 拒付率结束值是否包含等于
     */
    private String endRateContainsEqual;



    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}