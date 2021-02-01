package com.sh.lang.utils;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA2Util {
	public static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKlp83LWcTWUWaSjtfbbPTCB0C10kb+LneEQxJdFZz7sdCnsx9GEmuXjuuqIeiWQXfpkwGkOGuahXrVj8gO1cG46LKxjx256D/d0/bVymotIHy+575IlN/lHjy6q5J1tuMGLcfD/HSnAPBtGcNVoDx0ZbEbGCOspuRZk967X3T9JAgMBAAECgYBFkVyHK8gRWHn3QcLXO/BEmwoBoHy5S3vpkBkqhTWAgViyXqkvQGTbQkjfRmviQQZWbEkW2tPxIRLjKceX/knMS9YTX1TMg+tSiNRe6GUR7OoSYkoGOxb2kX3nc3H/Ksb0jWDGyF5GP6SfNF/JRIuUhGOCeGsnm7SxzIVbVYG2gQJBAPrUffwJfxEMYfKPA8Bx81JV70MzYsh5U83bqDXv3f0WWv8F5YVBw6w+LEdHEiD4wssU0GNWGe/TGLq3KY+omicCQQCs592JJe1ZQ0tZRdS7jp8YOKswweM2uLHgFtOb+0R+RiyE6d/cIUWG85hPOtTkQ3hKJ+ku0iuRS9YU4EFzJ3EPAkEA0c8ajs7Wtcj4yS7tgXUPhgiRuVFrSKuL+P7Kpks9Ea04cmdZRGztIs9itEBznpbWQY7ofJ8R1PRrlAsPvbXXMwJARkdgZnSCCrwtjjWyCCIqTGAMRR8pyX483oaosEfrSSAgzE17bjAf4IB2sg2JptY9uHaMczl99+rJM2cLc1DexQJAI1+wJ707OdpZkQh8qs0z0bv8LWopn7jiKtp4K/F7q05HONdqZnZRUJQjW1+CB9JkM+ELNhDhwBsF/dQ1y25W+w==";
	public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpafNy1nE1lFmko7X22z0wgdAtdJG/i53hEMSXRWc+7HQp7MfRhJrl47rqiHolkF36ZMBpDhrmoV61Y/IDtXBuOiysY8dueg/3dP21cpqLSB8vue+SJTf5R48uquSdbbjBi3Hw/x0pwDwbRnDVaA8dGWxGxgjrKbkWZPeu190/SQIDAQAB";
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 获取密钥对
	 * 
	 * @return 密钥对
	 */
	public static KeyPair getKeyPair() throws Exception {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		return generator.generateKeyPair();
	}

	/**
	 * 获取私钥
	 * 
	 * @param privateKey
	 *            私钥字符串
	 * @return
	 */
	public static PrivateKey getPrivateKey(String privateKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] decodedKey = Base64.decode(privateKey);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
		return keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 获取公钥
	 * 
	 * @param publicKey
	 *            公钥字符串
	 * @return
	 */
	public static PublicKey getPublicKey(String publicKey) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] decodedKey = Base64.decode(publicKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * RSA加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param publicKey
	 *            公钥
	 * @return
	 */
	public static String encrypt(String data, PublicKey publicKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		int inputLen = data.getBytes().length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offset = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段加密
		while (inputLen - offset > 0) {
			if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
				cache = cipher.doFinal(data.getBytes(), offset,
						MAX_ENCRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(data.getBytes(), offset, inputLen
						- offset);
			}
			out.write(cache, 0, cache.length);
			i++;
			offset = i * MAX_ENCRYPT_BLOCK;
		}
		byte[] encryptedData = out.toByteArray();
		out.close();
		// 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
		// 加密后的字符串
		return new String(Base64.encode((encryptedData)));
	}

	/**
	 * RSA解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 */
	public static String decrypt(String data, PrivateKey privateKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] dataBytes = Base64.decode(data);
		int inputLen = dataBytes.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offset = 0;
		byte[] cache;
		int i = 0;
		// 对数据分段解密
		while (inputLen - offset > 0) {
			if (inputLen - offset > MAX_DECRYPT_BLOCK) {
				cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
			} else {
				cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
			}
			out.write(cache, 0, cache.length);
			i++;
			offset = i * MAX_DECRYPT_BLOCK;
		}
		byte[] decryptedData = out.toByteArray();
		out.close();
		// 解密后的内容
		return new String(decryptedData, "UTF-8");
	}

	/**
	 * 签名
	 * 
	 * @param data
	 *            待签名数据
	 * @param privateKey
	 *            私钥
	 * @return 签名
	 */
	public static String sign(String data, PrivateKey privateKey)
			throws Exception {
		byte[] keyBytes = privateKey.getEncoded();
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey key = keyFactory.generatePrivate(keySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(key);
		signature.update(data.getBytes());
		return new String(Base64.encode(signature.sign()));
	}

	/**
	 * 验签
	 * 
	 * @param srcData
	 *            原始字符串
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            签名
	 * @return 是否验签通过
	 */
	public static boolean verify(String srcData, PublicKey publicKey,
			String sign) throws Exception {
		byte[] keyBytes = publicKey.getEncoded();
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey key = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(key);
		signature.update(srcData.getBytes());
		return signature.verify(Base64.decode(sign));
	}

	public static void main(String[] args) {
		try {
			// 生成密钥对
//			KeyPair keyPair = getKeyPair();
//			String privateKey = new String(Base64.encode(keyPair
//					.getPrivate().getEncoded()));
//			String publicKey = new String(Base64.encode(keyPair
//					.getPublic().getEncoded()));
//			System.out.println("私钥:" + privateKey);
//			System.out.println("公钥:" + publicKey);
			// RSA加密
			String data = "待加密的文字内容hello";
			String encryptData = encrypt(data, getPublicKey(PUBLIC_KEY));
			System.out.println("加密后内容:" + encryptData);
			// RSA解密
			String decryptData = decrypt(encryptData, getPrivateKey(PRIVATE_KEY));
			System.out.println("解密后内容:" + decryptData);

			// RSA签名
			String sign = sign(data, getPrivateKey(PRIVATE_KEY));
			
			System.out.println("签名结果:" + sign);
			
			// RSA验签
			boolean result = verify(data, getPublicKey(PUBLIC_KEY), sign);
			System.out.print("验签结果:" + result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("加解密异常");
		}
	}
}
