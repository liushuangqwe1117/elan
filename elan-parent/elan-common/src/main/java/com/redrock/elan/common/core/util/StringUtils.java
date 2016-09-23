/**
 * 版权所有(C) 2015 深圳市雁联计算系统有限公司
 * 创建:lyg 2015-8-24
 */

package com.redrock.elan.common.core.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @author lyg
 * @date 2015-8-24
 * @description：
 */

public class StringUtils {
	/**
	 * 将字符串有某种编码转变成另一种编码
	 * 
	 * @param string
	 *            编码的字符串
	 * @param originCharset
	 *            原始编码格式
	 * @param targetCharset
	 *            目标编码格式
	 * @return String 编码后的字符串
	 */
	public static String encodeString(String string, Charset originCharset,
			Charset targetCharset) {
		return string = new String(string.getBytes(originCharset),
				targetCharset);
	}

	/**
	 * URL编码
	 * 
	 * @param string
	 *            编码字符串
	 * @param charset
	 *            编码格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String encodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLEncoder.encode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return URLEncoder.encode(string);
	}

	/**
	 * URL编码
	 * 
	 * @param string
	 *            解码字符串
	 * @param charset
	 *            解码格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String decodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLDecoder.decode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return URLDecoder.decode(string);
	}

	/**
	 * 判断字符串是否是空的 方法摘自commons.lang
	 * 
	 * <pre>
	 * StringUtils.isEmpty(null)      = true
	 * StringUtils.isEmpty("")        = true
	 * StringUtils.isEmpty(" ")       = false
	 * StringUtils.isEmpty("bob")     = false
	 * StringUtils.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * 判断字符串是否是""," ",null,注意和isEmpty的区别
	 * </p>
	 * 方法摘自commons.lang
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	
	public static char getOne(String nuber){
		
		return nuber.charAt(0);
	}
	
	
	/**  
     * Luhn算法  
     * 根据卡号获取校验位  
     * @param cardNumber  
     * @return  
     */  
    public static int addCheckNo(String cardNumber){  
        int totalNumber = 0;  
        for (int i = cardNumber.length()-1; i >= 0; i-=2) {  
            int tmpNumber = calculate(Integer.parseInt(String.valueOf(cardNumber.charAt(i))) *  2);  
            if (i==0) {  
                totalNumber += tmpNumber;  
            }else {  
                totalNumber += tmpNumber + Integer.parseInt(String.valueOf(cardNumber.charAt(i-1)));  
            }  
              
        }  
        if (totalNumber >= 0 && totalNumber < 9) {  
            return (10 - totalNumber);  
        }else {  
            String str = String.valueOf(totalNumber);  
            if (Integer.parseInt(String.valueOf(str.charAt(str.length()-1))) == 0) {  
                return 0;   
            }else {  
                return (10 - Integer.parseInt(String.valueOf(str.charAt(str.length()-1))));  
            }  
        }  
          
    }  
      
    /**  
     * 计算数字各位和  
     * @param number  
     * @return  
     */  
    public static int calculate(int number){  
        String str = String.valueOf(number);  
        int total = 0;  
        for (int i = 0; i < str.length(); i++) {  
            total += Integer.valueOf(Integer.parseInt(String.valueOf(str.charAt(i))));  
        }  
        return total;  
    }  
}
