package com.member.common.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

class PasswordHash {

	/*
	 * <pre> 可用于替换的加密方法： PBEWithMD5AndDES PBEWithMD5AndTripleDES PBEWithSHA1AndDESede PBEWithSHA1AndRC2_40 等 public static final String ALGORITHM = "PBEWITHMD5andDES"; </pre>
	 */

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	// The following constants may be changed without breaking existing hashes.
	public static final int SALT_BYTE_SIZE = 18;//盐的长度
	public static final int HASH_BYTE_SIZE = 18;//加盐密码的长度，最好和盐的长度一致。
	public static final int PBKDF2_ITERATIONS = 300;//
	public static final int SALT_INDEX = 0;//盐的索引。
	public static final int PBKDF2_INDEX = 1;//加盐后的密码的索引。

	/**
	 * 获取这个类模板下生成的加密密码的长度
	 * @return
	 */
	public static int getCodeLength() {
		return 2 * (SALT_BYTE_SIZE + HASH_BYTE_SIZE) + 1;
	}

	/**
	 * Returns a salted PBKDF2 hash of the password.
	 * @param   password    the password to hash
	 * @return              a salted PBKDF2 hash of the password
	 * @throws Exception 
	 * @throws Except 
	 */
	public static String createHash(String password) throws Exception {
		return createHash(password.toCharArray());
	}

	/**
	 * Returns a salted PBKDF2 hash of the password.
	 *
	 * @param   password    the password to hash
	 * @return              a salted PBKDF2 hash of the password
	 */
	public static String createHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// 随机生成一串24位的的盐
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);

		// Hash the password对密码加盐
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		// format iterations:salt:hash将盐和密码
		return toHex(salt) + ":" + toHex(hash);
	}

	/**
	 * Validates（使生效，这里是验证密码是否有效的意思） a password using a hash.
	 *
	 * @param   password        the password to check
	 * @param   correctHash     the hash of the valid password
	 * @return                  true if the password is correct, false if not
	 */
	public static boolean validatePassword(String password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return validatePassword(password.toCharArray(), correctHash);
	}

	/**
	 * 用已有正确已加密密码Hash验证一个提供的密码是否正确。
	 * Validates a password using a hash.
	 *
	 * @param   password        the password to check
	 * @param   correctHash     the hash of the valid password
	 * @return                  true if the password is correct, false if not
	 */
	public static boolean validatePassword(char[] password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
		// Decode the hash into its parameters
		//将数据库中的Hash代码解析成具体成分：iteration常数、盐、密码hash值
		String[] params = correctHash.split(":");
		int iterations = PBKDF2_ITERATIONS;
		byte[] salt = fromHex(params[SALT_INDEX]);
		byte[] hash = fromHex(params[PBKDF2_INDEX]);
		// Compute the hash of the provided password, using the same salt,
		// iteration count, and hash length
		//　用解析出的盐对提供的待验password进行Hash散列
		byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
		// Compare the hashes in constant time. The password is correct if
		// both hashes match.
		//　对比解析出的密码Hash和待验密码的Hash，返回boolean类型。
		return slowEquals(hash, testHash);
	}

	/**
	 * [慢速比对]这个方法将两个字符数组进行逐个元素的通项对比，最后返回两个数组的同异（boolean）。
	 * 这种对比方式保证不论数组的相似程度，比对耗时长短是固定的。
	 * Compares two byte arrays in length-constant time. This comparison method
	 * is used so that password hashes cannot be extracted from an on-line 
	 * system using a timing attack and then attacked off-line.
	 * 
	 * @param   a       the first byte array
	 * @param   b       the second byte array 
	 * @return          true if both byte arrays are the same, false if not
	 */
	private static boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < a.length && i < b.length; i++)
			diff |= a[i] ^ b[i];
		return diff == 0;
	}

	/**
	 *  Computes the PBKDF2 hash of a password.
	 *
	 * @param   password    the password to hash.
	 * @param   salt        the salt
	 * @param   iterations  the iteration count (slowness factor)
	 * @param   bytes       the length of the hash to compute in bytes
	 * @return              the PBDKF2 hash of the password
	 */
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		//PBE对称加密算法，见http://snowolf.iteye.com/blog/380761
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return skf.generateSecret(spec).getEncoded();
	}

	/**
	 * 此方法将十六进制（hex）字符串转为二进制字节数组
	 * Converts a string of hexadecimal characters into a byte array.
	 *　binary-二进制的
	 * @param   hex         the hex string
	 * @return              the hex string decoded into a byte array
	 */
	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	/**
	 * 将一个字节数组转化为一个16进制的字符串
	 * Converts a byte array into a hexadecimal string.
	 *
	 * @param   array       the byte array to convert
	 * @return              a length*2 character string encoding the byte array
	 */
	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}

	/**
	 * 
	 *
	 * 这个测试程序主要用来测试生成code，和测试加密过程是否可靠。
	 * Tests the basic functionality of the PasswordHash class
	 * @param   args        ignored
	 */
	public static void main(String[] args) {//TODO 测试程序

		try {
			// 尝试生成HashsCode：
			String p = PasswordHash.createHash("p");
			System.out.println(p);
			String[] pp = p.split(":");
			System.out.println(pp[0].length() + "\n" + pp[1].length());
			System.out.println(getCodeLength());
			// Test password validation
			boolean failure = false;
			System.out.println("Running tests...");
			for (int i = 0; i < 100; i++) {
				String password = "" + i;
				String hash = createHash(password);
				String secondHash = createHash(password);
				if (hash.equals(secondHash)) {
					System.out.println("出现了重复的hashCode");
					failure = true;
				}
				String wrongPassword = "" + (i + 1);
				if (validatePassword(wrongPassword, hash)) {
					System.out.println("FAILURE: WRONG PASSWORD ACCEPTED!");
					failure = true;
				}
				if (!validatePassword(password, hash)) {
					System.out.println("FAILURE: GOOD PASSWORD NOT ACCEPTED!");
					failure = true;
				}
			}
			if (failure)
				System.out.println("TESTS FAILED!");
			else
				System.out.println("测试通过，无HashCode碰撞，密码可验证!");
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex);
		}
	}

}
