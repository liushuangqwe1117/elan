package com.redrock.elan.common.core.util;

import java.math.BigDecimal;

public class AmountUtil {

	public static String fenToYuan(Long amount){
		if(amount == null)return null;
		if(amount == 0L)return "0";
		return new BigDecimal(amount).divide(new BigDecimal(100)).toString();
	}
	public static void main(String[] args) {
		System.out.println(fenToYuan(1L));
		System.out.println(fenToYuan(1234L));
	}
}
