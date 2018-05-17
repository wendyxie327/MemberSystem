package com.member.common.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 
 * 功能：将SQL转换为对象	
 * @author XieWQ
 * @date 2017年11月6日 上午11:18:53
 */
public class SqlVoUtil {

	static String filePath = "C:\\Users\\Xiewenqian\\Desktop\\数据库设计.sql";
	static String newFileName = "Business";
	static String tblName = "tbl_user_account";
	
	public static void main(String[] args) {
		Map<String, String> nameMap = sql2Vo();
		StringBuffer keybuffer = new StringBuffer();
		StringBuffer valuebuffer = new StringBuffer(); 
		for (Entry<String, String> entry : nameMap.entrySet()) {
			keybuffer.append(entry.getKey()).append(", ");
			valuebuffer.append(" #{").append(entry.getValue()).append("},");
		}
		System.out.println(keybuffer.toString());
		System.out.println(valuebuffer.toString());

		createAddSQL(nameMap);
	}
	
	/**
	 * 读取文件
	 * @return
	 */
	public static String parseFileByChars(){
		File file = new File(filePath);
		StringBuffer stringBuffer = new StringBuffer();
		try {
			InputStream inputStream = new FileInputStream(file);
			Reader reader = new InputStreamReader(inputStream,"GBK");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String readStr = null;
			boolean isNeedRead = false;
			while ( (readStr=bufferedReader.readLine())!=null){
				if (readStr.toUpperCase().contains(";" )) {
					isNeedRead = false;
				}
				if (isNeedRead) {
					stringBuffer.append(readStr);
				}
				readStr = readStr.toUpperCase().trim();
				if (readStr.toUpperCase().trim().startsWith(("CREATE TABLE"))) {
					isNeedRead = true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 根据Sql语句，转换成对象
	 */
	public static Map<String, String> sql2Vo(){
		StringBuffer fileBuff = new StringBuffer();
		Map<String, String> map = new LinkedHashMap<String, String>();
		//读取文件
		String fileChars = parseFileByChars();
		System.out.println(parseFileByChars());
		//解析字段
		String[] strArray = fileChars.split("',");
		if (strArray!=null && strArray.length>0) {
			for (int i = 0; i < strArray.length; i++) {
				String[] nameArray = strArray[i].split("`");
				String fieldName = nameArray[1];//获取字段名称
				
				try {
					fileBuff.append("private ");
					
					//判断是哪个类型的
					if (strArray[i].toLowerCase().contains("char") || strArray[i].toLowerCase().contains("text") || strArray[i].toLowerCase().contains("varchar")) {
						fileBuff.append("String ");
					}else if (strArray[i].toLowerCase().contains("int")) {
						fileBuff.append("Integer ");
					}else if(strArray[i].toLowerCase().contains("datetime") || strArray[i].toLowerCase().contains("date")){
						fileBuff.append("Date ");
					}else if (strArray[i].toLowerCase().contains("decimal") ) {
						fileBuff.append("BigDecimal ");
					}else if (strArray[i].toLowerCase().contains("timestamp")) {
						fileBuff.append("Timestamp ");
						
					}
					map.put(fieldName, getCamelCaseFieldName(fieldName));
					
					fileBuff.append(getCamelCaseFieldName(fieldName)).append(";     //")
							.append(nameArray[2])
							.append("\n");
				} catch (Exception e) {
					continue;
				}
			}
			System.out.println(fileBuff.toString());
		}
		return map;
	}
	
	/**
	 * 将名称改为驼峰法命名
	 * @param name
	 * @return
	 */
	private static String getCamelCaseFieldName(String name){
		if (name != null) {
			String[] names = name.split("_");
			StringBuffer content = new StringBuffer("");
			for (int i = 0; i < names.length; i++) {
				if ( i == 0) {
					content.append(names[i]);
				}else{
					String str = names[i];
					content.append(str.replace(str.substring(0, 1), str.substring(0, 1).toUpperCase()));
				}
			}
			return content.toString();
		}
		return "";
	}
	
	private static String createAddSQL(Map<String, String> map){
		StringBuffer buffer = new StringBuffer();
		StringBuffer valueBuffer = new StringBuffer();
		buffer.append("INSERT INTO ").append(tblName).append("\n").append("(");
		int i=0;//用于分行
		for (Entry<String, String> entry : map.entrySet()) {
			buffer.append(" ").append(entry.getKey()).append(",");
			valueBuffer.append(" #{").append(entry.getKey()).append("},");
			i++;
			if (i>5) {
				i=0;
				buffer.append("\n");
				valueBuffer.append("\n");
			}
		}
		if (buffer.toString().endsWith("\n")) {
			buffer.deleteCharAt(buffer.length()-3);//去除最后面的逗号和\n
			valueBuffer.deleteCharAt(valueBuffer.length()-3);
		}else{
			buffer.deleteCharAt(buffer.length()-1);//去除最后面的逗号
			valueBuffer.deleteCharAt(valueBuffer.length()-1);//去除最后面逗号
		}
		
		
		buffer.append(")\n").append("VALUES\n")
			.append("(").append(valueBuffer).append(")");
		System.out.println(buffer.toString());
		return null;
	}
}
