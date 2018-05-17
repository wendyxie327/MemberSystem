package com.member.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @author wzhz 
*  
*/
public class UniqId {
	private static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static Map<Character, Integer> rDigits = new HashMap<Character, Integer>(16);
	static {
		for (int i = 0; i < digits.length; ++i) {
			rDigits.put(digits[i], i);
		}
	}

	private static UniqId me = new UniqId();
	private String hostAddr;
	private Random random = new SecureRandom();
	private MessageDigest mHasher;
	private UniqTimer timer = new UniqTimer();

	private ReentrantLock opLock = new ReentrantLock();

	private UniqId() {
		try {
			InetAddress addr = InetAddress.getLocalHost();

			hostAddr = addr.getHostAddress();
		} catch (IOException e) {
			hostAddr = String.valueOf(System.currentTimeMillis());
		}

		if (hostAddr == null || hostAddr.length() == 0 || "127.0.0.1".equals(hostAddr)) {
			hostAddr = String.valueOf(System.currentTimeMillis());
		}

		try {
			mHasher = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nex) {
			mHasher = null;
		}
	}

	/** 
	 * 获取UniqID实例 
	 *  
	 * @return UniqId 
	 */
	public static UniqId getInstance() {
		return me;
	}

	/** 
	 * 获得不会重复的毫秒数 
	 *  
	 * @return 
	 */
	public long getUniqTime() {
		return timer.getCurrentTime();
	}

	/** 
	 * 获得UniqId 
	 *  
	 * @return uniqTime-randomNum-hostAddr-threadId 
	 */
	public String getUniqID() {
		StringBuffer sb = new StringBuffer();
		long t = timer.getCurrentTime();

		sb.append(t);

		sb.append("-");

		sb.append(random.nextInt(8999) + 1000);

		sb.append("-");
		sb.append(hostAddr);

		sb.append("-");
		sb.append(Thread.currentThread().hashCode());

		return sb.toString();
	}

	/** 
	 * 获取MD5之后的uniqId string 
	 *  
	 * @return uniqId md5 string 
	 */
	public String getUniqIDHashString() {
		return hashString(getUniqID());
	}

	/** 
	 * 获取MD5之后的uniqId 
	 *  
	 * @return byte[16] 
	 */
	public byte[] getUniqIDHash() {
		return hash(getUniqID());
	}

	/** 
	 * 对字符串进行md5 
	 *  
	 * @param str 
	 * @return md5 byte[16] 
	 */
	public byte[] hash(String str) {
		opLock.lock();
		try {
			byte[] bt = mHasher.digest(str.getBytes("UTF-8"));
			if (null == bt || bt.length != 16) {
				throw new IllegalArgumentException("md5 need");
			}
			return bt;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("unsupported utf-8 encoding", e);
		} finally {
			opLock.unlock();
		}
	}

	/** 
	 * 对二进制数据进行md5 
	 *  
	 * @param data
	 * @return md5 byte[16] 
	 */
	public byte[] hash(byte[] data) {
		opLock.lock();
		try {
			byte[] bt = mHasher.digest(data);
			if (null == bt || bt.length != 16) {
				throw new IllegalArgumentException("md5 need");
			}
			return bt;
		} finally {
			opLock.unlock();
		}
	}

	/** 
	 * 对字符串进行md5 string 
	 *  
	 * @param str 
	 * @return md5 string 
	 */
	public String hashString(String str) {
		byte[] bt = hash(str);
		return bytes2string(bt);
	}

	/** 
	 * 对字节流进行md5 string 
	 *  
	 * @param str 
	 * @return md5 string 
	 */
	public String hashBytes(byte[] str) {
		byte[] bt = hash(str);
		return bytes2string(bt);
	}

	/** 
	 * 将一个字节数组转化为可见的字符串 
	 *  
	 * @param bt 
	 * @return 
	 */
	public String bytes2string(byte[] bt) {
		int l = bt.length;

		char[] out = new char[l << 1];

		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = digits[(0xF0 & bt[i]) >>> 4];
			out[j++] = digits[0x0F & bt[i]];
		}

		return new String(out);
	}

	/** 
	 * 将字符串转换为bytes 
	 *  
	 * @param str 
	 * @return byte[] 
	 */
	public byte[] string2bytes(String str) {
		if (null == str) {
			throw new NullPointerException("参数不能为空");
		}
		if (str.length() != 32) {
			throw new IllegalArgumentException("字符串长度必须是32");
		}
		byte[] data = new byte[16];
		char[] chs = str.toCharArray();
		for (int i = 0; i < 16; ++i) {
			int h = rDigits.get(chs[i * 2]).intValue();
			int l = rDigits.get(chs[i * 2 + 1]).intValue();
			data[i] = (byte) ((h & 0x0F) << 4 | (l & 0x0F));
		}
		return data;
	}

	/** 
	 * 实现不重复的时间 
	 *  
	 * @author dogun 
	 */
	private static class UniqTimer {
		private AtomicLong lastTime = new AtomicLong(System.currentTimeMillis());

		public long getCurrentTime() {
			return this.lastTime.incrementAndGet();
		}
	}
	/**
	 * 生成<br>5580e035-4122-4452-9aa6-ac3371e8ee23<br>
	 * UUID(Universally Unique Identifier)全局唯一标识符,是指在一台机器上生成的数字,它保证对在同一时空中的所有机器都是唯一的.
	 * 按照开放软件基金会(OSF)制定的标准计算,用到了以太网卡地址,纳秒级时间,芯片ID码和许多可能的数字.
	 * 由以下几部分的组合:
	 * 当前日期和时间(UUID的第一个部分与时间有关,如果你在生成一个UUID之后,过几秒又生成一个UUID,则第一个部分不同,其余相同),
	 * 时钟序列,
	 * 全局唯一的IEEE机器识别号(如果有网卡,从网卡获得,没有网卡以其他方式获得),
	 * UUID的唯一缺陷在于生成的结果串会比较长.
	 * @return
	 */
	public static String uuid() {
		String uuidStr = UUID.randomUUID().toString();
		return uuidStr.replace("-", "");
	}
	/**
	 * 通过时间yyyyMMddHHmmss + 3位随机数生成userId
	 * @return
	 */
	public static String getUserId() {
		String formatDateTime = DateUtil.formatDateTime(DateUtil.getNowDate(), DateUtil.dateTimeStringF);
		return formatDateTime + getRandom(3);
	}


	/**
	 * 获取随机数(仅数字)
	 * @param len 几位
	 * @return
	 */
	public static String getRandom(int len) {
		Random random = new Random();
		String result = "";
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				result += random.nextInt(9);
			}
		}
		return result;
	}
}