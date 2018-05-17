package com.member.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码的处理类
 * @author wzhz
 * @date 2015-11-03
 */
public class CreateImageCode {

	// 图片的宽度。
	private int width = 160;
	// 图片的高度。
	private int height = 40;
	// 验证码字符个数
	private int codeCount = 4;
	// 验证码干扰线数
	private int lineCount = 10;
	// 随机字符
	private String randomStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	// private String randomStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	// 验证码
	private String code = null;
	// 验证码图片Buffer
	private BufferedImage buffImg = null;

	Random random = new Random();

	/**
	 * 默认构造器
	 */
	private CreateImageCode() {
		creatImage();
	}

	/**
	 * 通过构造器创建自定义图片大小的验证码
	 * @param width 图片的宽度。
	 * @param height 图片的高度。
	 */
	private CreateImageCode(int width, int height) {
		this.width = width;
		this.height = height;
		creatImage();
	}

	/**
	 * 通过构造器创建自定义图片大小、字符个数的验证码
	 * @param width 图片的宽度。
	 * @param height 图片的高度。
	 * @param codeCount 验证码字符个数
	 */
	private CreateImageCode(int width, int height, int codeCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		creatImage();
	}

	/**
	 * 通过构造器创建自定义图片大小、字符个数、干扰线数的验证码
	 * @param width 图片的宽度。
	 * @param height 图片的高度。
	 * @param codeCount 验证码字符个数
	 * @param lineCount 验证码干扰线数
	 */
	private CreateImageCode(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
		creatImage();
	}

	// 生成图片
	private void creatImage() {
		int fontWidth = width / codeCount;// 字体的宽度
		int fontHeight = height - 5;// 字体的高度
		int codeY = height - 8;

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		// Graphics2D g = buffImg.createGraphics();

		// 设置背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设置字体
		Font font = getFont(fontHeight);
		// Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		g.setFont(font);

		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}

		// 添加噪点
		float yawpRate = 0.01f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			buffImg.setRGB(x, y, random.nextInt(255));
		}

		String str1 = randomStr(codeCount);// 得到随机字符
		this.code = str1;
		for (int i = 0; i < codeCount; i++) {
			String strRand = str1.substring(i, i + 1);
			g.setColor(getRandColor(1, 255));
			// g.drawString(a,x,y);
			// a为要画出来的东西，x和y表示要画的东西最左侧字符的基线位于此图形上下文坐标系的 (x, y) 位置处

			g.drawString(strRand, i * fontWidth + 3, codeY);
		}

	}

	/**
	 * 得到随机字符
	 * @param n
	 * @return
	 */
	private String randomStr(int n) {
		String str = "";
		int len = randomStr.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str = str + randomStr.charAt((int) r);
		}
		return str;
	}

	/**
	 * 得到随机颜色
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 产生随机字体
	 */
	private Font getFont(int size) {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, size);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
		font[2] = new Font("Fixedsys", Font.PLAIN, size);
		font[3] = new Font("Wide Latin", Font.PLAIN, size);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
		return font[random.nextInt(5)];
	}

	// 扭曲方法
	@SuppressWarnings("unused")
	private void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private void shearX(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private void shearY(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}

	}

	/**
	 * 使用ImageIO工具将图片流写入OutputStream
	 * @param sos
	 * @throws IOException
	 */
	private void write(OutputStream sos) throws IOException {
		ImageIO.write(this.getBuffImg(), "png", sos);
		sos.close();
	}

	private BufferedImage getBuffImg() {
		return buffImg;
	}

	private String getCode() {
		return code.toLowerCase();
	}

	/**
	 * 使用默认构造器创建CreateImageCode对象
	 * @return
	 */
	public static CreateImageCode generateVerifyCode() {
		return new CreateImageCode();
	}

	/**
	 * 通过构造器创建自定义图片大小的验证码CreateImageCode对象
	 * @param width 图片的宽度。
	 * @param height 图片的高度。
	 */
	public static CreateImageCode generateVerifyCode(int width, int height) {
		return new CreateImageCode(width, height);
	}

	/**
	 * 通过构造器创建自定义图片大小、字符个数的验证码CreateImageCode对象
	 * @param width 图片的宽度。
	 * @param height 图片的高度。
	 * @param codeCount 验证码字符个数
	 */
	public static CreateImageCode generateVerifyCode(int width, int height, int codeCount) {
		return new CreateImageCode(width, height, codeCount);
	}

	/**
	 * 通过构造器创建自定义图片大小、字符个数、干扰线数的验证码CreateImageCode对象
	 * @param width 图片的宽度。
	 * @param height 图片的高度。
	 * @param codeCount 验证码字符个数
	 * @param lineCount 验证码干扰线数
	 */
	public static CreateImageCode generateVerifyCode(int width, int height, int codeCount, int lineCount) {
		return new CreateImageCode(width, height, codeCount, lineCount);
	}

	/**
	 * 生成验证码 调用此方法： 会生成一个默认大小随机字体的4位数字验证码，<br>
	 * 并将图片流写入response，4位数字验证码写入Session-verifyCode
	 * @param request 请求
	 * @param response 响应
	 * @return verifyCode
	 * @throws IOException
	 */
	public static String writeVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 使用默认的构造器
		CreateImageCode vCode = new CreateImageCode();
		request.getSession().setAttribute("verifyCode", vCode.getCode());
		vCode.write(response.getOutputStream());
		return vCode.getCode();
	}

	/**
	 * 自定义规则生成验证码 调用此方法： 会生成一个自定义规则的验证码，<br>
	 * 并将图片流写入response，验证码写入Session-verifyCode
	 * @param request 请求
	 * @param response 响应
	 * @return verifyCode
	 * @throws IOException
	 */
	public static String writeVerifyCode(CreateImageCode createImageCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		request.getSession().setAttribute("verifyCode", createImageCode.getCode());
		createImageCode.write(response.getOutputStream());
		return createImageCode.getCode();
	}

	/**
	 * 校验验证码（不区别大小写）
	 * @param verifyCode
	 * @param code
	 * @return true正确、false错误
	 */
	public static boolean checkVerifyCode(String verifyCode, String code){
		if (StringUtil.isEmpty(verifyCode) || StringUtil.isEmpty(code))
			return false;
		if (verifyCode.equalsIgnoreCase(code))
			return true;
		return false;
	}

}