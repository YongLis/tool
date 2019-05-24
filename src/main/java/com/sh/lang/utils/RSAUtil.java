package com.sh.lang.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

/**
 * RSA加解密工具
 */

public class RSAUtil {
	
	/**
	 * 生成密钥对 
	 */
	public static void initKey(){
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			
			KeyPair keyPair = generator.generateKeyPair();
			
			RSAPublicKey rsaPublicKey  = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			
			String publicKey = new Base64().encodeBase64String(rsaPublicKey.getEncoded());
			String privateKey = new Base64().encodeBase64String(rsaPrivateKey.getEncoded());
			
			System.out.println("public-key-----------------");
			System.out.println(publicKey);
			System.out.println("public-key-----------------");
			
			System.out.println("private-key-----------------");
			System.out.println(privateKey);
			System.out.println("private-key-----------------");
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String encrypt(byte[] buffer, String publicKey){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(new Base64().decodeBase64(publicKey));
			
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
			
			return new Base64().encodeBase64String(cipher.doFinal(buffer));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String decrypt(String message, String privateKey){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(new Base64().decode(privateKey));
			
			RSAPrivateKey rsaPublicKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
			
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
			
			return new String(cipher.doFinal(new Base64().decodeBase64(message)));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	

	public static void main(String[] args) {
//		RSAUtil.initKey();
//		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAIRFXDX7KLVp/mk46S/skRQO5GaRBRr9zsUU7/UKGHf2HfovzQuQVkM/fKVoiruGhAuEmz8iDq1dKXkEmQGhQjj8uJiS+2J3fZBivIjpcknkPKfl7mWys4q7cMpOR77plh7H8b61R4P0WVRNFt5tz5EAxO0MUVt58+wt0dFH12wIDAQAB";
//		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIAhEVcNfsotWn+aTjpL+yRFA7kZpEFGv3OxRTv9QoYd/Yd+i/NC5BWQz98pWiKu4aEC4SbPyIOrV0peQSZAaFCOPy4mJL7Ynd9kGK8iOlySeQ8p+XuZbKzirtwyk5HvumWHsfxvrVHg/RZVE0W3m3PkQDE7QxRW3nz7C3R0UfXbAgMBAAECgYBSrwQ1nSvY5HOmLLmWvTmQKklAxd9NkI4z5o27LLAlRG07ZtqBXQU7NXwvksvr3dcUxHrvRN8suQFXqIN6X83wYvBV8TJgMG7I7eIE/eMSXe03fWdwI0IMX02468WNRePpj7nGqHS3yERE8wj+RRK8HsUFD8pRqekQvLLbEfjRgQJBAMoup+8ix893q7hqrk30ED/w+FYKNGhZNQNcQ/X3dHraBmmoj8oXcWk2ASRlcVTAcEYm75WXGwtAIyFTS0HW0DECQQCiPDLRPXig/iVbX3Cq962geXr6xziZcYMlsi9KTJgxlRkMV2YYjP1GYjSlhHE/R/a0ARGtU6MrcptiIr8LSA/LAkAV+kRfBlTaBJQyE8amyqUJjlQwZiOURD4zA1CP6DpNMG4KBjy7Jxk46pntLunG4LRTq9/XTvmtm2c2NCu2GHfBAkEAnGP7eMkRJ+DWkiaGrcjkjvWMH4M8hG5wRj5mZ/E+JU3dP5Bg0vnWjarNpWDu6lms8Ai3yaX+fTuVnhxwnadx0QJABml/kRL0yHJ007mGdGUlFWiDYTfa8sQSLsMc6lWdC8PfZFj/p2KDJLBF0Z3jSwGH0cJ1rJK5WPBUEcGhu1izPQ==";

		String publicKey = "mQENBFzl/20BCADM26wgIC6y2srP4vaXLGyqhg2+Bu2RTBbeHzqritzcWF2kg/je4r24BN+jUAXaKBmzeeoK/sZGNd/o9M83MMt49zlnnBJoyupug+XNfa8yK22vlfKF/4TLapq0CYUh82TbWBAL3QmxbpPCE6II+HfxM3E9VQauB122Kemm6KKHF6qIH/0B7YGTm6xKqihykT6bWHew0g3se5dV4qPVQ6O61S+LTQU0m6CHO6ukWM1o08uaRs/dKcJRDw5PY0B8Rw2ZJ7ZqCyAJNtuSLwJr5qnUVziaFXU+BCPt752xcVVa3VeHZBYbg62Nq52SnaX+jhG8cU84xHHb8zbl1UZuEtMTABEBAAG0FXlvbmcubGlAaXBheWxpbmtzLmNvbYkBVAQTAQgAPhYhBD0Y4QsiYl8+of31PExnbGai3V1HBQJc5f9tAhsDBQkDw9PTBQsJCAcCBhUKCQgLAgQWAgMBAh4BAheAAAoJEExnbGai3V1HKy8H/AtL6n4HqDnelwBuiVsIZ9PW4D9YMXiSvFsYP/AaKe0U/mAVDs2Ghy4kJYMly5IJcjsgEjWTpQeLQt9T7cTl8fsK4ywQCtEZfWG8/5SVoCpcwKlmafAHDkvkbNJr4/dgVwsMtIFXHwikDeZseyqJD8HiJelK3uRd57UfMVdGuwGc52inoHDHtNitNnjqB1gp1dXWO4JS4TtQl4LvxU+1IIMr21sPg0ny9H5+1ajC5fn0wM+ZJ2kh2ztYnr65/ttfYZXF1ZObzuiVmoEuzuxax13X+SsWdZpAw3QLoWyHNiRzIMdHa02YaNmd8fKH9GLoSNFNi0W47+VpXypB8KzFTs+5AQ0EXOX/bQEIAL8dfUPOf3m0nSC5gls1+txf2oJ8uiF9sLarfVoV5NCE0FPPF8AR2IocAlC0hxOwkibvQNWg6Uop3HyutvB20lFK4VkCV6s+aNQCwofZME0o126HKyXj5G9mYrEZyLRXjrozkiy+LVSpmrU1Ej41KkFk/BHbj1HINsyARvmCvEkxhsR7YD1DMTtXLGcQESXWBK7DcnCb4/pg3+32PYMrsG680koCGz1qTrO2PvKYsTgYx0Sv7vkKG8DOVGqwsABpADvxnazb/yRRoe+DNN6cVIfGYEtar/dsWh/MjUBdFWXgUXsaMmkaNCW0dT6Ff2TmJ68SfNpxNck1F7t8vsslhh8AEQEAAYkBPAQYAQgAJhYhBD0Y4QsiYl8+of31PExnbGai3V1HBQJc5f9tAhsMBQkDw9PTAAoJEExnbGai3V1H8DYH/35/k/2raFRrrZE1cgsI2oJhAO0ptHNwNTTSQP8l7TqllvxaBGpeMiT39nkBzkJcbBiJzVeYtA2vxC71WDLKeg9cgNGdnsKLwITf0VBCKKg/pgWt8+oO3HsDAhvIRhWZOSdwWOJj7cTuGPdlh1NF6mSzJzBZBqtvAvBC+hu5sD6wiG0Da9gKI+4Adso5dv8I63T6iy0sw7EGZyaPwuIgq4ghnSLnUd7ugpbUMT0+R72gTnFtHv8npUPPzpCZML7efqKFd+TpzY6rFaeMZRsAVCN7PZaJ5kBJ4CLTkRi8ZvYNdYC2aMclUQXlEWJv4iJ+vtDTyn+FBW6xHBMFajvzaeU==1wY+";
		String privateKey = "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=yvPc";
		
		String tmpString = "admin12afafd3";
		String res = RSAUtil.encrypt(tmpString.getBytes(), publicKey);
		System.out.println(res);
		
		System.out.println(res.length());
		String deRes = RSAUtil.decrypt(res, privateKey);
		System.out.println(deRes);
		
		
	}

}
