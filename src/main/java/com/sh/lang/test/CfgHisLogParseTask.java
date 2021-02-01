package com.sh.lang.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.io.eval.IFFTEvaluator;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.sh.lang.retry.OracleDataSource;
import com.sh.lang.retry.pg.PostgresqlDataSource;
import com.sh.lang.retry.pg.PostgresqlDataSourceDev;
import com.sh.lang.utils.MD5Util;

/**
 * 商户历史计费解析 
 */
public class CfgHisLogParseTask {
	
	public void writeLogFile(String fileName, String text) {
		File file = new File(fileName);

		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
			writer.write(text + System.getProperty("line.separator"));
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public List<MerChargeCfgLog> queryLog(){
		List<MerChargeCfgLog> list = new ArrayList<MerChargeCfgLog>();
		String sql = "SELECT log_id,cfg_id,merchant_id,merchant_name,prod_code,calc_accuracy,contract,effective_date,expiry_date,operate_user,check_user,record_status,useable,to_char(gmt_create_time,'yyyyMMddHH24miss') gmt_create_time,to_char(gmt_update_time,'yyyyMMddHH24miss') as gmt_update_time,chg_json from ccs.t_ccs_mer_charge_cfg_log WHERE RECORD_STATUS = '05' AND merchant_id not in (SELECT merchant_id from mes.ipl_test_merchant) ORDER BY gmt_create_time ASC";
		
		Statement statement = null;
		Connection conn = null;
	
		// 上次执行时间点 20180618180000
		try {
			Class.forName(PostgresqlDataSource.driver).newInstance();
			conn = DriverManager.getConnection(PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password);
			conn.setAutoCommit(false);
			statement = conn.createStatement();// 准备PreparedStatement
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				MerChargeCfgLog log = new MerChargeCfgLog();
				log.setCfgId(rs.getInt("cfg_id")+"");
				log.setLogId(rs.getInt("log_id")+"");
				log.setMerchantId(rs.getString("merchant_id"));
				log.setMerchantName(rs.getString("merchant_name"));
				log.setProdCode(rs.getString("prod_code"));
                log.setCalcAccuracy(rs.getString("calc_accuracy"));
                log.setContract(rs.getString("contract"));
                log.setEffectiveDate(rs.getString("effective_date"));
                log.setExpiryDate(rs.getString("expiry_date"));
                log.setOperateUser(rs.getString("operate_user"));
                log.setCheckUser(rs.getString("check_user"));
                log.setRecordStatus(rs.getString("record_status"));
                log.setUseable(rs.getString("useable"));
                SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
                log.setGmtCreateTime(sf.parse(rs.getString("gmt_create_time")));
                log.setGmtUpdateTime(sf.parse(rs.getString("gmt_update_time")));
                log.setChgJson(rs.getString("chg_json"));
                
           
                
                list.add(log);				
			}
			
			System.out.println("查询完成");
		} catch (Exception e) {
			e.printStackTrace();
			

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
	
	
	public List<String> queryAcsOrderId(){
		List<String> list = new ArrayList<String>();
		String sql = "SELECT id FROM acquire.t_acquire_order WHERE gmt_create_time >= CAST ( '20200401033000' AS TIMESTAMP )  AND gmt_create_time < CAST ( '20200406230000' AS TIMESTAMP ) 	AND merchant_id NOT IN ( SELECT merchant_id FROM mes.ipl_test_merchant ) 	AND order_type in  ('sale','authorization','creat_token','creat_token_sale','creat_token_auth','capture','token_sale','token_auth') AND NOT EXISTS (SELECT acs_order_id from datacenter.t_purchase_order where acs_order_id = id)";		
		Statement statement = null;
		Connection conn = null;
	
		// 上次执行时间点 20180618180000
		try {
			Class.forName(PostgresqlDataSource.driver).newInstance();
			conn = DriverManager.getConnection(PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password);
			conn.setAutoCommit(false);
			statement = conn.createStatement();// 准备PreparedStatement
			ResultSet rs = statement.executeQuery(sql);
			java.lang.StringBuilder builder = new StringBuilder();
			int i=0;
			while(rs.next()){
				if(i == 0){
					builder.append("(");
				}
				
				if(i == 1000){
//					System.out.println(builder.toString().substring(0, builder.toString().length()-1)+")");
					writeLogFile("D://log//acs_order_id.txt", builder.toString().substring(0, builder.toString().length()-1)+")");
					i=0;
					builder = new StringBuilder();
				}
				else{
					builder.append("'")
					.append(rs.getLong("id")).append("',");
					i++;
				}
			}
			
			System.out.println("查询完成");
		} catch (Exception e) {
			e.printStackTrace();
			

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
	
	
	public void parseResultAndInsert(List<MerChargeCfgLog> list) throws Exception{
		List<MerchantCfgHisLog> logList = new ArrayList<MerchantCfgHisLog>();
		Map<String, Date> tmpMap = new HashMap<String, Date>();
		List<CcsLadderFeeHis> ladderFeeHisList = new ArrayList<CcsLadderFeeHis>();
		for(int i=0; i< list.size(); i++){
			MerChargeCfgLog cfgLog = list.get(i);
			MerchantCfgHisLog hisLog = new MerchantCfgHisLog();
			hisLog.setBatchNo(System.currentTimeMillis()+"");
//			hisLog.setId(Long.parseLong(cfgLog.getCfgId()));
			hisLog.setMerchantId(cfgLog.getMerchantId());
			hisLog.setMerchantName(cfgLog.getMerchantName());
			hisLog.setGmtCreateTime(cfgLog.getGmtCreateTime());
			hisLog.setGmtUpdateTime(cfgLog.getGmtUpdateTime());

			hisLog.setProdCode(cfgLog.getProdCode());
//			hisLog.setExpireTime(cfgLog.getGmtCreateTime());
			SimpleDateFormat sf = new SimpleDateFormat("yyyymmdd");
			if(tmpMap.get(cfgLog.getMerchantId()) == null){
				hisLog.setEffectiveTime(sf.parse(cfgLog.getEffectiveDate()));
			}
			else{
				hisLog.setEffectiveTime(tmpMap.get(cfgLog.getMerchantId()));
			}
			
			hisLog.setExpireTime(cfgLog.getGmtCreateTime());
			tmpMap.put(cfgLog.getMerchantId(), cfgLog.getGmtCreateTime());
			
//			 查询失效日期
//			for(int j=i+1; j< list.size(); j++){
//				MerChargeCfgLog cfgLog2 = list.get(j);
//				if(cfgLog2.getMerchantId() == cfgLog.getMerchantId()){
//					
//					break;
//				}
//				
//			}			
			
			GsonBuilder builder = new GsonBuilder();

	        // Register an adapter to manage the date types as long values
	        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
	            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
	                return new Date(json.getAsJsonPrimitive().getAsLong());
	            }
	        });
			
			Gson json = builder.create();
//			System.out.println(cfgLog.getChgJson());
			MerChargeCfgDetailDTO dto = json.fromJson(cfgLog.getChgJson(), MerChargeCfgDetailDTO.class);			
			
			hisLog.setAccOpenFee(dto.getAccOpenFee());
			hisLog.setAccOpenFeeCy(dto.getAccOpenFeeCy());
			hisLog.setAnnualTechMtcCollDate(dto.getAnnualTechMtcCollDate());
			hisLog.setAnnualTechMtcFee(dto.getAnnualTechMtcFee());
			hisLog.setAnnualTechMtcFeeCy(dto.getAnnualTechMtcFeeCy());
			hisLog.setCalcAccuracy(dto.getCalcAccuracy());
		    hisLog.setCnyWithdrawFee(dto.getCnyWithdrawFee());
		    hisLog.setCnyWithdrawFeeCy(dto.getCnyWithdrawFeeCy());
		    hisLog.setContract(dto.getContract());
		    hisLog.setFixedMargin(dto.getFixedMargin());
		    hisLog.setFixedMarginCy(dto.getFixedMarginCy());
		    hisLog.setForgnCyWithdrawFee(dto.getForgnCyWithdrawFee());
		    hisLog.setForgnCyWithdrawFeeCy(dto.getForgnCyWithdrawFeeCy());
		    hisLog.setFrdFeeRefundType(dto.getFraudFeeRefundType());
		    hisLog.setFrdPayoutFeePct(dto.getFrdPayoutFeePct());
		    hisLog.setFrdPayoutFeePctNotAcq(dto.getFrdPayoutFeePctNotAcq());
		    hisLog.setOperateUser(dto.getOperateUser());
		    hisLog.setRecordStatus(dto.getRecordStatus());
		    hisLog.setRefundProcFee(dto.getRefundProcFee());
		    hisLog.setRefundProcFeeCy(dto.getRefundProcFeeCy());
		    if(StringUtils.isNotEmpty(dto.getRejectionDealFee())){
		        hisLog.setRejectionDealFee(new BigDecimal(dto.getRejectionDealFee()));
		    }
		
		    hisLog.setRejectionDealFeeCurrency(dto.getRejectionDealFeeCurrency());
		    hisLog.setRejectionDealFeeType(dto.getRejectionDealFeeType());
		    hisLog.setSysProcFee(dto.getSysProcFee());
		    hisLog.setSysProcFeeCy(dto.getSysProcFeeCy());
		    hisLog.setThreeDJudgFee(dto.getThreeDJudgFee());
		    hisLog.setThreeDJudgFeeCy(dto.getThreeDProcFeeCy());
		    hisLog.setThreeDProcFee(dto.getThreeDProcFee());
		    hisLog.setThreeDProcFeeCy(dto.getThreeDProcFeeCy());
		    
		    List<MerChargeCfgBaseDTO> baseList = dto.getMerChargeCfgBaseDTOList();
		    MerChargeCfgBaseDTO baseDTO = baseList.get(0);
		    hisLog.setChargeMode(baseDTO.getChargeMode());
		    hisLog.setChargeType(baseDTO.getChargeType());
		    hisLog.setPctFeeRefundType(baseDTO.getPctFeeRefundType());;
		    hisLog.setFixedFeeRefundType(baseDTO.getFixedFeeRefundType());
		    hisLog.setObjectOfCharge(baseDTO.getObjectOfCharge());
		    
		    
		    List<MerChargeCfgBaseDtlDTO> dtlList = baseDTO.getMerChargeCfgBaseDtlDTOList();
		    for(int k=0; k< dtlList.size(); k++){
		    	MerChargeCfgBaseDtlDTO dtlDTO =  dtlList.get(k);
		    	MerchantCfgHisLog tmpLog = new MerchantCfgHisLog();
		    	BeanUtils.copyProperties(hisLog, tmpLog);
		    	
//		    	SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		    	tmpLog.setId();
		    	tmpLog.setDtlId(k+1);
		    	tmpLog.setBillingBasis(dtlDTO.getBillingBasis());
		    	tmpLog.setBillingCurrency(dtlDTO.getBillingCurrency());
		    	tmpLog.setBillingCycle(dtlDTO.getBillingCycle());
		    	tmpLog.setCardOrg(dtlDTO.getCardOrg());
		    	tmpLog.setCountryCode(dtlDTO.getCountryCode());
		    	tmpLog.setPriority(dtlDTO.getPriority());
		    	tmpLog.setRegion(dtlDTO.getRegion());
		    	tmpLog.setTransactionMode(dtlDTO.getTransactionMode());
		    	tmpLog.setChannelCode(dtlDTO.getChannelCode());
		    	tmpLog.setSubChannelCode(dtlDTO.getSubChannelCode());
		    	tmpLog.setFixedFeeAmt(dtlDTO.getFixedFeeAmt());
		    	tmpLog.setFixedFeeCurrency(dtlDTO.getFixedFeeCurrency());
		    	tmpLog.setPercent(dtlDTO.getPercent());
		    	tmpLog.setPctMinFee(dtlDTO.getPctMinFee());
		    	tmpLog.setPctFeeCurrency(dtlDTO.getPctFeeCurrency());
		    	tmpLog.setIssueCurrency(dtlDTO.getIssueCurrency());
//		    	Thread.currentThread().sleep(2);
		    	// 获取阶梯计费
		    	List<MerChargeLadderChargeDTO> ladderChargeDTOs = dtlDTO.getMerChargeLadderChargeDTOList();
		    	if(ladderChargeDTOs != null && ladderChargeDTOs.size() > 0){
		    		for(MerChargeLadderChargeDTO ladderChargeDTO : ladderChargeDTOs){
		    			CcsLadderFeeHis ccsLadderFeeHis = new CcsLadderFeeHis();
		    			ccsLadderFeeHis.setDtlId(tmpLog.getDtlId());
//		    			ccsLadderFeeHis.setConditionId(ladderChargeDTO.gets);
		    			ccsLadderFeeHis.setlValue(ladderChargeDTO.getlValue());
		    			ccsLadderFeeHis.setlValueSymbol(ladderChargeDTO.getlValueSymbol());
		    			ccsLadderFeeHis.setLrvalueRelation(ladderChargeDTO.getLrvalueRelation());
		    			ccsLadderFeeHis.setrValue(ladderChargeDTO.getrValue());
		    			ccsLadderFeeHis.setrValueSymbol(ladderChargeDTO.getrValueSymbol());
		    			ccsLadderFeeHis.setUseable(ladderChargeDTO.getUseable());
		    			ccsLadderFeeHis.setPct(ladderChargeDTO.getPctPercent());
		    			ccsLadderFeeHis.setMinPct(ladderChargeDTO.getmPctFeeAmount());
		    			ccsLadderFeeHis.setMinPctCurrency(ladderChargeDTO.getmPctFeeCurrency());
		    			ccsLadderFeeHis.setFixedFee(ladderChargeDTO.getFixedFeeAmount());
		    			ccsLadderFeeHis.setFixedFeeCurrency(ladderChargeDTO.getFixedFeeCurrency());
		    			
		    			ladderFeeHisList.add(ccsLadderFeeHis);
		    		}
		    	}
		    	
		    	logList.add(tmpLog);
		    	
		        if(tmpLog.getMerchantId().equals("500000000000043")){
		        	System.out.println("-------"+new Gson().toJson(tmpLog));
                }
		    	
		    	
		    }
		    
//		    System.out.println("-----------------------------------");
		    
		}
		// 开始插入数据库
		insertHisLog(logList);
		insertHisLadder(ladderFeeHisList);
	}
	
	public void insertHisLog(List<MerchantCfgHisLog> list){
				
	
		Connection conn = null;
	
		// 上次执行时间点 20180618180000
		try {
			Class.forName(PostgresqlDataSource.driver).newInstance();
			conn = DriverManager.getConnection(PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password);
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement("insert into REPORT.T_CCS_MER_CHARGE_CFG_HIS(dtl_id, merchant_id, merchant_name, prod_code, calc_accuracy, contract, sys_proc_fee_fail, sys_proc_fee_fail_cy, sys_proc_fee, sys_proc_fee_cy, cny_withdraw_fee, cny_withdraw_fee_cy, forgn_cy_withdraw_fee, forgn_cy_withdraw_fee_cy, three_d_proc_fee, three_d_proc_fee_cy, three_d_judg_fee, three_d_judg_fee_cy, refund_proc_fee, refund_proc_fee_cy, acc_open_fee, acc_open_fee_cy, annual_tech_mtc_fee, annual_tech_mtc_fee_cy, fixed_margin, fixed_margin_cy, operate_user, check_user, record_status, useable, gmt_create_time, gmt_update_time, effective_time, expire_time, frd_payout_fee_pct, frd_payout_fee_pct_not_acq, frd_fee_refund_type, rejection_deal_fee, rejection_deal_fee_currency, rejection_deal_fee_type, annual_tech_mtc_coll_date, fixed_margin_coll_date, acc_open_fee_coll_date, base_id, charge_type, charge_mode, pct_fee_refund_type, fixed_fee_refund_type, object_of_charge, region,country_code, card_org, transaction_mode, channel_code, sub_channel_code, fixed_fee_amt, fixed_fee_currency, percent, pct_min_fee, pct_fee_currency, priority, issue_currency, billing_basis, billing_cycle, billing_currency, batch_no,id) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			int num =0;
			for(MerchantCfgHisLog log : list){
				
				if(log.getMerchantId().equals("500000000000043")){
		        	System.out.println(new Gson().toJson(log));
                }
				
//				System.out.println(log.getId());
				statement.setInt(1, log.getDtlId());
				statement.setString(2, log.getMerchantId());
				statement.setString(3, log.getMerchantName());
				statement.setString(4, log.getProdCode());
				statement.setString(5, log.getCalcAccuracy());
				statement.setString(6, log.getContract());
				statement.setBigDecimal(7, log.getSysProcFeeFail());
				statement.setString(8, log.getSysProcFeeFailCy());
				statement.setBigDecimal(9, log.getSysProcFee());
				statement.setString(10, log.getSysProcFeeCy());
				statement.setBigDecimal(11, log.getCnyWithdrawFee());
				statement.setString(12, log.getCnyWithdrawFeeCy());
				statement.setBigDecimal(13, log.getForgnCyWithdrawFee());
				statement.setString(14, log.getForgnCyWithdrawFeeCy());
				statement.setBigDecimal(15, log.getThreeDProcFee());
				statement.setString(16, log.getThreeDProcFeeCy());
				statement.setBigDecimal(17, log.getThreeDJudgFee());
				statement.setString(18, log.getThreeDJudgFeeCy());
				statement.setBigDecimal(19, log.getRefundProcFee());
				statement.setString(20, log.getRefundProcFeeCy());
				statement.setBigDecimal(21, log.getAccOpenFee());
				statement.setString(22, log.getAccOpenFeeCy());
				statement.setBigDecimal(23, log.getAnnualTechMtcFee());
				statement.setString(24, log.getAnnualTechMtcFeeCy());
				statement.setBigDecimal(25, log.getFixedMargin());
				statement.setString(26, log.getFixedMarginCy());
				statement.setString(27, log.getOperateUser());
				statement.setString(28, log.getCheckUser());
				statement.setString(29, log.getRecordStatus());
				statement.setString(30, log.getUseable());
//				statement.setDate(31, new java.sql.Date(log.getGmtCreateTime().getTime()) );
//				statement.setDate(32, new java.sql.Date(log.getGmtUpdateTime().getTime()) );
//				statement.setDate(33, new java.sql.Date(log.getEffectiveTime().getTime()) );
//				statement.setDate(34, new java.sql.Date(log.getExpireTime().getTime()) );
				
				statement.setTimestamp(31, new Timestamp(log.getGmtCreateTime().getTime()) );
				statement.setTimestamp(32, new Timestamp(log.getGmtUpdateTime().getTime()) );
				statement.setTimestamp(33, new Timestamp(log.getEffectiveTime().getTime()) );
				statement.setTimestamp(34, new Timestamp(log.getExpireTime().getTime()) );
				
				statement.setBigDecimal(35, log.getFrdPayoutFeePct());
				statement.setBigDecimal(36, log.getFrdPayoutFeePctNotAcq());
				statement.setString(37, log.getFrdFeeRefundType());
				statement.setBigDecimal(38, log.getRejectionDealFee());
				statement.setString(39, log.getRejectionDealFeeCurrency());
				statement.setString(40, log.getRejectionDealFeeType());
				statement.setString(41, log.getAnnualTechMtcCollDate());
				statement.setString(42, log.getFixedMarginCollDate());
				statement.setString(43, log.getAccOpenFeeCollDate());
				statement.setLong(44, log.getBaseId());
				statement.setString(45, log.getChargeType());
				statement.setString(46, log.getChargeMode());
				statement.setString(47, log.getPctFeeRefundType());
				statement.setString(48, log.getFixedFeeRefundType());
				statement.setString(49, log.getObjectOfCharge());
				statement.setString(50, log.getRegion());
				statement.setString(51, log.getCountryCode());
				statement.setString(52, log.getCardOrg());					
				statement.setString(53, log.getTransactionMode());
				statement.setString(54, log.getChannelCode());
				statement.setString(55, log.getSubChannelCode());
				statement.setBigDecimal(56, log.getFixedFeeAmt());
				statement.setString(57, log.getFixedFeeCurrency());
				statement.setBigDecimal(58, log.getPercent());
				statement.setBigDecimal(59, log.getPctMinFee());
				statement.setString(60, log.getPctFeeCurrency());
				statement.setInt(61, log.getPriority());
				statement.setString(62, log.getIssueCurrency());
				statement.setString(63, log.getBillingBasis());
				statement.setString(64, log.getBillingCycle());
				statement.setString(65, log.getBillingCurrency());
				statement.setString(66, log.getBatchNo());
	            statement.setString(67, String.valueOf(System.nanoTime()));
				statement.addBatch();
			
				num++;
				if(num%10 == 0){
					statement.executeBatch();
				}
			}
			statement.executeBatch();
			conn.commit();
			statement.close();
			
			System.out.println("插入完成");
		} catch (Exception e) {
			e.printStackTrace();
			

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
	
	public void insertHisLadder(List<CcsLadderFeeHis> list){
		
		
		Connection conn = null;
		Map<String, String> map = new HashMap<String, String>();
	
		// 上次执行时间点 20180618180000
		try {
			Class.forName(PostgresqlDataSource.driver).newInstance();
			
			
			
			conn = DriverManager.getConnection(PostgresqlDataSource.url, PostgresqlDataSource.user, PostgresqlDataSource.password);
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement("insert into REPORT.T_CCS_LADDER_FEE_CFC_HIS(dtl_id, l_value, r_value, useable, pct, min_pct, min_pct_currency, fixed_fee_currency, fixed_fee)values(?,?,?,?,?,?,?,?,?)");
			
			int num =0;
			for(CcsLadderFeeHis log : list){
				String keyString = MD5Util.md5(new Gson().toJson(log));
				
				if(map.containsKey(keyString)){
					continue;
				}
				map.put(keyString, keyString);
				
				statement.setLong(1, log.getDtlId());
				statement.setBigDecimal(2, log.getlValue());
				if(StringUtils.isEmpty( log.getLrvalueRelation())){
					statement.setBigDecimal(3, new BigDecimal("9999999999999"));
				}
				else{
					statement.setBigDecimal(3, log.getrValue());
				}
			
				
				statement.setString(4, log.getUseable());
				statement.setBigDecimal(5, log.getPct());
				statement.setBigDecimal(6, log.getMinPct());
				statement.setString(7, log.getMinPctCurrency());
				statement.setString(8, log.getFixedFeeCurrency());
				statement.setBigDecimal(9, log.getFixedFee());
				statement.addBatch();
			
				num++;
				if(num%10 == 0){
					statement.executeBatch();
				}
			}
			statement.executeBatch();
			conn.commit();
			statement.close();
			
			System.out.println("插入完成");
		} catch (Exception e) {
			e.printStackTrace();
			

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
		CfgHisLogParseTask task = new CfgHisLogParseTask();
		try {
//			task.queryAcsOrderId();
			List<MerChargeCfgLog> cfgLogs = task.queryLog();
			System.out.println(cfgLogs.size());
//			
			 task.parseResultAndInsert(cfgLogs);
//			
//			task.insertDB(hisLogs);
			
//			System.out.println(new Gson().toJson(hisLogs));
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		

	}

}
