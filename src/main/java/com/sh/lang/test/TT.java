package com.sh.lang.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TT {

	public static List<String> getAllFileNames(String path) {
		File root = new File(path);
		List<String> files = new ArrayList<String>();
		if (!root.isDirectory()) {
			files.add(root.getName());
		} else {
			File[] subFiles = root.listFiles();
			for (File f : subFiles) {
				files.addAll(getAllFileNames(f.getAbsolutePath()));
			}
		}
		return files;
	}

	public static void main(String[] args) {
		// String[] awsStr = {"css.t_payment_deal", "fi.t_exchange_rate_daily",
		// "fi.transaction_base_rate", "ccs.t_ccs_mer_charge_cfg_base_dtl",
		// "mcs.t_mcs_charge_strategy_detail",
		// "acquire.t_acquire_three_d_result", "ccs.t_ccs_mer_charge_cfg_base",
		// "acquire.t_chargeback_biz_order", "css.t_margin_clearing_detail",
		// "acquire.t_acquire_risk_cust", "mcs.t_mcs_settlement_config",
		// "acquire.t_cashier_buried_info", "channel.t_channel_sub_account",
		// "acquire.t_acquire_token_info", "fi.t_clearing_rate_config",
		// "acquire.t_acquire_risk_bill", "mcs.t_mcs_charge_strategy",
		// "fi.payment_order_expand", "ccs.t_ccs_fee_condition",
		// "acquire.t_chargeback_order", "ccs.t_ccs_mer_charge_cfg",
		// "fi.chargeback_order_log", "mes.t_mes_merchant_info",
		// "ers.t_ers_markup_config", "mes.t_mes_base_merchent",
		// "ccs.t_ccs_fee_strategy", "acquire.t_acquire_order",
		// "channel.t_channel_info", "fi.charge_back_order",
		// "fi.t_receipt_stream", "acct.t_balance_entry",
		// "acquire.t_acquire_risk", "ers.t_ers_crawl_rate",
		// "css.t_payment_order", "fi.payment_channel", "fi.t_binary_batch",
		// "ers.t_ers_his_rate", "inf.p_organization", "acc.t_acct_attrib",
		// "fi.refund_order", "mes.t_mes_base", "acc.t_acct", "fi.trade_data",
		// "intra.users", "acquire.t_acquire_order_context",
		// "fi.partner_settlement_order", "acquire.t_acquire_c_info",
		// "channel.t_channel_order", "fi.pre_controller", "fi.payment_order"};
		// String[] pgStr = {"verify.t_capital_file_detail",
		// "verify.t_capital_file", "verify.t_capital_cost_detail",
		// "txn.t_txn_order", "txn.t_chargeback_order", "pe.payment_service",
		// "mes.t_mes_merchant_info", "mes.t_mes_base_merchent",
		// "mes.t_mes_base", "mes.t_mes_account",
		// "mcs.t_mcs_settlement_config_v2", "mcs.t_mcs_settlement_config",
		// "mcs.t_mcs_merchant_website_config",
		// "mcs.t_mcs_large_merchant_config",
		// "mcs.t_mcs_charge_strategy_detail", "mcs.t_mcs_charge_strategy",
		// "ipayextr.t_users", "ipayextr.t_userrelation", "intra.users",
		// "inf.t_dict_code ", "inf.t_dict_code", "inf.t_dict_category",
		// "inf.p_organization", "inf.inf.t_dict_code ", "inf.country_info",
		// "inf.card_bin_inf", "fo.withdraw_workorder", "fo.fundout_order",
		// "fo.batch_paymentorder", "fi.transaction_base_rate",
		// "fi.trade_order", "fi.refund_order", "fi.refund_apply",
		// "fi.pre_controller", "fi.payment_order_expand", "fi.payment_order",
		// "fi.payment_channel_item", "fi.payment_channel",
		// "fi.partner_settlement_order", "fi.chargeback_order_log",
		// "fi.charge_back_order", "fi.channel_order",
		// "fe.t_withdraw_exchange_diff", "fe.t_user_withdraw",
		// "fe.t_shop_withdraw_detail", "fe.t_shop_settle_exchange_markup",
		// "fe.t_shop_invitecode", "fe.t_shop_in_account", "fe.t_shop",
		// "fe.t_plat_trade_order", "fe.t_plat", "fe.t_member",
		// "fe.t_individual_info", "fe.t_fundout_channel_balance_mx",
		// "fe.t_fundout_channel_balance_his", "fe.t_fund_in_account_activate",
		// "fe.t_fund_in_account", "fe.t_fee_policy_info", "fe.t_fee_config",
		// "fe.t_exchange_rate_day", "fe.t_exchange_rate",
		// "fe.t_enterprise_contact", "fe.t_enterprise_base",
		// "fe.t_edi_import_record", "fe.t_edi_import_detail",
		// "fe.t_channel_settlement", "fe.t_biz_log", "fe.t_agent_sales",
		// "fe.t_account_order", "fe.t_account_balance_his",
		// "fe.t_account_balance", "fe.t_account_activate_batch",
		// "ers.t_ers_his_rate", "ers.t_ers_data_source_config",
		// "ers.t_ers_base_rate", "dc.t_shop_in_account", "dc.dc_data_pull",
		// "dc.dc_data_auth", "dc.dc_auth_ref", "css.t_payment_service",
		// "css.t_payment_order", "css.t_payment_migrating_data",
		// "css.t_payment_deal", "css.t_margin_clearing_detail",
		// "css.t_ccs_mer_charge_cfg_base_dtl", "css.t_ccs_mer_charge_cfg_base",
		// "css.t_ccs_mer_charge_cfg", "channel.t_channel_sub_account",
		// "channel.t_channel_order", "channel.t_channel_info",
		// "channel.t_channel_chan_info", "ccs.t_ccs_mer_trans_data_stats",
		// "ccs.t_ccs_mer_charge_cfg_log", "ccs.t_ccs_mer_charge_cfg_base_dtl",
		// "ccs.t_ccs_mer_charge_cfg_base", "ccs.t_ccs_mer_charge_cfg",
		// "ccs.t_ccs_fee_strategy", "ccs.t_ccs_fee_condition",
		// "ccs.t_ccs_fee_code", "ccs.t_ccs_fee_cfg_relation",
		// "ccs.t_ccs_calculate_fee_info", "acquire.t_mop_mapi_request",
		// "acquire.t_chargeback_work_order", "acquire.t_chargeback_order",
		// "acquire.t_chargeback_code_mapping",
		// "acquire.t_chargeback_biz_order", "acquire.t_acquire_token_info",
		// "acquire.t_acquire_three_d_result", "acquire.t_acquire_risk_ship",
		// "acquire.t_acquire_risk_goods", "acquire.t_acquire_risk_cust",
		// "acquire.t_acquire_risk_browser", "acquire.t_acquire_risk_bill",
		// "acquire.t_acquire_risk", "acquire.t_acquire_order_context",
		// "acquire.t_acquire_order", "acquire.t_acquire_logistics_info_mgt",
		// "acquire.t_acquire_c_info", "acct.t_balance_entry",
		// "acct.t_acct_balance_daily", "acct.t_acct_attrib", "acct.t_acct",
		// "acc.t_liquidate_info", "acc.t_balance_entry", "acc.t_balance_deal",
		// "acc.t_acct_attrib", "acc.t_acct"};
		//
		// // for(String pg : pgStr){
		// // Boolean flag = false;
		// // for(String aws : awsStr){
		// // if(aws.equals(pg)){
		// // flag = true;
		// // }
		// // }
		// //
		// // if(!flag){
		// // String[] tmp = pg.split("\\.");
		// //
		// //
		// //
		// System.out.println("insert into dba_mon.tb_aws_report_mig(tbname,logdate,owner)value('"+tmp[1]+"',sysdate,'"+tmp[0]+"');");
		// // }
		// // }
		//
		//
		// String[] t = {"report.etl_tmp_settle_funds_pos",
		// "report.etl_tmp_settle_funds_neg", "report.etl_tmp_fund_refund_bi",
		// "report.etl_tmp_fund_refund", "report.etl_tmp_fund_purchase",
		// "report.etl_tmp_currency_exc", "report.etl_settle_merchant_rebate",
		// "report.etl_settle_meast_pro_income",
		// "report.etl_settle_meast_exc_income",
		// "report.etl_settle_funds_pos_next",
		// "report.etl_settle_funds_neg_next",
		// "report.etl_settle_exc_gains_losses",
		// "report.etl_settle_channel_rebate", "report.etl_settles_to_channel",
		// "report.etl_settlement_tap_src", "report.etl_settlement_sxy_src",
		// "report.etl_settlement_sc_summary", "report.etl_settlement_sc_src",
		// "report.etl_settlement_sc_ic_src", "report.etl_settlement_sc_br_src",
		// "report.etl_settlement_pv_ic_src", "report.etl_settlement_pv_br_src",
		// "report.etl_settlement_molpay_src", "report.etl_settlement_lp_src",
		// "report.etl_settlement_hq_src", "report.etl_settlement_fd_trans_src",
		// "report.etl_settlement_fd_payment_src",
		// "report.etl_settlement_fd_fee_src", "report.etl_settlement_et_src",
		// "report.etl_settlement_dc_src", "report.etl_settlement_dc_proc",
		// "report.etl_settlement_dc_dmr", "report.etl_settlement_ct_ppro_src",
		// "report.etl_settlement_ct_pagb_src", "report.etl_settlement_cd_src",
		// "report.etl_settlement_cdpa_src", "report.etl_settlement_boc_src",
		// "report.etl_settlement_bc_src", "report.etl_settlement_bc_cb_src",
		// "report.etl_settlement_all_combine_t",
		// "report.etl_settlement_all_combine_bk",
		// "report.etl_settlement_all_combine_3",
		// "report.etl_settlement_all_combine", "report.etl_settlement_ae_src",
		// "report.etl_kjsd_fin_refund_his", "report.etl_kjsd_fin_purchase_his",
		// "report.etl_job_runtime", "report.etl_import_logs",
		// "report.etl_channel_rebate_src", "report.etl_cfp_settle_solaris_src",
		// "report.etl_cfp_settle_saxo_src", "report.etl_cfp_settle_qbc_src",
		// "report.etl_cfp_settle_chicago_src", "report.etl_cfp_settle_cd_src",
		// "report.etl_cfp_settle_allpay_test",
		// "report.etl_cfp_settle_allpay_src", "report.etl_cfp_outchannel_conf",
		// "report.etl_cfp_inchannel_conf", "report.t_kjsd_purchase_details",
		// "report.t_kjsd_purchase_check_point",
		// "report.t_kjsd_purchase_settle", "report.t_kjsd_refund_check_point",
		// "report.t_kjsd_refund_details", "report.t_kjsd_test_merchant",
		// "report.t_kjsd_purchase_mid_travol",
		// "report.t_kjsd_fund_refund_stl_mrc",
		// "report.t_kjsd_fund_refund_fin_mrc",
		// "report.t_kjsd_fund_refund_daily",
		// "report.t_kjsd_fund_refund_daily_copy1",
		// "report.t_kjsd_fund_refund_daily_copy2",
		// "report.t_kjsd_purchase_details_copy1",
		// "report.t_kjsd_fund_purchase_stl_mrc",
		// "report.t_kjsd_fund_purchase_fin_mrc",
		// "report.t_kjsd_fund_purchase_daily_1",
		// "report.t_kjsd_fund_purchase_fin_mrc_4",
		// "report.t_kjsd_fund_purchase_stl_mrc_4",
		// "report.t_kjsd_fund_purchase_daily",
		// "report.t_kjsd_fund_purchase_stl_bi",
		// "report.t_kjsd_fund_refund_daily_bi", "report.t_settle_ch_cost",
		// "report.t_settle_exchange_rate", "report.t_settle_ch_body_conf",
		// "report.t_settle_meast_exc_income",
		// "report.t_settle_meast_pro_income",
		// "report.t_settle_funds_neg_daily", "report.t_settle_trans_base_rate",
		// "report.t_settle_funds_pos_daily", "report.t_settle_dc_dmr_conf",
		// "report.t_settle_fund_balance", "report.t_settle_local_channel_conf",
		// "report.t_settle_exc_gains_losses", "report.t_settle_dc_exc_rate",
		// "report.t_settle_acct_balance", "report.t_settle_withdraw",
		// "report.t_settle_wallet", "report.t_settle_business_analysis",
		// "report.t_settle_inout_struct", "report.t_settle_member_summary",
		// "report.t_settle_channel_rebate", "report.t_settle_inaccount",
		// "report.t_settle_monitor_bi_report",
		// "report.t_settle_merchant_rebate", "report.t_settle_ch_cost_m",
		// "report.t_settle_total_income"};
		// for(String tmp : t){
		// String[] tt = tmp.split("\\.");
		// System.out.println("insert into dba_mon.tb_aws_report_mig(tbname,logdate,owner)value('"+tt[1]+"',sysdate,'"+tt[0]+"');");
		//
		// }
		
		List<String> fileList = getAllFileNames("C:\\FineReport_8.0\\WebReport\\WEB-INF\\reportlets\\IpayLinks\\数据字典");
		for(String tmp: fileList){
			if(tmp.endsWith(".cpt")){
				System.out.println(tmp);
			}
			
		}

	}

}
