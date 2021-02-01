package com.sh.lang.retry.pg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class DataSyncTask {
	
	public static List<AcquireDetail> queryFromMysql(){
		
		List<AcquireDetail> list = new ArrayList<AcquireDetail>();
		String sql = "SELECT eventId AS eventid, merchantId AS merchantid, accessType AS accesstype, transId AS transid, acsOrderId AS acsorderid, tradeType AS tradetype, siteId AS siteid, registerUserId AS registeruserid, transChannel AS transchannel, payMethod AS paymethod, terminalType AS terminaltype, origPayType AS origpaytype, origTransId AS origtransid, transTimeout AS transtimeout, currencyCode AS currencycode, currencyAmount AS currencyamount, amount AS amount, goodsName AS goodsname, goodsDesc AS goodsdesc, billAddress AS billaddress, billCity AS billcity, billCountryCode AS billcountrycode, billEmail AS billemail, billFirstName AS billfirstname, billLASTName AS billlastname, billPhoneCity AS billphonecity, billPhoneNumber AS billphonenumber, billPostalCode AS billpostalcode, billState AS billstate, cardEmail AS cardemail, cardFirstName AS cardfirstname, cardLastName AS cardlastname, cardNo AS cardno, cardNoCountryName AS cardnocountryname, cardPhoneNumber AS cardphonenumber, cashierIp AS cashierip, cashierIpCountry AS cashieripcountry, customerIp AS customerip, customerIpCountry AS customeripcountry, fingerPrintId AS fingerprintid, riskLevel AS risklevel, shippingAddress AS shippingaddress, shippingCity AS shippingcity, shippingCountryCode AS shippingcountrycode, shippingEmail AS shippingemail, shippingFirstName AS shippingfirstname, shippingLASTName AS shippinglastname, shippingPhoneNumber AS shippingphonenumber, shippingPostalCode AS shippingpostalcode, shippingState AS shippingstate, submitTime AS submittime, xddid AS xddid, origAcsOrderId AS origacsorderid, cbkCode AS cbkcode, cbkMsg AS cbkmsg, DATE_FORMAT(createTime, '%Y%m%d%H%i%s') AS createtime, dealStatus AS dealstatus, value2 AS value2, decision AS decision, riskCode AS riskcode, riskMsg AS riskmsg, cardOrg AS cardorg, cardNoCountryNameA2 AS cardnocountrynamea2, riskRuleId AS riskruleid, riskRuleType AS riskruletype, shippingName AS shippingname, orderRespCode AS orderrespcode, orderRespMsg AS orderrespmsg, orderStatus AS orderstatus, orgCode AS orgcode, completeTime AS completetime, DATE_FORMAT(updateTime, '%Y%m%d%H%i%s') AS updatetime, riskAssessmentOnly AS riskassessmentonly, compensation AS compensation, decisionDetail AS decisiondetail, maskCardNo AS maskcardno, repoLabelMonitorBlack AS repolabelmonitorblack, repoLabelMonitorWhite AS repolabelmonitorwhite, custLevel AS custlevel FROM bigdata.t_acquire_detail WHERE createTime BETWEEN str_to_date('2020-08-09', '%Y-%m-%d') AND str_to_date('2020-08-10', '%Y-%m-%d')";
		
		Statement statement = null;
		Connection conn = null;
		// 上次执行时间点 20180618180000
		try {
			Class.forName(MysqlDataSource.driver).newInstance();
			conn = DriverManager.getConnection(MysqlDataSource.url, MysqlDataSource.user, MysqlDataSource.password);
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				AcquireDetail acquireDetail = new AcquireDetail();
				
				acquireDetail.setEventid(rs.getString("eventid")) ;
				acquireDetail.setMerchantid(rs.getString("merchantid")) ;
				acquireDetail.setAccesstype(rs.getString("accesstype")) ;
				acquireDetail.setTransid(rs.getString("transid")) ;
				acquireDetail.setAcsorderid(rs.getString("acsorderid")) ;
				acquireDetail.setTradetype(rs.getString("tradetype")) ;
				acquireDetail.setSiteid(rs.getString("siteid")) ;
				acquireDetail.setRegisteruserid(rs.getString("registeruserid")) ;
				acquireDetail.setTranschannel(rs.getString("transchannel")) ;
				acquireDetail.setPaymethod(rs.getString("paymethod")) ;
				acquireDetail.setTerminaltype(rs.getString("terminaltype")) ;
				acquireDetail.setOrigpaytype(rs.getString("origpaytype")) ;
				acquireDetail.setOrigtransid(rs.getString("origtransid")) ;
				acquireDetail.setTranstimeout(rs.getString("transtimeout")) ;
				acquireDetail.setCurrencycode(rs.getString("currencycode")) ;
				acquireDetail.setCurrencyamount(rs.getString("currencyamount")) ;
				acquireDetail.setAmount(rs.getString("amount"));
				acquireDetail.setGoodsname(rs.getString("goodsname")) ;
				acquireDetail.setGoodsdesc(rs.getString("goodsdesc"));
				acquireDetail.setBilladdress(rs.getString("billaddress")) ;
				acquireDetail.setBillcity(rs.getString("billcity")) ;
				acquireDetail.setBillcountrycode(rs.getString("billcountrycode"));
				acquireDetail.setBillemail(rs.getString("billemail")) ;
				acquireDetail.setBillfirstname(rs.getString("billfirstname")) ;
				acquireDetail.setBilllastname(rs.getString("billlastname")) ;
				acquireDetail.setBillphonecity(rs.getString("billphonecity"));
				acquireDetail.setBillphonenumber(rs.getString("billphonenumber")) ;
				acquireDetail.setBillpostalcode(rs.getString("billpostalcode")) ;
				acquireDetail.setBillstate(rs.getString("billstate")) ;
				acquireDetail.setCardemail(rs.getString("cardemail"));
				acquireDetail.setCardfirstname(rs.getString("cardfirstname"));
				acquireDetail.setCardlastname(rs.getString("cardlastname")) ;
				acquireDetail.setCardno(rs.getString("cardno")) ;
				acquireDetail.setCardnocountryname(rs.getString("cardnocountryname")) ;
				acquireDetail.setCardphonenumber(rs.getString("cardphonenumber")) ;
				acquireDetail.setCashierip(rs.getString("cashierip")) ;
				acquireDetail.setCashieripcountry(rs.getString("cashieripcountry"));
				acquireDetail.setCustomerip(rs.getString("customerip"));
				acquireDetail.setCustomeripcountry(rs.getString("customeripcountry")) ;
				acquireDetail.setFingerprintid(rs.getString("fingerprintid"));
				acquireDetail.setRisklevel(rs.getString("risklevel")) ;
				acquireDetail.setShippingaddress(rs.getString("shippingaddress"));
				acquireDetail.setShippingcity(rs.getString("shippingcity")) ;
				acquireDetail.setShippingcountrycode(rs.getString("shippingcountrycode")) ;
				acquireDetail.setShippingemail(rs.getString("shippingemail")) ;
				acquireDetail.setShippingfirstname(rs.getString("shippingfirstname")) ;
				acquireDetail.setShippinglastname(rs.getString("shippinglastname")) ;
				acquireDetail.setShippingphonenumber(rs.getString("shippingphonenumber")) ;
				acquireDetail.setShippingpostalcode(rs.getString("shippingpostalcode"));
				acquireDetail.setShippingstate(rs.getString("shippingstate")) ;
				acquireDetail.setSubmittime(rs.getString("submittime")) ;
				acquireDetail.setXddid(rs.getString("xddid")) ;
				acquireDetail.setOrigacsorderid(rs.getString("origacsorderid"));
				acquireDetail.setCbkcode(rs.getString("cbkcode")) ;
				acquireDetail.setCbkmsg(rs.getString("cbkmsg")) ;
				acquireDetail.setCreatetime(rs.getString("createtime"));
				acquireDetail.setDealstatus(rs.getString("dealstatus"));
				acquireDetail.setValue2(rs.getString("value2")) ;
				acquireDetail.setDecision(rs.getString("decision")) ;
				acquireDetail.setRiskcode(rs.getString("riskcode")) ;
				acquireDetail.setRiskmsg(rs.getString("riskmsg")) ;
				acquireDetail.setCardorg(rs.getString("cardorg"));
				acquireDetail.setCardnocountrynamea2(rs.getString("cardnocountrynamea2")) ;
				acquireDetail.setRiskruleid(rs.getString("riskruleid"));
				acquireDetail.setRiskruletype(rs.getString("riskruletype"));
				acquireDetail.setShippingname(rs.getString("shippingname")) ;
				acquireDetail.setOrderrespcode(rs.getString("orderrespcode")) ;
				acquireDetail.setOrderrespmsg(rs.getString("orderrespmsg")) ;
				acquireDetail.setOrderstatus(rs.getString("orderstatus")) ;
				acquireDetail.setOrgcode(rs.getString("orgcode"));
				acquireDetail.setCompletetime(rs.getString("completetime")) ;
				acquireDetail.setUpdatetime(rs.getString("updatetime")) ;
				acquireDetail.setRiskassessmentonly(rs.getString("riskassessmentonly")) ;
				acquireDetail.setCompensation(rs.getString("compensation"));
				acquireDetail.setDecisiondetail(rs.getString("decisiondetail")) ;
				acquireDetail.setMaskcardno(rs.getString("maskcardno")) ;
				acquireDetail.setRepolabelmonitorblack(rs.getString("repolabelmonitorblack")) ;
				acquireDetail.setRepolabelmonitorwhite(rs.getString("repolabelmonitorwhite")) ;
				acquireDetail.setCustlevel(rs.getString("custlevel")) ;
				
				list.add(acquireDetail);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
//			writeLogFile(failPath, sql + ";");

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}
	
	public static void batchInsert(List<AcquireDetail> list){

		Connection conn = null;
		// 上次执行时间点 20180618180000
		try {
			Class.forName(PostgresqlDataSource.driver).newInstance();
			conn = DriverManager.getConnection(PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password);
			
			conn.setAutoCommit(false);
			java.sql.PreparedStatement cmd = conn.prepareStatement("insert into datacenter.t_acquire_detail(eventid, merchantid, accesstype, transid, acsorderid, tradetype, siteid, registeruserid, transchannel, paymethod, terminaltype, origpaytype, origtransid, transtimeout, currencycode, currencyamount, amount, goodsname, goodsdesc, billaddress, billcity, billcountrycode, billemail, billfirstname, billlastname, billphonecity, billphonenumber, billpostalcode, billstate, cardemail, cardfirstname, cardlastname, cardno, cardnocountryname, cardphonenumber, cashierip, cashieripcountry, customerip, customeripcountry, fingerprintid, risklevel, shippingaddress, shippingcity, shippingcountrycode, shippingemail, shippingfirstname, shippinglastname, shippingphonenumber, shippingpostalcode, shippingstate, submittime, xddid, origacsorderid, cbkcode, cbkmsg, createtime, dealstatus, value2, decision, riskcode, riskmsg, cardorg, cardnocountrynamea2, riskruleid, riskruletype, shippingname, orderrespcode, orderrespmsg, orderstatus, orgcode, completetime, updatetime, riskassessmentonly, compensation, decisiondetail, maskcardno, repolabelmonitorblack, repolabelmonitorwhite, custlevel) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CAST(? as TIMESTAMP), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CAST(? as TIMESTAMP), ?, ?, ?, ?, ?, ?, ?)");
			
			for(int i=0; i< list.size(); i++){
				AcquireDetail record = list.get(i);
				cmd.setString(1, record.getEventid());
				cmd.setString(2, record.getMerchantid());
				cmd.setString(3, record.getAccesstype());
				cmd.setString(4, record.getTransid());
				cmd.setString(5, record.getAcsorderid());
				cmd.setString(6, record.getTradetype());
				cmd.setString(7, record.getSiteid());
				cmd.setString(8, record.getRegisteruserid());
				cmd.setString(9, record.getTranschannel());
				cmd.setString(10, record.getPaymethod());
				cmd.setString(11, record.getTerminaltype());
				cmd.setString(12, record.getOrigpaytype());
				cmd.setString(13, record.getOrigtransid());
				cmd.setString(14, record.getTranstimeout());
				cmd.setString(15, record.getCurrencycode());
				cmd.setString(16, record.getCurrencyamount());
				cmd.setString(17, record.getAmount());
				cmd.setString(18, record.getGoodsname());
				cmd.setString(19, record.getGoodsdesc());
				cmd.setString(20, record.getBilladdress());
				cmd.setString(21, record.getBillcity());
				cmd.setString(22, record.getBillcountrycode());
				cmd.setString(23, record.getBillemail());
				cmd.setString(24, record.getBillfirstname());
				cmd.setString(25, record.getBilllastname());
				cmd.setString(26, record.getBillphonecity());
				cmd.setString(27, record.getBillphonenumber());
				cmd.setString(28, record.getBillpostalcode());
				cmd.setString(29, record.getBillstate());
				cmd.setString(30, record.getCardemail());
				cmd.setString(31, record.getCardfirstname());
				cmd.setString(32, record.getCardlastname());
				cmd.setString(33, record.getCardno());
				cmd.setString(34, record.getCardnocountryname());
				cmd.setString(35, record.getCardphonenumber());
				cmd.setString(36, record.getCashierip());
				cmd.setString(37, record.getCashieripcountry());
				cmd.setString(38, record.getCustomerip());
				cmd.setString(39, record.getCustomeripcountry());
				cmd.setString(40, record.getFingerprintid());
				cmd.setString(41, record.getRisklevel());
				cmd.setString(42, record.getShippingaddress());
				cmd.setString(43, record.getShippingcity());
				cmd.setString(44, record.getShippingcountrycode());
				cmd.setString(45, record.getShippingemail());
				cmd.setString(46, record.getShippingfirstname());
				cmd.setString(47, record.getShippinglastname());
				cmd.setString(48, record.getShippingphonenumber());
				cmd.setString(49, record.getShippingpostalcode());
				cmd.setString(50, record.getShippingstate());
				cmd.setString(51, record.getSubmittime());
				cmd.setString(52, record.getXddid());
				cmd.setString(53, record.getOrigacsorderid());
				cmd.setString(54, record.getCbkcode());
				cmd.setString(55, record.getCbkmsg());
				cmd.setString(56, record.getCreatetime());
				cmd.setString(57, record.getDealstatus());
				cmd.setString(58, record.getValue2());
				cmd.setString(59, record.getDecision());
				cmd.setString(60, record.getRiskcode());
				cmd.setString(61, record.getRiskmsg());
				cmd.setString(62, record.getCardorg());
				cmd.setString(63, record.getCardnocountrynamea2());
				cmd.setString(64, record.getRiskruleid());
				cmd.setString(65, record.getRiskruletype());
				cmd.setString(66, record.getShippingname());
				cmd.setString(67, record.getOrderrespcode());
				cmd.setString(68, record.getOrderrespmsg());
				cmd.setString(69, record.getOrderstatus());
				cmd.setString(70, record.getOrgcode());
				cmd.setString(71, record.getCompletetime());
				cmd.setString(72, record.getUpdatetime());
				cmd.setString(73, record.getRiskassessmentonly());
				cmd.setString(74, record.getCompensation());
				cmd.setString(75, record.getDecisiondetail());
				cmd.setString(76, record.getMaskcardno());
				cmd.setString(77, record.getRepolabelmonitorblack());
				cmd.setString(78, record.getRepolabelmonitorwhite());
				cmd.setString(79, record.getCustlevel());
				
				
				cmd.addBatch();
				
				if(i%500==0 && i > 0){
					cmd.executeBatch();
					
					System.out.println("提交："+i);
				}
			}
			
			cmd.executeBatch();
			conn.commit();
			
			cmd.close();
			
		} catch (Exception e) {
			e.printStackTrace();
//			writeLogFile(failPath, sql + ";");

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

	public static void main(String[] args) {
		List<AcquireDetail> list = DataSyncTask.queryFromMysql();
		System.out.println(list.size()+"---");
        DataSyncTask.batchInsert(list);
	}

}
