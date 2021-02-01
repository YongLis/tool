package com.sh.lang.retry.pg;

import java.util.Date;

/**
 * tableName:report.t_code_offline_transcation
 * remarks:code线下交易还原
 */
public class CodeOfflineTranscation {
    /**
     * columnName:order_id
     * remarks:订单号
     */
    private String orderId;

    /**
     * columnName:apply_type
     * remarks:申请人主体类型：必填，即收款主体类型：C－对公用户D－对私中国居民
     */
    private String applyType;

    /**
     * columnName:country
     * remarks:国籍：必填，默认中国，填CHN
     */
    private String country;

    /**
     * columnName:apply_cert_type
     * remarks:付款人常驻国家或地区：必填，3位国家地区编码
     */
    private String applyCertType;

    /**
     * columnName:apply_cert_no
     * remarks:申请人证件号码
     */
    private String applyCertNo;

    /**
     * columnName:apply_name
     * remarks:申请人名称,填写申请人名称。个人填姓名，企业填公司名称。
     */
    private String applyName;

    /**
     * columnName:apply_phone
     * remarks:申请人联系电话
     */
    private String applyPhone;

    /**
     * columnName:apply_amount
     * remarks:申请金额：必填，整数，以分为单位。
     */
    private String applyAmount;

    /**
     * columnName:apply_currency
     * remarks:申请币种
     */
    private String applyCurrency;

    /**
     * columnName:trade_code
     * remarks:交易编码：货物贸易122030， 酒店住宿223029， 旅游服务223010， 航空机票222024， 留学教育223022， 国际会展228025， 物流支付222040， 国际会议228990， 软件服务227020， 设计咨询228024 
     */
    private String tradeCode;

    /**
     * columnName:payer_name
     * remarks:付款人名称：必填，境外付款人名称，限简体中文和英文
     */
    private String payerName;

    /**
     * columnName:payer_area
     * remarks:付款人常驻国家或地区：必填，3位国家地区编码
     */
    private String payerArea;

    /**
     * columnName:trade_remark
     * remarks:交易附言
     */
    private String tradeRemark;

    /**
     * columnName:settle_order_no
     * remarks:境内付款结算订单号:如同时上传付款文件，请填写,否则为空。对应付款明细文件境内结算订单号。
     */
    private String settleOrderNo;

    /**
     * columnName:goods_type
     * remarks:商品类别：货物贸易必填，其他贸易为空。填2位字符。00 服装01 食品02 电子产品03 箱包04日用品05 保健品06 化妆品07 家电
     */
    private String goodsType;

    /**
     * columnName:tracking_company
     * remarks:物流公司：申请币种是人民币时必填
     */
    private String trackingCompany;

    /**
     * columnName:tracking_number
     * remarks:物流单号：申请币种是人民币时必填
     */
    private String trackingNumber;

    /**
     * columnName:trade_source
     * remarks:交易来源：根据订单来源，填写平台列表里对应的平台名称。
     */
    private String tradeSource;

    /**
     * columnName:goods_name
     * remarks:商品名称
     */
    private String goodsName;

    /**
     * columnName:order_create_time
     * remarks:订单时间
     */
    private String orderCreateTime;

    /**
     * columnName:goods_number
     * remarks:商品数量
     */
    private String goodsNumber;

    /**
     * columnName:apply_email
     * remarks:申请人邮箱
     */
    private String applyEmail;

    /**
     * columnName:trade_time
     * remarks:交易时间
     */
    private String tradeTime;

    /**
     * columnName:payment_order_no
     * remarks:订单时间：选填，精确到秒，yyyymmddhhmmss
     */
    private String paymentOrderNo;

    /**
     * columnName:user_name
     * remarks:使用者姓名
     */
    private String userName;

    /**
     * columnName:service_time
     * remarks:服务时间
     */
    private String serviceTime;

    /**
     * columnName:proc_date
     * remarks:导入日期
     */
    private String procDate;

    /**
     * columnName:file_name
     * remarks:文件名
     */
    private String fileName;

    /**
     * columnName:create_time
     * remarks:创建时间
     */
    private Date createTime;

    /**
     * columnName:modify_time
     * remarks:更新时间
     */
    private Date modifyTime;

    /**
     * columnName:bonded_flag
     * remarks:是否保税货物项下付款：必填，0-否  1-是。默认0-否。
     */
    private String bondedFlag;

    /**
     * columnName:goods_batch
     * remarks:商品型号
     */
    private String goodsBatch;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getApplyCertType() {
        return applyCertType;
    }

    public void setApplyCertType(String applyCertType) {
        this.applyCertType = applyCertType == null ? null : applyCertType.trim();
    }

    public String getApplyCertNo() {
        return applyCertNo;
    }

    public void setApplyCertNo(String applyCertNo) {
        this.applyCertNo = applyCertNo == null ? null : applyCertNo.trim();
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName == null ? null : applyName.trim();
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone == null ? null : applyPhone.trim();
    }

    public String getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(String applyAmount) {
        this.applyAmount = applyAmount == null ? null : applyAmount.trim();
    }

    public String getApplyCurrency() {
        return applyCurrency;
    }

    public void setApplyCurrency(String applyCurrency) {
        this.applyCurrency = applyCurrency == null ? null : applyCurrency.trim();
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode == null ? null : tradeCode.trim();
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    public String getPayerArea() {
        return payerArea;
    }

    public void setPayerArea(String payerArea) {
        this.payerArea = payerArea == null ? null : payerArea.trim();
    }

    public String getTradeRemark() {
        return tradeRemark;
    }

    public void setTradeRemark(String tradeRemark) {
        this.tradeRemark = tradeRemark == null ? null : tradeRemark.trim();
    }

    public String getSettleOrderNo() {
        return settleOrderNo;
    }

    public void setSettleOrderNo(String settleOrderNo) {
        this.settleOrderNo = settleOrderNo == null ? null : settleOrderNo.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getTrackingCompany() {
        return trackingCompany;
    }

    public void setTrackingCompany(String trackingCompany) {
        this.trackingCompany = trackingCompany == null ? null : trackingCompany.trim();
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public String getTradeSource() {
        return tradeSource;
    }

    public void setTradeSource(String tradeSource) {
        this.tradeSource = tradeSource == null ? null : tradeSource.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime == null ? null : orderCreateTime.trim();
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber == null ? null : goodsNumber.trim();
    }

    public String getApplyEmail() {
        return applyEmail;
    }

    public void setApplyEmail(String applyEmail) {
        this.applyEmail = applyEmail == null ? null : applyEmail.trim();
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }

    public String getPaymentOrderNo() {
        return paymentOrderNo;
    }

    public void setPaymentOrderNo(String paymentOrderNo) {
        this.paymentOrderNo = paymentOrderNo == null ? null : paymentOrderNo.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime == null ? null : serviceTime.trim();
    }

    public String getProcDate() {
        return procDate;
    }

    public void setProcDate(String procDate) {
        this.procDate = procDate == null ? null : procDate.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getBondedFlag() {
        return bondedFlag;
    }

    public void setBondedFlag(String bondedFlag) {
        this.bondedFlag = bondedFlag == null ? null : bondedFlag.trim();
    }

    public String getGoodsBatch() {
        return goodsBatch;
    }

    public void setGoodsBatch(String goodsBatch) {
        this.goodsBatch = goodsBatch == null ? null : goodsBatch.trim();
    }
}