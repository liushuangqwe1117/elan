/**
 * 版权所有(C) 2014 深圳市雁联计算系统有限公司
 * 创建:LS 2014-1-16
 */

package com.redrock.elan.portal.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author LS
 * @date 2014-1-16
 * @description：TODO
 */

public class BigDecimalUtil {

	/**
	 * 将分转换为元
	 * 
	 * @description
	 * @param money
	 * @return
	 * @author LS
	 * @date 2014-1-16
	 */
	public static String parseMoneyWith2Point(Long money) {
		if (money == null)
			return null;
		BigDecimal d1 = new BigDecimal(money);
		return d1.divide(new BigDecimal(100)).toString();
	}

	/**
	 * 将money格式化#,##0.00
	 * 
	 * @description
	 * @return
	 * @author LS
	 * @date 2014-1-16
	 */
	public static String formatMoney(Long money) {
		if (money == null)
			return "0.00";
		return new DecimalFormat("#,##0.00").format(new BigDecimal(parseMoneyWith2Point(money)));
	}
}
