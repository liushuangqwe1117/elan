package com.redrock.elan.common.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

import org.apache.commons.codec.binary.Base64;
import org.ylinkpay.framework.core.exception.MsgException;
import org.ylinkpay.framework.support.security.JSignVerify;



public class SecureUtils {
	
	private static String ENCODING = "utf-8";
	
	/**
	 * 验签
	 * @param cert
	 * @param checkValue
	 * @param xmlMsg
	 * @throws MsgException
	 */
	public static void verifyMsgSign(byte[] cert, String checkValue, String xmlMsg) 
			throws MsgException {
		try{
			byte[] binaryData  = Base64.decodeBase64(checkValue.getBytes(ENCODING));
			
			//获取公钥
			PublicKey publicKey = getPublicKey(cert);
			if(publicKey == null){
				throw new MsgException("获取证书失败");
			}
			
			//验签
			Signature signcheck = Signature.getInstance("SHA1withRSA");
			signcheck.initVerify(publicKey);
			signcheck.update(xmlMsg.getBytes(ENCODING));
			boolean flag = signcheck.verify(binaryData);
			if(!flag){
				throw new MsgException("验证签名失败");
			}
		}catch (Exception e) {
			throw new MsgException("验证签名失败," + e.getMessage());
		}
	}
	
	/**
	 * 加签
	 * 
	 * @param msg
	 * @param priKey
	 * @return
	 * @throws MsgException
	 */
	public static String signMsg(String msg, PrivateKey priKey) throws MsgException{
		try{
			byte[] signed = JSignVerify.SignMemory(msg.getBytes(ENCODING), priKey);			
			return new String(Base64.encodeBase64(signed), ENCODING);							
		}catch (Exception e) {
			throw new MsgException("加签失败," + e.getMessage());
		} 		
	}
	
	/**
	 * 得到公钥
	 * 
	 * @param certData
	 * @return
	 * @throws Exception
	 */
	private static PublicKey getPublicKey(byte[] certData) throws Exception {
		InputStream is = new ByteArrayInputStream(certData);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate certificate = cf.generateCertificate(is);
		System.out.println("PublicKey:" + certificate.getPublicKey().getAlgorithm());
		System.out.println("PublicKey:" + certificate.getPublicKey().getFormat());
		if(is != null)
			is.close();
		return certificate.getPublicKey();
	}
	
	private static PrivateKey getPrivateKey(String filePath , String keyPwd) {
		InputStream fis = null;
		try{
			File file = new File(filePath);
			if(!file.exists()){
				throw new RuntimeException("私钥文件不存在：" + filePath);
			}
			fis = new FileInputStream(file);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(fis, keyPwd.toCharArray());

			Enumeration<?> aliases = keyStore.aliases();
			String keyAlias = null;
			if (aliases != null){
				while (aliases.hasMoreElements()){
					keyAlias = (String) aliases.nextElement();
					return (PrivateKey)(keyStore.getKey(keyAlias, keyPwd.toCharArray()));
				}
			}
			if (fis != null)
				fis.close();
		}catch (Exception e){
			e.printStackTrace();
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			throw new RuntimeException("获取支付中心私钥失败");
		}
		return null;
	}
	
	public static void main(String[] args) {
		String pubKeyPath = "G:\\eclipse_workspace\\netpay-access\\src\\main\\resources\\cert\\szfs999999999999.crt";
		String privateKeyPath = "G:\\eclipse_workspace\\netpay-access\\src\\main\\resources\\cert\\szfs999999999999.pfx";
		String content = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPFJlcXVlc3QgeG1sbnM6eHNpPSJodHRwOi8vd3d3LnczLm9yZy8yMDAxL1hNTFNjaGVtYS1pbnN0YW5jZSIgeHNpOm5vTmFtZXNwYWNlU2NoZW1hTG9jYXRpb249IiI+PE5vdGlmeUlkPjE0NDg2MTU3NTIzNjA8L05vdGlmeUlkPjxQYXlUaW1lPjIwMTUtMTEtMjYgMTU6NTI6MzA8L1BheVRpbWU+PFRyYWRlSWQ+MjAxNTExMjYwMzUyMzAxMDAwMzA5NzwvVHJhZGVJZD48UGF5bWVudElkPjEwMDAwMDAwODk8L1BheW1lbnRJZD48QW1vdW50PjEwMDwvQW1vdW50PjxBbkRvdT4wPC9BbkRvdT48WmVuZ0RpYW4+MDwvWmVuZ0RpYW4+PEppRmVuPjA8L0ppRmVuPjxWaXJDYXJkTm8+PC9WaXJDYXJkTm8+PFZpckNhcmRBbW91bnQ+MDwvVmlyQ2FyZEFtb3VudD48Q291cG9uTm8+PC9Db3Vwb25Obz48YmFua1R5cGU+MDAwMzwvYmFua1R5cGU+PC9SZXF1ZXN0Pg==";
		String sign = "";
		String keyPwd = "31g1awnc";
		try {
			PrivateKey privateKey = getPrivateKey(privateKeyPath,keyPwd);
			System.out.println("privateKey:" + privateKey.getAlgorithm());
			System.out.println("privateKey:" + privateKey.getFormat());
//			String nwString = new String(Base64.decodeBase64(content.getBytes()));
			//sign = signMsg(nwString, XmlDealUtil.initPrivateKey());
			System.out.println(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			File file = new File(pubKeyPath);
			InputStream inputStream = new FileInputStream(file);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte [] buff = new byte[1024];
			int len = -1;
			while((len = inputStream.read(buff)) != -1){
				outputStream.write(buff, 0, len);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			System.out.println("公钥" + Base64.decodeBase64(new String(outputStream.toByteArray())));
			verifyMsgSign(outputStream.toByteArray(), sign, content);
			System.out.println("verify msg sign successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
