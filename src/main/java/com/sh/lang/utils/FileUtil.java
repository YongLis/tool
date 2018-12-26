package com.sh.lang.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * checkFileIsExist return fale表示文件不存在，true表示文件存在（之前刚好相反，李强修改）
 * FileUtil
 */
public class FileUtil {
	
	public static final String MD5_EXT = ".md5";
	
	public static void writeFileByLine(String subFilePath,List<String> list){
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			osw = new OutputStreamWriter(new FileOutputStream(subFilePath),"utf-8");
			bw = new BufferedWriter(osw);
			for (String string : list) {
				bw.write(string);
				bw.newLine();
			}
		} catch (Exception e) {
		    LOGGER.error("错误：",e);
			throw new RuntimeException(e.getMessage());
		}finally{
			releaseIOResource(null,null, osw, bw);
		}


	}
	private static void releaseIOResource(InputStreamReader isr, BufferedReader br, OutputStreamWriter osw, BufferedWriter bw) {
		try {
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (bw != null) {
				bw.close();
			}
			if (osw != null) {
				osw.close();
			}
		} catch (Exception e) {
			LOGGER.error(String.format("releaseIOResource 出现异常, 异常信息为 %s", e.getMessage()));
		}
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class) ;
	public static List<String> readFileByLine(String sFile)
			throws FileNotFoundException, IOException {
		File inputFile = new File(sFile);// 定义读取的文件源
		return readFileByLine(inputFile);
	}

	public static List<String> readFileByLine(File inputFile) throws FileNotFoundException, IOException {
		List<String> list = new ArrayList<String>();
		InputStreamReader in=null;
		BufferedReader bin=null;
		try {
			FileInputStream fis = new FileInputStream(inputFile);
			in = new InputStreamReader(fis, "UTF-8");
			// 将文件输入流构造到缓存
			bin = new BufferedReader(in);
			String line = null;
			while ((line = bin.readLine()) != null) {
				list.add(line.trim());
			}
			bin.close();
		} catch (Exception e) {
		    LOGGER.error("错误：",e);
			throw new RuntimeException(e.getMessage());
		}finally{
			releaseIOResource(in, bin, null, null);
		}
		return list;
	}
	public static String getFilePathByOS(String path){
		return path.replace("\\",File.separator).replace("/", File.separator);
	}
	public static void createNewFile(String subFilePath) throws IOException{
		File file=new File(subFilePath);
		if(!file.exists()){
			if(file.isDirectory()){
				file.mkdirs();
			}else{
				File parent=file.getParentFile();
				if(!parent.exists()){
					parent.mkdirs();
				}
				file.createNewFile();
			}
		}
	}
	
	public static File createNewFileAndReturn(String filePath) throws IOException{
		File file=new File(filePath);
		if(!file.exists()){
			if(file.isDirectory()){
				file.mkdirs();
			}else{
				File parent=file.getParentFile();
				if(!parent.exists()){
					parent.mkdirs();
				}
				file.createNewFile();
			}
		}
		return file;
	}
	
	/**
	 * 判断文件是否已经存在
	 * 
	 * @param filePath 文件全路径
	 * @return fale表示文件不存在，true表示文件存在
	 */
	public static boolean checkFileIsExist(String filePath) {
		File file = new File(filePath);
		// 文件存在则不下载
		if (file.exists()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 文件进行重命名
	 * 
	 * @param path
	 * @param oldFileName
	 * @param newFileName
	 */
	public static void renameFile(String path, String oldFileName, String newFileName) {
		
		File newFile = new File(path + File.separator + newFileName);
		File oldFile = new File(path + File.separator + oldFileName);
		
		if (!oldFile.exists()) {
			return; // 重命名文件不存在
		}
		
		if (newFile.exists()) {
			return; // 文件存在
		} else {
			oldFile.renameTo(newFile);
		}
	}
	
	/**
	 * 计算文件的MD5摘要值
	 * 
	 * @param path
	 */
	public static String getFileMD5(String inputFile) throws IOException {
		// 缓冲区大小（这个可以抽出一个参数）
		int bufferSize = 256 * 1024;
		FileInputStream fileInputStream = null;
		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用DigestInputStream
			fileInputStream = new FileInputStream(inputFile);
			digestInputStream = new DigestInputStream(fileInputStream, messageDigest);
			// read的过程中进行MD5处理，直到读完文件
			byte[] buffer = new byte[bufferSize];
			while (digestInputStream.read(buffer) > 0) {
			}
			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();
			// 拿到结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();
			// 同样，把字节数组转换成字符串
			return PasswordHash.toHex(resultByteArray);
		} catch (NoSuchAlgorithmException e) {
			return null;
		} finally {
			IOUtils.closeQuietly(digestInputStream);
			IOUtils.closeQuietly(fileInputStream);
		}
	}

	/**
	 * 生成MD5摘要文件
	 * 
	 * @param path
	 */
	public static void createMD5File(String inputFile) throws Exception {
		String md5FileName = inputFile + MD5_EXT;
		String inputFileMD5 = getFileMD5(inputFile);
		FileOutputStream fos = new FileOutputStream(md5FileName);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(inputFileMD5);
		// flush stream
		bw.flush();
		osw.flush();
		fos.flush();
		// close stream
		IOUtils.closeQuietly(bw);
		IOUtils.closeQuietly(osw);
		IOUtils.closeQuietly(fos);
	}
}
