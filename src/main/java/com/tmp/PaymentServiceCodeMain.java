package com.tmp;

import com.ipaylinks.enums.PaymentServiceCodeEnum;

public class PaymentServiceCodeMain {

	public static void main(String[] args) {
 
	           System.out.println(PaymentServiceCodeEnum._101001.name()+","+ PaymentServiceCodeEnum.getPaymentServiceName("101001"));  
	           
	   PaymentServiceCodeEnum[] tmpCodeEnums = PaymentServiceCodeEnum.values();
	   for(PaymentServiceCodeEnum tmpEnum : tmpCodeEnums){
		   System.out.println("insert into report.t_payment_service_code(payment_service_code, service_code_name) values ("+tmpEnum.name().substring(1)+",'"+ PaymentServiceCodeEnum.getPaymentServiceName(tmpEnum.name().substring(1))+"');");  
		   
		   
	   }
	       

	}

}
