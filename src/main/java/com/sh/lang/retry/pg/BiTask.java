package com.sh.lang.retry.pg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.sh.lang.test.MerchantCfgHisLog;

public class BiTask {
	public static String url = "jdbc:postgresql://106.14.49.137:3432/riskdb";
	public static String user = "dev_ly";
	public static String password = "6gYUlZ4mnMhyyszp";

	public Connection getConnection() throws Exception {
		Class.forName("org.postgresql.Driver").newInstance();
		return DriverManager.getConnection(url, user, password);
	}

	public static List<String> getSqlList(String startTime) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date date = sfDateFormat.parse(startTime); // 20180709160000
			Date newDate = null;
			for (int i = 1; i < 9600; i++) {
				newDate = DateUtils.addHours(date, 1 * 8);
				if (newDate.compareTo(sfDateFormat.parse("20200320000000")) <= 0) {
					String sql = "SELECT report.f_tmp_refund_fee_info('"
							+ sfDateFormat.format(date) + "','"
							+ sfDateFormat.format(newDate) + "')";
					// String sql =
					// "SELECT datacenter.t_purchase_order_summarization('"+sfDateFormat.format(date)+"','"+sfDateFormat.format(newDate)+"')";
					list.add(sql);
					date = newDate;
				} else {
					break;
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static void writeErrorFile(String fileName, String text) {
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

	public static void refundBiTask() {

		BiTask jobMain = new BiTask();
		CallableStatement statement = null;
		Connection conn = null;
		// 上次执行时间点 20200225000000
		List<String> sqlList = getSqlList("20200320000000");

		// String sql =
		// "SELECT datacenter.t_payment_info_summarization('20181206160000','20181206220000')";

		for (String sql : sqlList) {
			try {
				conn = jobMain.getConnection();
				conn.setAutoCommit(false);
				statement = conn.prepareCall(sql);// 准备PreparedStatement
				statement.executeQuery();// 执行查询
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				writeErrorFile("D://tmp//bi_refund_error.txt", sql + ";");

			} finally {
				try {
					if (conn != null) {
						conn.close();
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			writeErrorFile("D://tmp//bi_refund_success.txt", sql + ";");
			System.out.println(sql + " 执行完成");
		}

		System.out.println("end");

	}

	private List<BiPurchaseFeeInfo> biPurchase(String startTime, String endTime) {
		List<BiPurchaseFeeInfo> list = new ArrayList<BiPurchaseFeeInfo>();
		String sql = "WITH tmp_order AS ( 	SELECT 		tao.merchant_id, 		tao.merchant_order_id, 		tao.ID, 		tao.order_type, 		tao.org_code, 		tac.order_currency, 		tac.order_amount, 		tao.gmt_create_time, 		tao.gmt_complete_time 	FROM 		acquire.t_acquire_order tao, 		acquire.t_acquire_order_context tac 	WHERE 		tao.ID = tac.ID 		AND tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 		AND tao.outer_status = 'success' 		AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 		AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP ) ) 		AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 	), 	tmp_trade_order AS ( 	SELECT 		t1.trade_order_no, 		t1.acs_order_id, 		t1.complete_date 	FROM 		fi.trade_order t1 	WHERE 		t1.status IN ( '3', '4' ) 		AND EXISTS ( 		SELECT ID 		FROM 			acquire.t_acquire_order tao 		WHERE 			t1.acs_order_id = tao.ID 			AND tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 			AND tao.outer_status = 'success' 			AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 			AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP )) 			AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 		) 	), 	tmp_payment_order AS ( 	SELECT 		fpo.trade_order_no, 		fpo.payment_order_no, 		fpo.pay_type, 		fpe.card_no 	FROM 		fi.payment_order fpo, 		fi.payment_order_expand fpe 	WHERE 		fpo.payment_order_no = fpe.payment_order_no 		AND fpo.status = '1' 		AND EXISTS ( 		SELECT 			t1.trade_order_no 		FROM 			fi.trade_order t1 		WHERE 			t1.trade_order_no = fpo.trade_order_no 			AND t1.status IN ( '3', '4' ) 			AND EXISTS ( 			SELECT ID 			FROM 				acquire.t_acquire_order tao 			WHERE 				t1.acs_order_id = tao.ID 				AND tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 				AND tao.outer_status = 'success' 				AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 				AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP ) ) 				AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 			) 		) 	), 	tmp_channel_order AS ( 	SELECT 		* 	FROM 		fi.channel_order fco 	WHERE 		status = '1' 		AND EXISTS ( 		SELECT 			fpo.payment_order_no 		FROM 			fi.payment_order fpo 		WHERE 			fpo.payment_order_no = fco.payment_order_no 			AND fpo.status = '1' 			AND EXISTS ( 			SELECT 				t1.trade_order_no 			FROM 				fi.trade_order t1 			WHERE 				t1.trade_order_no = fpo.trade_order_no 				AND t1.status IN ( '3', '4' ) 				AND EXISTS ( 				SELECT ID 				FROM 					acquire.t_acquire_order tao 				WHERE 					t1.acs_order_id = tao.ID 					AND tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 					AND tao.outer_status = 'success' 					AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 					AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP )) 					AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 				) 			) 		) 	), 	tmp_balance_entry AS ( 	SELECT 		mer_order_id, 		amt, 		payment_service_code, 		direction 	FROM 		acct.t_balance_entry 	WHERE 		mer_order_id IN ( 		SELECT 			tao.merchant_order_id 		FROM 			acquire.t_acquire_order tao 		WHERE 			tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 			AND tao.outer_status = 'success' 			AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 			AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP )) 			AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 		) 		AND payment_service_code IN ( '101005', '101006', '101007' ) 	), 	tmp_css_payment_order AS ( 	SELECT 		margin_amount, 		mer_order_id 	FROM 		css.t_payment_order 	WHERE 		mer_order_id IN ( 		SELECT 			tao.merchant_order_id 		FROM 			acquire.t_acquire_order tao 		WHERE 			tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 			AND tao.outer_status = 'success' 			AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 			AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP )) 			AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 		) 		AND deal_code = '101002' 	), 	tmp_margin_clearing_detail AS ( 	SELECT 		t1.amount, 		t2.mer_order_id, 		t1.TYPE, 		t2.deal_code 	FROM 		css.t_margin_clearing_detail t1, 		css.t_payment_order t2 	WHERE 		t1.order_id = t2.order_id 		AND t2.mer_order_id IN ( 		SELECT 			tao.merchant_order_id 		FROM 			acquire.t_acquire_order tao 		WHERE 			tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 			AND tao.outer_status = 'success' 			AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 			AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP ) ) 			AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 		) 		AND t2.deal_code = '101002' 	), 	tmp_payment_deal AS ( 	SELECT 		t1.exchange_rate, 		t2.acquire_order_id, 		t1.deal_code, 		t1.payment_service_code 	FROM 		css.t_payment_deal t1, 		css.t_payment_order t2 	WHERE 		1 = 1 		AND t2.txn_seq_no = t1.txn_seq_no 		AND t2.acquire_order_id IN ( 		SELECT 			tao.ID 		FROM 			acquire.t_acquire_order tao 		WHERE 			tao.merchant_id IN ( SELECT merchant_id FROM ccs.t_ccs_mer_charge_cfg WHERE merchant_id = tao.merchant_id ) 			AND tao.outer_status = 'success' 			AND tao.order_type IN ( 'sale', 'token_sale', 'create_token_sale', 'capture' ) 			AND ( tao.gmt_create_time >= CAST ( ? AS TIMESTAMP ) AND tao.gmt_create_time < CAST ( ? AS TIMESTAMP ) ) 			AND ( tao.prod_code IS NULL OR tao.prod_code = '10' ) 		) 	) SELECT 	tao.merchant_id, 	tao.merchant_order_id, 	'' || tao.ID AS acs_order_id, 	tao.order_type, 	tao.order_currency, 	tao.order_amount, 	to_char( tao.gmt_create_time, 'yyyy-mm-dd HH24:mi:ss' ) AS gmt_create_time, 	to_char( COALESCE ( tto.complete_date, tao.gmt_complete_time ), 'yyyy-mm-dd HH24:mi:ss' ) AS complete_date, 	tto.trade_order_no, 	fpo.payment_order_no, 	fpo.pay_type, 	substr( fpo.card_no, 1, 6 ) AS card_bin, 	fco.channel_order_no, 	fco.org_code AS channel_code, 	fco.pay_item_id, 	COALESCE ( cbi.card_org, fco.card_org ) AS card_org, 	cbi.issuer, 	cbi.card_type, 	cbi.issuer_country, 	cbi.country_code3, 	cbi.region, CASE 		 		WHEN tao.order_type IN ( 'token_sale', 'create_token_sale', 'capture' ) THEN 		'01' ELSE ( SELECT CASE WHEN COUNT ( rec_id ) > 0 THEN '02' ELSE'01' END AS charge_type FROM report.t_settle_local_channel_conf WHERE org_code = fco.org_code ) 	END AS charge_type, 	tcpo.margin_amount, 	tbe.amt AS ccs_amt_101005, 	tbe2.amt ccs_amt_101006, 	tbe3.amt ccs_amt_101007, 	tmcd.amount AS margin_amount_in, 	tmcd2.amount AS margin_amount_out, 	tpd2.exchange_rate AS settlement_rate, 	tpd2.exchange_rate AS fixed_settlement_rate FROM 	tmp_order tao 	LEFT JOIN tmp_trade_order tto ON tao.ID = tto.acs_order_id 	LEFT JOIN tmp_payment_order fpo ON fpo.trade_order_no = tto.trade_order_no 	LEFT JOIN tmp_channel_order fco ON fco.payment_order_no = fpo.payment_order_no 	LEFT JOIN inf.card_bin_inf cbi ON cbi.card_bin = substr( fpo.card_no, 1, 6 ) 	LEFT JOIN tmp_css_payment_order tcpo ON tcpo.mer_order_id = tao.merchant_order_id 	LEFT JOIN tmp_balance_entry tbe ON tbe.mer_order_id = tao.merchant_order_id 	AND tbe.payment_service_code = '101005' 	AND tbe.direction = '1' 	LEFT JOIN tmp_balance_entry tbe2 ON tbe2.mer_order_id = tao.merchant_order_id 	AND tbe2.payment_service_code = '101006' 	AND tbe2.direction = '2' 	LEFT JOIN tmp_balance_entry tbe3 ON tbe3.mer_order_id = tao.merchant_order_id 	AND tbe3.payment_service_code = '101007' 	AND tbe3.direction = '2' 	LEFT JOIN tmp_margin_clearing_detail tmcd ON tmcd.mer_order_id = tao.merchant_order_id 	AND tmcd.TYPE = '1' 	LEFT JOIN tmp_margin_clearing_detail tmcd2 ON tmcd2.mer_order_id = tao.merchant_order_id 	AND tmcd2.TYPE = '2' 	LEFT JOIN tmp_payment_deal tpd ON tpd.acquire_order_id = tao.ID 	AND tpd.deal_code IN ( '101002', '102002' ) 	AND tpd.payment_service_code = '101005' 	LEFT JOIN tmp_payment_deal tpd2 ON tpd2.acquire_order_id = tao.ID 	AND tpd2.deal_code IN ( '101002', '102002' ) 	AND tpd2.payment_service_code = '101007'";
		BiTask jobMain = new BiTask();
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = jobMain.getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql);// 准备PreparedStatement
			statement.setString(1, startTime);
			statement.setString(2, endTime);
			statement.setString(3, startTime);
			statement.setString(4, endTime);
			statement.setString(5, startTime);
			statement.setString(6, endTime);
			statement.setString(7, startTime);
			statement.setString(8, endTime);
			statement.setString(9, startTime);
			statement.setString(10, endTime);
			statement.setString(11, startTime);
			statement.setString(12, endTime);
			statement.setString(13, startTime);
			statement.setString(14, endTime);
			statement.setString(15, startTime);
			statement.setString(16, endTime);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				BiPurchaseFeeInfo bi = new BiPurchaseFeeInfo();
				bi.setMerchantId(rs.getString("merchant_id"));
				bi.setMerchantOrderId(rs.getString("merchant_order_id"));
				bi.setAcsOrderId(rs.getString("acs_order_id"));
				bi.setOrderType(rs.getString("order_type"));
				bi.setOrderCurrency(rs.getString("order_currency"));
				bi.setOrderAmount(rs.getBigDecimal("order_amount"));
				SimpleDateFormat sFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				bi.setGmtCreateTime(sFormat.parse(rs
						.getString("gmt_create_time")));
				bi.setTradeCompleteTime(sFormat.parse(rs
						.getString("complete_date")));
				bi.setGmtCompleteTime(bi.getTradeCompleteTime());
				bi.setTransactionMode(rs.getString("pay_type"));
				bi.setCardBin(rs.getString("card_bin"));
				bi.setChannelCode(rs.getString("channel_code"));
				bi.setCardOrg(rs.getString("card_org"));
				bi.setIssuer(rs.getString("issuer"));
				bi.setCountryCode3(rs.getString("country_code3"));
				bi.setRegion(rs.getString("region"));
				bi.setChargeType(rs.getString("charge_type"));
				bi.setMarginAmtTradeDay(rs.getBigDecimal("margin_amount"));
				bi.setMarginAmtSettDay(rs.getBigDecimal("margin_amount_in"));
				bi.setRestituteMarginAmt(rs.getBigDecimal("margin_amount_out"));
				bi.setAmt101005(rs.getBigDecimal("ccs_amt_101005"));
				bi.setAmt101006(rs.getBigDecimal("ccs_amt_101006"));
				bi.setAmt101007(rs.getBigDecimal("ccs_amt_101007"));
				bi.setSettlementRate(rs.getBigDecimal("settlement_rate"));
				bi.setFixedFeeRate(rs.getBigDecimal("fixed_settlement_rate"));
				list.add(bi);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// writeErrorFile("D://tmp//bi_refund_error.txt", sql + ";");

		} finally {
			try {
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("查询完毕" + list.size());
		return list;
	}

	private BiPurchaseFeeInfo queryBiPurchaseCfg(BiPurchaseFeeInfo bi) {
		BiPurchaseFeeInfo result = new BiPurchaseFeeInfo();
		BeanUtils.copyProperties(bi, result);
		String cfgSql = "SELECT settlement_currencys,settlement_cycle,margin_ratio/100 AS margin_ratio,margin_settlement_cycle,rate_chargeback_type,rate_refund_type FROM ("
				+ " SELECT mc.*,ROW_NUMBER () OVER (PARTITION BY mc.merchant_id ORDER BY to_number(mc.priority,'9999') DESC) AS rid FROM mcs.t_mcs_settlement_config_v2 mc WHERE mc.merchant_id= ? AND (trans_currency= ? OR trans_currency='*') AND (PAYMENT_METHODS= ? OR PAYMENT_METHODS='*') AND is_delete='1') K WHERE K.rid= 1";
		BiTask jobMain = new BiTask();
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = jobMain.getConnection();
			// conn.setAutoCommit(false);
			statement = conn.prepareStatement(cfgSql);// 准备PreparedStatement
			statement.setString(1, bi.getMerchantId());
			statement.setString(2, bi.getOrderCurrency());
			statement.setString(3, bi.getChargeType());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				result.setSettlementCurrency(rs
						.getString("settlement_currencys"));
				result.setSettlementCycle(rs.getString("settlement_cycle"));
				result.setMarginRatio(rs.getBigDecimal("margin_ratio"));
				result.setMarginSettlementCycle(rs
						.getString("margin_settlement_cycle"));

			}

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
		// System.out.println("查询商户结算配置完毕");
		return result;
	}

	private BiPurchaseFeeInfo queryBiPurchaseFee(BiPurchaseFeeInfo bi) {
//		System.out.println("acs_order_id = " + bi.getAcsOrderId());
		BiPurchaseFeeInfo result = bi;
		String countSql = "select count(1) from report.t_ccs_mer_charge_cfg_his where merchant_id = ?  and ? between EFFECTIVE_TIME and EXPIRE_TIME";

		String feeSql1 = "";
		String feeSql2 = "";
		BiTask jobMain = new BiTask();
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = jobMain.getConnection();
			// conn.setAutoCommit(false);
			statement = conn.prepareStatement(countSql);// 准备PreparedStatement
			statement.setString(1, bi.getMerchantId());
			statement.setDate(2, new java.sql.Date(bi.getGmtCompleteTime()
					.getTime()));
			ResultSet rs = statement.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if (count > 0) { // 查询历史计费
				if (bi.getChargeType().equals("01")) { // 信用卡
					feeSql1 = "SELECT CASE WHEN COALESCE (K.PCT_MIN_FEE,0)> K.PERCENT THEN COALESCE (K.PCT_MIN_FEE,0)/100 ELSE K.PERCENT/100 END,K.FIXED_FEE_AMT,K.FIXED_FEE_CURRENCY FROM (SELECT*FROM report.T_CCS_MER_CHARGE_CFG_HIS WHERE MERCHANT_ID= ? AND PROD_CODE='10' AND CHARGE_TYPE= ? AND (REGION= ? OR REGION='*') AND (POSITION (? IN COUNTRY_CODE)> 0 OR COUNTRY_CODE='*') AND (CARD_ORG='*' OR POSITION (? IN CARD_ORG)> 0) AND (TRANSACTION_MODE='*' OR TRANSACTION_MODE= ?) AND ? BETWEEN EFFECTIVE_TIME AND EXPIRE_TIME ORDER BY PRIORITY DESC) K LIMIT 1 OFFSET 0";
				}

				if (bi.getChargeType().equals("02")) { // 本地化
					feeSql2 = "SELECT CASE WHEN COALESCE (K.PCT_MIN_FEE,0)> K.PERCENT THEN COALESCE (K.PCT_MIN_FEE,0)/100 ELSE K.PERCENT/100 END,K.FIXED_FEE_AMT,K.FIXED_FEE_CURRENCY FROM (SELECT*FROM report.T_CCS_MER_CHARGE_CFG_HIS WHERE MERCHANT_ID= ? AND PROD_CODE='10' AND CHARGE_TYPE= ? AND (POSITION (? IN CHANNEL_CODE)> 0 OR CHANNEL_CODE='*') AND ? BETWEEN EFFECTIVE_TIME AND EXPIRE_TIME ORDER BY PRIORITY DESC) K LIMIT 1 OFFSET 0";
				}

			} else { // 查询当前计费

				if (bi.getChargeType().equals("01")) { // 信用卡
					feeSql1 = "SELECT CASE WHEN COALESCE (K.PCT_MIN_FEE,0)> K.PERCENT THEN COALESCE (K.PCT_MIN_FEE,0)/100 ELSE K.PERCENT/100 END,K.FIXED_FEE_AMT,K.FIXED_FEE_CURRENCY FROM (SELECT*FROM ccs.v_ccs_mer_charge_cfg WHERE MERCHANT_ID= ? AND PROD_CODE='10' AND CHARGE_TYPE= ? AND (REGION= ? OR REGION='*') AND (POSITION (? IN COUNTRY_CODE)> 0 OR COUNTRY_CODE='*') AND (CARD_ORG='*' OR POSITION (? IN CARD_ORG)> 0) AND (TRANSACTION_MODE='*' OR TRANSACTION_MODE= ?) ORDER BY PRIORITY DESC) K LIMIT 1 OFFSET 0";
				}

				if (bi.getChargeType().equals("02")) { // 本地化
					feeSql2 = "SELECT CASE WHEN COALESCE (K.PCT_MIN_FEE,0)> K.PERCENT THEN COALESCE (K.PCT_MIN_FEE,0)/100 ELSE K.PERCENT/100 END,K.FIXED_FEE_AMT,K.FIXED_FEE_CURRENCY FROM (SELECT*FROM ccs.v_ccs_mer_charge_cfg WHERE MERCHANT_ID= ? AND PROD_CODE='10' AND CHARGE_TYPE= ? AND (POSITION (? IN CHANNEL_CODE)> 0 OR CHANNEL_CODE='*') ORDER BY PRIORITY DESC) K LIMIT 1 OFFSET 0";
				}
			}

			if (bi.getChargeType().equals("01")) { // 信用卡
				statement = conn.prepareStatement(feeSql1);

				statement.setString(
						1,
						StringUtils.isEmpty(bi.getMerchantId()) ? "" : bi
								.getMerchantId());
				statement.setString(
						2,
						StringUtils.isEmpty(bi.getChargeType()) ? "" : bi
								.getChargeType());
				statement.setString(3, StringUtils.isEmpty(bi.getRegion()) ? ""
						: bi.getRegion());
				statement.setString(
						4,
						StringUtils.isEmpty(bi.getCountryCode3()) ? "" : bi
								.getCountryCode3());
				statement.setString(
						5,
						StringUtils.isEmpty(bi.getCardOrg()) ? "" : bi
								.getCardOrg());
				statement.setString(6, StringUtils.isEmpty(bi
						.getTransactionMode()) ? "" : bi.getTransactionMode());
				if(count > 0){
					statement.setTimestamp(7, new Timestamp(bi
							.getTradeCompleteTime().getTime()));
				}
				

				if (bi.getChargeType().equals("02")) { // 本地化
					statement = conn.prepareStatement(feeSql2);
					statement.setString(1, bi.getMerchantId());
					statement.setString(2, bi.getChargeType());
					statement.setString(3, bi.getChannelCode());
					
					if(count > 0){
						statement.setTimestamp(4, new Timestamp(bi
								.getTradeCompleteTime().getTime()));
					}
					
				}

				ResultSet feeRs = statement.executeQuery();
				if (feeRs.next()) {
					result.setFeePercentage(feeRs.getBigDecimal(1));
					result.setFixedFee(feeRs.getBigDecimal(2));
					result.setFixedFeeCurrency(feeRs.getString(3));
					
				}

			}

		} catch (Exception e) {
			System.out.println(statement.toString());
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
//		System.out.println("查询计费完毕");
		return result;
	}

	public List<BiPurchaseFeeInfo> calBiPurchaseFee(String startTime,
			String endTime) {
		System.out.println("startTime="+startTime+" , endTime="+endTime);
		// System.out.println("遍历计算start");
		List<BiPurchaseFeeInfo> result = new ArrayList<BiPurchaseFeeInfo>();
		List<BiPurchaseFeeInfo> list = biPurchase(startTime, endTime);
		for (int i = 0; i < list.size(); i++) {
			
			BiPurchaseFeeInfo biPurchaseFeeInfo = list.get(i);
			BiPurchaseFeeInfo newBi = new BiPurchaseFeeInfo();

			BiPurchaseFeeInfo biCfg = queryBiPurchaseCfg(biPurchaseFeeInfo);

			biPurchaseFeeInfo.setSettlementCurrency(biCfg
					.getSettlementCurrency());
			biPurchaseFeeInfo.setSettlementCycle(biCfg.getSettlementCycle());
			biPurchaseFeeInfo.setMarginRatio(biCfg.getMarginRatio());
			biPurchaseFeeInfo.setMarginSettlementCycle(biCfg
					.getMarginSettlementCycle());

			BiPurchaseFeeInfo biFee = queryBiPurchaseFee(biPurchaseFeeInfo);
			biPurchaseFeeInfo.setFeePercentage(biFee.getFeePercentage());
			biPurchaseFeeInfo.setFixedFee(biFee.getFixedFee());
			biPurchaseFeeInfo.setFixedFeeCurrency(biFee.getFixedFeeCurrency());

			BigDecimal v_settlement_rate = biPurchaseFeeInfo
					.getSettlementRate() == null ? BigDecimal.ZERO
					: biPurchaseFeeInfo.getSettlementRate();
			BigDecimal v_order_amount = biPurchaseFeeInfo.getOrderAmount();
			BigDecimal v_merchant_fee_ratio = biPurchaseFeeInfo
					.getFeePercentage() == null ? BigDecimal.ZERO
					: biPurchaseFeeInfo.getFeePercentage();
			BigDecimal v_fixed_fee = biPurchaseFeeInfo.getFixedFee() == null ? BigDecimal.ZERO
					: biPurchaseFeeInfo.getFixedFee();
			BigDecimal v_fixed_settlement_rate = biPurchaseFeeInfo
					.getFixedFeeRate() == null ? BigDecimal.ZERO
					: biPurchaseFeeInfo.getFixedFeeRate();
			BigDecimal v_margin_ratio = biPurchaseFeeInfo.getMarginRatio() == null ? BigDecimal.ZERO
					: biPurchaseFeeInfo.getMarginRatio();

			// 计算
			// 清算金额， 公式=交易金额 * 清算汇率（值四舍五入留两位小数，日元交易则四舍五入没有小数）
			BigDecimal v_settlement_amount = v_order_amount
					.multiply(v_settlement_rate);
			// 交易手续费，公式=交易金额*清算汇率 * 比例费比例（值四舍五入留两位小数，日元交易则四舍五入没有小数）
			BigDecimal v_trans_fee = v_order_amount.multiply(v_settlement_rate)
					.multiply(v_merchant_fee_ratio);

			// 结算日期 = 交易日期 + 结算周期
			Date v_settlement_day = DateUtils.addDays(
					biPurchaseFeeInfo.getGmtCompleteTime(),
					Integer.valueOf(biPurchaseFeeInfo.getSettlementCycle()));
			// 交易日收取保证金，公式=交易金额 * 清算汇率 * 保证金比例
			BigDecimal v_td_margin_ratio_fee = v_order_amount.multiply(
					v_settlement_rate).multiply(v_margin_ratio);
			// 结算日收取保证金，公式=交易金额 * 清算汇率 * 保证金比例
			BigDecimal v_sd_margin_ratio_fee = v_order_amount.multiply(
					v_settlement_rate).multiply(v_margin_ratio);
			// 保证金循环到期日，交易日期 + 保证金循环周期
			Date v_merchant_ratio_end_date = DateUtils.addDays(
					biPurchaseFeeInfo.getGmtCompleteTime(), Integer
							.valueOf(biPurchaseFeeInfo
									.getMarginSettlementCycle()));
			// 循环到期日归还保证金，公式=交易金额 * 清算汇率 * 保证金比例
			BigDecimal v_back_merchant_ratio_fee = v_order_amount.multiply(
					v_settlement_rate).multiply(v_margin_ratio);

			// 单笔处理费，公式=固定费值 * 固定费币种兑结算币种汇率
			BigDecimal v_single_trans_fee = v_fixed_fee
					.multiply(v_fixed_settlement_rate);
			// 基本户入账金额， 公式=交易金额 * 清算汇率
			// -交易金额*清算汇率*保证金比例-交易金额*清算汇率*比例费比例-固定费值*固定费币种兑结算币种汇率
			BigDecimal v_acct_in_amount = v_settlement_amount
					.subtract(v_td_margin_ratio_fee).subtract(v_trans_fee)
					.subtract(v_single_trans_fee);

			biPurchaseFeeInfo.setSettlementAmount(v_settlement_amount);
			biPurchaseFeeInfo.setTradeFee(v_trans_fee);
			biPurchaseFeeInfo.setSingleTradeFee(v_single_trans_fee);
			biPurchaseFeeInfo.setSettlementDate(new java.sql.Date(
					v_settlement_day.getTime()));
			biPurchaseFeeInfo.setTradeDateMarginFee(v_td_margin_ratio_fee);
			biPurchaseFeeInfo.setSettlementDateMarginFee(v_sd_margin_ratio_fee);
			biPurchaseFeeInfo
					.setMarginFeeCycleEndDate(v_merchant_ratio_end_date);
			biPurchaseFeeInfo.setBackMarginFee(v_back_merchant_ratio_fee);

			biPurchaseFeeInfo.setRecordedAmount(v_acct_in_amount);

			BeanUtils.copyProperties(biPurchaseFeeInfo, newBi);

			// System.out.println(new Gson().toJson(biPurchaseFeeInfo));
			result.add(newBi);
		}
		// System.out.println("遍历计算end");
		return result;
	}

	public void insertPostgresqlDB(List<BiPurchaseFeeInfo> list) {
		Connection conn = null;

		// 上次执行时间点 20180618180000
		try {
			Class.forName(PostgresqlDataSourceDev.driver).newInstance();
			conn = DriverManager.getConnection(PostgresqlDataSourceDev.url,
					PostgresqlDataSourceDev.user,
					PostgresqlDataSourceDev.password);
			conn.setAutoCommit(false);
			PreparedStatement statement = conn
					.prepareStatement("insert into report.tmp_purchase_fee_info(merchant_order_id, acs_order_id, merchant_id, order_currency, order_amount, gmt_create_time, gmt_complete_time, settlement_currency, settlement_cycle, margin_ratio, margin_settlement_cycle, settlement_rate, fee_percentage, fixed_fee, fixed_fee_currency, fixed_fee_rate, settlement_amount, trade_fee, single_trade_fee, recorded_amount, trade_complete_time, settlement_date, trade_date_margin_fee, settlement_date_margin_fee, margin_fee_cycle_end_date, back_margin_fee, amt_101005, amt_101006, amt_101007, margin_amt_trade_day, margin_amt_sett_day, restitute_margin_amt, org_code, outer_status, order_type, channel_code, transaction_mode, region, issuer, card_org, country_code3, charge_type, card_bin) "
							+ "values(?, ?, ?, ?,  ?,  CAST(? as TIMESTAMP),  CAST(? as TIMESTAMP),  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  CAST(? as TIMESTAMP),  CAST(? as TIMESTAMP),  ?,  ?,  CAST(? as TIMESTAMP),  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)");

			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				BiPurchaseFeeInfo bi = list.get(i);
				// if(bi.getMerchantId().equals("500000000000043")){
				// System.out.println(new Gson().toJson(bi));
				// }

				// System.out.println(log.getId());
				statement.setString(1, bi.getMerchantOrderId());
				statement.setString(2, bi.getAcsOrderId());
				statement.setString(3, bi.getMerchantId());
				statement.setString(4, bi.getOrderCurrency());
				statement.setBigDecimal(5, bi.getOrderAmount());
				SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				statement.setString(6, sfDateFormat.format(bi.getGmtCreateTime()));
				statement.setString(7, sfDateFormat.format(bi.getGmtCompleteTime()));
				statement.setString(8, bi.getSettlementCurrency());
				statement.setString(9, bi.getSettlementCycle());
				statement.setBigDecimal(10, bi.getMarginRatio());
				statement.setString(11, bi.getMarginSettlementCycle());
				statement.setBigDecimal(12, bi.getSettlementRate());
				statement.setBigDecimal(13, bi.getFeePercentage());
				statement.setBigDecimal(14, bi.getFixedFee());
				statement.setString(15, bi.getFixedFeeCurrency());
				statement.setBigDecimal(16, bi.getFixedFeeRate());
				statement.setBigDecimal(17, bi.getSettlementAmount());
				statement.setBigDecimal(18, bi.getTradeFee());
				statement.setBigDecimal(19, bi.getSingleTradeFee());
				statement.setBigDecimal(20, bi.getRecordedAmount());
				statement.setString(21, sfDateFormat.format(bi
						.getTradeCompleteTime()));
				statement.setString(22, sfDateFormat.format(bi.getSettlementDate()));
				statement.setBigDecimal(23, bi.getTradeDateMarginFee());
				statement.setBigDecimal(24, bi.getSettlementDateMarginFee());
				statement.setString(25, sfDateFormat.format(bi
						.getMarginFeeCycleEndDate()));
				statement.setBigDecimal(26, bi.getBackMarginFee());
				statement.setBigDecimal(27, bi.getAmt101005());
				statement.setBigDecimal(28, bi.getAmt101006());
				statement.setBigDecimal(29, bi.getAmt101007());
				statement.setBigDecimal(30, bi.getMarginAmtTradeDay());
				statement.setBigDecimal(31, bi.getMarginAmtSettDay());
				statement.setBigDecimal(32, bi.getRestituteMarginAmt());
				statement.setString(33, bi.getOrgCode());
				statement.setString(34, bi.getOuterStatus());
				statement.setString(35, bi.getOrderType());
				statement.setString(36, bi.getChannelCode());
				statement.setString(37, bi.getTransactionMode());
				statement.setString(38, bi.getRegion());
				statement.setString(39, bi.getIssuer());
				statement.setString(40, bi.getCardOrg());
				statement.setString(41, bi.getCountryCode3());
				statement.setString(42, bi.getChargeType());
				statement.setString(43, bi.getCardBin());
				
				statement.addBatch();

				num++;
//				if (num % 50 == 0 || num == list.size()) {
//					System.out.println("num="+num+", total="+list.size());
//					statement.executeBatch();
//				}
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
		BiTask task = new BiTask();
		SimpleDateFormat sfDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = sfDateFormat.parse("20191212"); // 20180709160000
			Date newDate = null;
			for (int i = 1; i < 3600; i++) {
				newDate = DateUtils.addDays(date, 1);
				if (newDate.compareTo(sfDateFormat.parse("20191220")) <= 0) {
					
					List<BiPurchaseFeeInfo> calBiPurchaseFee = task
							.calBiPurchaseFee(sfDateFormat.format(date),
									sfDateFormat.format(newDate));

					task.insertPostgresqlDB(calBiPurchaseFee);

					date = newDate;
				} else {
					break;
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
