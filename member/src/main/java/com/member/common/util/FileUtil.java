package com.member.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件处理
 * @author wzhz
 * @date 2016-07-03
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static void main(String[] args) {
		/*createFile("D:/temp/", "test1.xls");*/
		// List<String> allFileDirectory = getAllFileDirectory("/GreenVehicle/Download/");
		String fileNameNoEx = getExtName("123456789.123456");
		System.out.println(fileNameNoEx);
	}

	/**
	 * 创建文件
	 * @param path
	 * @param fileName
	 */
	public static void createFile(String path, String fileName) {
		//path表示你所创建文件的路径
		File fPath = new File(path);
		if (!fPath.exists()) {
			fPath.mkdirs();
		}
		// fileName表示你创建的文件名；为txt类型；
		File file = new File(fPath, fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("创建文件：\"" + path + fileName + "\"失败" + e.toString());
			}
		}
	}

	/**
	 * 获取指定目录下的文件夹
	 * @param path
	 * @return 
	 */
	public static List<String> getAllFileDirectory(String path) {
		List<String> list = new ArrayList<>();
		File f = new File(path);
		if (!f.exists()) {
			logger.error(path + " not exists");
			return list;
		}
		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			if (fs.isDirectory()) {
				list.add(fs.getName());
			}/* else {
				logger.info(fs.getName());
			}*/
		}
		return list;
	}

	/**
	 * 获取指定目录下的文件
	 * @param path
	 * @return
	 */
	public static List<String> getAllFile(String path) {
		List<String> list = new ArrayList<>();
		File f = new File(path);
		if (!f.exists()) {
			logger.error(path + " not exists");
			return list;
		}

		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			if (!fs.isDirectory()) {
				list.add(fs.getName());
			}
		}
		return list;
	}

	/**
	 * 获取指定目录下的文件夹和文件
	 * @param path
	 * @return
	 */
	public static List<String> getAllFileAndDirectory(String path) {
		List<String> list = new ArrayList<>();
		File f = new File(path);
		if (!f.exists()) {
			logger.error(path + " not exists");
			return list;
		}
		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
			File fs = fa[i];
			/*if (fs.isDirectory()) {
				logger.info(fs.getName() + " [目录]");
			} else {
				logger.info(fs.getName());
			}*/
			list.add(fs.getName());
		}
		return list;
	}

	/**
	 * 保存文件picFile<br>
	 * @param filePath
	 * @param fileName
	 * @param picFile
	 * @return
	 */
	public static boolean saveFile(String filePath, String fileName, File picFile) {
		try {
			File newsFileRoot = new File(filePath);
			if (!newsFileRoot.exists()) {
				newsFileRoot.mkdirs();
			}
			
			FileOutputStream fos = new FileOutputStream(filePath + fileName);
			FileInputStream fis = new FileInputStream(picFile);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) > 0) {
				fos.write(buf, 0, len);
			}
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
			return true;
		} catch (Exception e) {
			logger.error("保存文件：\"" + filePath + fileName + "\"失败" + e.toString());
			return false;
		}
	}
	/**
	 * 将文件复制到指定目录（服务器）
	 * @param file
	 * @param contextPath
	 * @param er
	 */
	@SuppressWarnings("all")
	@Deprecated
	public static boolean fileCopy(File file, String contextPath) {
		FileInputStream in;
		FileOutputStream out;
		try {
			in = new FileInputStream(file);
			int len = in.available();
			int lenM = len / 1024 / 1024;
			out = new FileOutputStream(contextPath);
			byte[] b = new byte[1024];
			int count = -1;
			while ((count = in.read(b)) != -1) {
				out.write(b);
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			logger.error("文件：\"" + contextPath + "\"为空" + e.toString());
			return false;
		} catch (IOException e) {
			logger.error("复制文件：\"" + contextPath + "\"失败" + e.toString());
			return false;
		}
		return true;
	}
	/**
	 * 保存文件MultipartFile<br>
	 * @param multipartFile
	 * @param filePath
	 * @param fileName
	 */
	public static boolean saveFile(MultipartFile multipartFile, String filePath, String fileName){
        File targetFile = new File(filePath, fileName);
        if(!targetFile.exists())
            targetFile.mkdirs();
        //保存
        try {
        	multipartFile.transferTo(targetFile);
        	return true;
        } catch (Exception e) {
            logger.error("保存文件：\"" + filePath + fileName + "\"失败" + e.toString());
            return false;
        }
	}
	/**
	 * Java文件操作<br>
	 * 删除文件<br>
	 * 根据路径删除指定的目录或文件，无论存在与否
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	/**
	 * 删除文件与目录
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFolder(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(filePath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(filePath);
			}
		}
	}
	/**
	 * 删除目录
	 * @param filePath
	 * @return
	 */
	public static boolean deleteDirectory(String filePath) {
		boolean flag = false;
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!filePath.endsWith(File.separator))
			filePath = filePath + File.separator;
		File dirFile = new File(filePath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory())
			return false;
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete())
			return true;
		else
			return false;
	}
	/**
	 * Java文件操作<br>
	 * 获取文件扩展名
	 * @param fileName
	 * @return
	 */
	public static String getExtName(String fileName) {
		if ((fileName != null) && (fileName.length() > 0)) {
			int dot = fileName.lastIndexOf('.');
			if ((dot > -1) && (dot < (fileName.length() - 1))) {
				return fileName.substring(dot + 1);
			}
		}
		return fileName;
	}
	/**
	 * Java文件操作<br>
	 * 获取不带扩展名的文件名
	 * @param fileName
	 * @return
	 */
	public static String getNameNoEx(String fileName) {
		if ((fileName != null) && (fileName.length() > 0)) {
			int dot = fileName.lastIndexOf('.');
			if ((dot > -1) && (dot < (fileName.length()))) {
				return fileName.substring(0, dot);
			}
		}
		return fileName;
	}
	/**
	 * 随机生成23位文件名<br>
	 * 不带拓展名<br>
	 * fileNameFlag:<br>
	 * "G":Goods<br>
	 * "C":普通<br>
	 * @param fileNameFlag
	 * @return
	 */
	public static String getFileName(String flag) {
		if (StringUtil.isEmpty(flag))
			flag = "C";
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(flag).append("!").append(UniqId.uuid());
		return stringBuffer.toString();
	}
	/**
	 * Java文件操作MultipartFile<br>
	 * @param file
	 * @return
	 */
	public static String getFileName(MultipartFile file){
		if (file == null)
			return null;
		return file.getOriginalFilename();
	}
	/**
	 * Java文件操作<br>
	 * @param file
	 * @return
	 */
	public static String getFileName(File file){
		if (file == null)
			return null;
		return file.getName();
	}
	/**
	 * 判断文件夹是否存在<br>
	 * 不存在则创建
	 * @param path 文件夹路径
	 */
	public static void isExist(String path) {
		File file = new File(path);
		// 判断文件夹是否存在,如果不存在则创建文件夹
		if (!file.exists())
			file.mkdir();
	}
	
	/**
	 * 将json符串串写入文件
	 * @param filePath
	 * @param jsonStr
	 * @return
	 */
	public static boolean writeFile(String filePath, String jsonStr) {
		try {
			File file = new File(filePath);
			if (!file.exists())
				file.createNewFile();
			OutputStreamWriter pw = null;//定义一个流
			pw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			// PrintStream ps = new PrintStream(new FileOutputStream(file),false, "utf-8");  
			pw.write(jsonStr);// 往文件里写入字符串  
			pw.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	/**
	 * 按行读取数据
	 * @param fileName
	 * @return
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer strLine = new StringBuffer("");
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				strLine.append(tempString);
			}
			reader.close();
			return strLine.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return strLine.toString();
	}

}