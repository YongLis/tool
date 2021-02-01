package com.sh.lang.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

public class RSAUtil {
    public static final String ALGORITHM = "RSA";
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    private int keySize = 0;
    private String transformation = null;
    private String signatureAlgorithm = null;
    private static RSAUtil instance = null;
    
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
    
    private RSAUtil(int keySize, String transformation, String signatureAlgorithm) {
        super();
        this.keySize = keySize;
        this.transformation = transformation;
        this.signatureAlgorithm = signatureAlgorithm;
    }
    
    public static synchronized RSAUtil getInstance(int keySize) {
        if (instance == null) {
            instance = new RSAUtil(keySize, "RSA/ECB/PKCS1Padding", "SHA1withRSA");
        }
        return instance;
    }
    
    public static synchronized RSAUtil getInstance() {
        if (instance == null) {
            instance = new RSAUtil(1024, "RSA/ECB/PKCS1Padding", "SHA1withRSA");
        }
        return instance;
    }
    
    public KeyPair generateRandomKeyPair() throws RuntimeException {
        try {
            KeyPairGenerator keyPairGen = null;
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGen.initialize(keySize, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public KeyPair generateKeyPair(byte[] seed) throws RuntimeException {
        try {
            KeyPairGenerator keyPairGen = null;
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGen.initialize(keySize, new SecureRandom(seed));
            KeyPair keyPair = keyPairGen.genKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PrivateKey loadPrivateKey(File keyFile) throws RuntimeException {
        try {
            
            byte[] data = FileUtils.readFileToByteArray(keyFile);
            
            PKCS8EncodedKeySpec pkcs8Enc = new PKCS8EncodedKeySpec(data);
            
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return keyFactory.generatePrivate(pkcs8Enc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PrivateKey loadPrivateKey(String keyPath) throws RuntimeException {
        File keyFile = new File(keyPath);
        return loadPrivateKey(keyFile);
    }
    
    public static PrivateKey loadPrivateKeyFromPem(String keyPath) throws RuntimeException {
        
        BufferedReader bufReader = null;
        byte[] data = null;
        
        try {
            bufReader = new BufferedReader(new FileReader(keyPath));
            StringBuffer buf = new StringBuffer();
            String next = null;
            
            while ((next = bufReader.readLine()) != null) {
                if (next.indexOf("RSA PRIVATE KEY") != -1) {
                    break;
                }
            }
            if (next == null) {
                throw new RuntimeException("Not exist [PRIVATE KEY] section " + "in private key file.");
            }
            
            next = bufReader.readLine();
            if (next == null) {
                throw new RuntimeException("Invalid [PRIVATE KEY] section .");
            }
            if (next.startsWith("Proc-Type: 4,ENCRYPTED")) {
                throw new RuntimeException("Not supported encrypted private key.");
            } else {
                buf.append(next);
            }
            
            while ((next = bufReader.readLine()) != null) {
                if (next.startsWith("-----END")) {
                    break;
                }
                buf.append(next);
            }
            bufReader.close();
            data = Base64.decode(buf.toString().getBytes());
            PKCS8EncodedKeySpec pkcs8Enc = new PKCS8EncodedKeySpec(data);
            
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8Enc);
            return priKey;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PublicKey loadPublicKey(String keyPath) throws RuntimeException {
        try {
            File file = new File(keyPath);
            FileInputStream in = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            in.read(data);
            in.close();
            
            X509EncodedKeySpec x509Enc = new X509EncodedKeySpec(data);
            
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            return keyFactory.generatePublic(x509Enc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static PublicKey loadPublicKeyFromPemCert(String certPath) throws RuntimeException {
        try {
            InputStream bis = null;
            bis = new BufferedInputStream(new FileInputStream(certPath));
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate cert = cf.generateCertificate(bis);
            return cert.getPublicKey();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public static void savePrivateKey(PrivateKey key, String keyPath) throws RuntimeException {
        try {
            FileOutputStream out = new FileOutputStream(new File(keyPath));
            PKCS8EncodedKeySpec pkcs8Enc = null;
            pkcs8Enc = new PKCS8EncodedKeySpec(key.getEncoded());
            out.write(pkcs8Enc.getEncoded());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void savePublicKey(PublicKey key, String keyPath) throws RuntimeException {
        try {
            FileOutputStream out = new FileOutputStream(new File(keyPath));
            X509EncodedKeySpec x509Enc = null;
            x509Enc = new X509EncodedKeySpec(key.getEncoded());
            out.write(x509Enc.getEncoded());
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public byte[] generateSignature(InputStream in, PrivateKey prikey) throws RuntimeException {
        try {
            Signature sig = Signature.getInstance(signatureAlgorithm);
            sig.initSign(prikey);
            
            byte[] buffer = new byte[1024];
            int len;
            while (0 <= (len = in.read(buffer))) {
                sig.update(buffer, 0, len);
            }
            in.close();
            
            return sig.sign();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean verifySignature(InputStream in, PublicKey pubKey, byte[] orgSig) throws RuntimeException {
        try {
            Signature sig = Signature.getInstance(signatureAlgorithm);
            
            sig.initVerify(pubKey);
            
            byte[] buffer = new byte[1024];
            int len;
            while (0 <= (len = in.read(buffer))) {
                sig.update(buffer, 0, len);
            }
            in.close();
            
            return sig.verify(orgSig);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public byte[] encode(Key key, byte[] data) throws RuntimeException {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    
    public byte[] decode(Key key, byte[] data) throws RuntimeException {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param privateKey    私钥(十六进制编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
        // byte[] keyBytes = HexUtil.toByteArray(privateKey);
        byte[] keyBytes = Base64.decode(privateKey);// 修改base64编码
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
    
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey     公钥(十六进制编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
        // byte[] keyBytes = HexUtil.toByteArray(privateKey);
        byte[] keyBytes = Base64.decode(publicKey);// 修改base64编码
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
    
    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param data      源数据
     * @param publicKey 公钥(十六进制编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        // byte[] keyBytes = HexUtil.toByteArray(privateKey);
        byte[] keyBytes = Base64.decode(publicKey);// 修改base64编码
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    
    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param data       源数据
     * @param privateKey 私钥(十六进制编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        // byte[] keyBytes = HexUtil.toByteArray(privateKey);
        byte[] keyBytes = Base64.decode(privateKey);// 修改base64编码
        
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    
    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param encryptedData 已加密数据(十六进制编码)
     * @param privateKey    私钥(十六进制编码)
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encryptedData, String privateKey) throws Exception {
        // return new
        // String(decryptByPrivateKey(HexUtil.toByteArray(encryptedData),
        // privateKey));
        return new String(decryptByPrivateKey(Base64.decode(encryptedData), privateKey));
    }
    
    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param encryptedData 已加密数据
     * @param publicKey     公钥
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encryptedData, String publicKey) throws Exception {
        // return new
        // String(decryptByPublicKey(HexUtil.toByteArray(encryptedData),
        // publicKey));
        return new String(decryptByPublicKey(Base64.decode(encryptedData), publicKey));
    }
    
    /**
     * <p>
     * 公钥加密，返回十六进制编码的密文
     * </p>
     *
     * @param data      源数据
     * @param publicKey 公钥(十六进制编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String publicKey) throws Exception {
        return new String(Base64.encode(encryptByPublicKey(data.getBytes(), publicKey)));
    }
    
    /**
     * <p>
     * 私钥加密，返回十六进制编码的密文
     * </p>
     *
     * @param data       源数据
     * @param privateKey 私钥(十六进制编码)
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String privateKey) throws Exception {
        return new String(Base64.encode(encryptByPrivateKey(data.getBytes(), privateKey)));
    }
    
    /**
     * pem转私钥
     *
     * @param pem
     * @return 返回私钥
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyByPem(String pem) throws Exception {
        
        byte[] bPriKey = Base64.decode(pem.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bPriKey);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        return key;
    }
    
    /**
     * JKS转私钥
     *
     * @return 返回私钥
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyByJks(String jksFile, String storePass, String keyPass, String alisa)
            throws Exception {
        
        PrivateKey prikey = null;
        PublicKey pubKey = null;
        try {
            File f = new File(jksFile); // 声明File对象
            
            InputStream input = new FileInputStream(f);
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(input, storePass.toCharArray());
            prikey = (PrivateKey) ks.getKey(alisa, keyPass.toCharArray());
            return prikey;
        } catch (Exception e) {
        
        }
        return null;
    }
    
    /**
     * JKS转私钥
     *
     * @return 返回私钥
     * @throws Exception
     */
    public static PublicKey getPublicKeyByJks(String jksFile, String storePass, String keyPass, String alisa)
            throws Exception {
        
        PrivateKey prikey = null;
        PublicKey pubKey = null;
        try {
            File f = new File(jksFile); // 声明File对象
            
            InputStream input = new FileInputStream(f);
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(input, storePass.toCharArray());
            Certificate c = ks.getCertificate(alisa);
            pubKey = c.getPublicKey();
            
            return pubKey;
        } catch (Exception e) {
        
        }
        return null;
    }
    
    public static String sign(String content, String input_charset, Key key)
            throws UnsupportedEncodingException, Exception {
        Cipher cipher;
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] output = cipher.doFinal(content.getBytes(input_charset));
            return new String(Base64.encode(output));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }
    
    public static String readFile(String filePath, String charSet) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        try {
            FileChannel fileChannel = fileInputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(byteBuffer);
            byteBuffer.flip();
            return new String(byteBuffer.array(), charSet);
        } finally {
            fileInputStream.close();
        }
        
    }
    
    public static String getKey(String string) throws Exception {
        String content = readFile(string, "UTF8");
        return content.replaceAll("\\-{5}[\\w\\s]+\\-{5}[\\r\\n|\\n]", "");
    }
    
    public static String signByPrivate(String content, PrivateKey privateKey, String input_charset) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initSign(privateKey);
        signature.update(content.getBytes(input_charset));
        return new String(Base64.encode(signature.sign()));
    }
    
    public static String signByPrivate(String content, String privateKey, String input_charset) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        PrivateKey privateKeyInfo = getPrivateKey(privateKey);
        return signByPrivate(content, privateKeyInfo, input_charset);
    }
    
    public static boolean verifyByKeyPath(String content, String sign, String publicKeyPath, String input_charset) {
        try {
            return verify(content, sign, getKey(publicKeyPath), input_charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * RSA验签名检查
     *
     * @param content       待签名数据
     * @param sign          签名值
     * @param publicKey     公钥
     * @param input_charset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String publicKey, String input_charset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            return verify(content, sign, pubKey, input_charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }
    
    public static boolean verify(String content, String sign, PublicKey publicKey, String inputCharset) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(content.getBytes(inputCharset));
            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = buildPKCS8Key(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
        
    }
    
    private static byte[] buildPKCS8Key(String privateKey) throws IOException {
        if (privateKey.contains("-----BEGIN PRIVATE KEY-----")) {
            return Base64.decode(privateKey.replaceAll("-----\\w+ PRIVATE KEY-----", ""));
        } else if (privateKey.contains("-----BEGIN RSA PRIVATE KEY-----")) {
            final byte[] innerKey = Base64.decode(privateKey.replaceAll("-----\\w+ RSA PRIVATE KEY-----", ""));
            final byte[] result = new byte[innerKey.length + 26];
            System.arraycopy(Base64.decode("MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKY="), 0, result, 0, 26);
            System.arraycopy(BigInteger.valueOf(result.length - 4).toByteArray(), 0, result, 2, 2);
            System.arraycopy(BigInteger.valueOf(innerKey.length).toByteArray(), 0, result, 24, 2);
            System.arraycopy(innerKey, 0, result, 26, innerKey.length);
            return result;
        } else {
            return Base64.decode(privateKey);
        }
    }
    
    public static KeyInfo getPFXPrivateKey(String pfxPath, String password) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
        FileInputStream fis = new FileInputStream(pfxPath);
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(fis, password.toCharArray());
        fis.close();
        Enumeration<String> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements())// we are readin just one certificate.
        {
            keyAlias = enumas.nextElement();
        }
        
        KeyInfo keyInfo = new KeyInfo();
        
        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, password.toCharArray());
        Certificate cert = ks.getCertificate(keyAlias);
        PublicKey pubkey = cert.getPublicKey();
        
        keyInfo.privateKey = prikey;
        keyInfo.publicKey = pubkey;
        return keyInfo;
    }
    
    public static class KeyInfo {
        
        PublicKey publicKey;
        PrivateKey privateKey;
        
        public PublicKey getPublicKey() {
            return publicKey;
        }
        
        public PrivateKey getPrivateKey() {
            return privateKey;
        }
    }
    
    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        if (key == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        byte[] buffer = Base64.decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        return keyFactory.generatePublic(keySpec);
    }
    
    /**
     * 测试公钥加密——私钥解密
     *
     * @throws Exception
     */
    static void test1() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "iofjio范德萨 iof912108";
        System.out.println("\r加密前文字：\r\n" + source);
        
        RSAUtil rsaUtil = RSAUtil.getInstance(1024);
        KeyPair kp = rsaUtil.generateRandomKeyPair();
        
        // String encodedData = RSAUtil.encryptByPublicKey(source,
        // transKeyToStr(kp.getPublic()));
        // System.out.println("加密后文字长度：\r\n" + encodedData.length());
        // System.out.println("加密后文字：\r\n" + encodedData);
        // String decodedData = RSAUtil.decryptByPrivateKey(encodedData,
        // transKeyToStr(kp.getPrivate()));
        // String target = decodedData;
        // System.out.println("解密后文字: \r\n" + target);
    }
    
    static void test2() throws Exception {
        String privateKey = null;
        String publicKey = null;
        
        RSAUtil rsaUtil = RSAUtil.getInstance(1024);
        KeyPair keyPair = rsaUtil.generateRandomKeyPair();
        // privateKey = RSAUtil.transKeyToStr(keyPair.getPrivate());
        // publicKey = RSAUtil.transKeyToStr(keyPair.getPublic());
        
        byte[] encodedKey = keyPair.getPrivate().getEncoded();
        privateKey = Base64.toBase64String(keyPair.getPrivate().getEncoded());
        publicKey = Base64.toBase64String(keyPair.getPublic().getEncoded());
        
        System.out.println("私钥：" + privateKey);
        System.out.println("公钥：" + publicKey);
    }
    
    public static void main(String args[]) {
        try {
            test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}