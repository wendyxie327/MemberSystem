package com.member.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http或Https请求工具类
 */
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static final String POST_REQUEST_METHOD = "POST";
	public static final String GET_REQUEST_METHOD = "GET";

	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param params 提交的数据（参数）
	 * @return JSON字符串
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String params) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
			httpsUrlConn.setDoOutput(true);
			httpsUrlConn.setDoInput(true);
			httpsUrlConn.setUseCaches(false);
			httpsUrlConn.setRequestMethod(requestMethod);// 设置请求方式（GET/POST）
			httpsUrlConn.setRequestProperty("Content-Type", "application/json");

			ResPropertiesUtil properties = new ResPropertiesUtil();
			String applicationId = properties.getProperty("applicationId");
			String restapikey = properties.getProperty("restApikey");
			if (null != applicationId) {
				httpsUrlConn.setRequestProperty("X-Bmob-Application-Id", applicationId);
			}
			if (null != restapikey) {
				httpsUrlConn.setRequestProperty("X-Bmob-REST-API-Key", restapikey);
			}

			httpsUrlConn.connect();

			// 当有数据需要提交时
			if (null != params) {
				OutputStream outputStream = httpsUrlConn.getOutputStream();
				outputStream.write(params.getBytes("UTF-8"));// 注意编码格式，防止中文乱码
				outputStream.close();
			}
			int retcode = httpsUrlConn.getResponseCode();

			if (retcode == 200) {
				// 将返回的输入流转换成字符串
				InputStream inputStream = httpsUrlConn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

				String str = null;
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				// 释放资源
				inputStream.close();
				inputStream = null;
			}
			httpsUrlConn.disconnect();
		} catch (Exception e) {
			logger.error("https connection timed out.", e);
		}
		return buffer.toString();
	}

	/**
	 * 发起http请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param params 提交的数据（参数）
	 * @return JSON字符串
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String params) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setConnectTimeout(10000);//设置超时时间10秒
			httpUrlConn.setDoOutput(true);//可发送数据
			httpUrlConn.setDoInput(true);//可接收数据
			httpUrlConn.setUseCaches(false);//不使用缓存
			httpUrlConn.addRequestProperty("Accept", "*/*");//接收数据类型
			httpUrlConn.setRequestMethod(requestMethod);// 设置请求方式（GET/POST）
			httpUrlConn.connect();//链接

			// 当有数据需要提交时
			if (null != params) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(params.getBytes("UTF-8"));//设置编码格式
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			logger.error("http connection timed out. URL=" + requestUrl, e);
		}
		return buffer.toString();
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			logger.error("Class: cn.eagle.framework.utils.HttpUtil, method: sendGet()", e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定URL发送POST方法的请求
	 * 
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是name1=value1&name2=value2的形式。
	 * @return URL所代表远程资源的响应
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			logger.error("Class: cn.eagle.framework.utils.HttpUtil, method: sendPost()", e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
