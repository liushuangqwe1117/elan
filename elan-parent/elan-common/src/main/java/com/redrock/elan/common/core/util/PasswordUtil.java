package com.redrock.elan.common.core.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.ylinkpay.framework.core.util.CryptographyUtil;

public class PasswordUtil {
	
	/**
	 * 加密随机盐值
	 */
	private static final String ENCYPT_RANDOM_SALT = "K9tpRg5j";

	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password) {
		if (password == null)
			return null;
		return DigestUtils.shaHex(password);
	}

	/**
	 * 密码匹配
	 * 
	 * @param password
	 * @param passwordMd5
	 * @return
	 */
	public static boolean passwordsMatch(String password,
			String passwordEncrypted) {
		if (password == null || passwordEncrypted == null)
			return false;
		return passwordEncrypted.equals(encryptPassword(password));
	}

	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPassword(char[] password) {
		if (password == null)
			return null;
		String pwString = String.valueOf(password);
		return encryptPassword(pwString);
	}

	public static String encryptPlainByMd5WithSalt(String plainPass){
		return encryptMd5ByMd5WithSalt(CryptographyUtil.hashMd5Hex(plainPass));
	}
	
	public static String encryptMd5ByMd5WithSalt(String md5Pass){
		return CryptographyUtil.hashMd5Hex(md5Pass + ENCYPT_RANDOM_SALT);
	}
	
	public static String hashMd5Hex(String plainText){
		return CryptographyUtil.hashMd5Hex(plainText);
	}
	
	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPasswordWithSha(String password) {
		if (password == null)
			return null;
		return DigestUtils.shaHex(password);
	}


	/**
	 * 加密密码
	 * 
	 * @param password
	 * @return
	 */
	public static String encryptPasswordWithSha(char[] password) {
		if (password == null)
			return null;
		String pwString = String.valueOf(password);
		return encryptPasswordWithSha(pwString);
	}
	
	
	public static void main(String[] args) {
		//356a192b7913b04c54574d18c28d46e6395428ab
		System.out.println(encryptPasswordWithSha("1"));
	}

}
