package com.member.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.common.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 判断是否登录
 *
 * @author wzhz
 * @date 2017-04-13
 */
public class LoginFilter implements Filter {

    /**
     * 输出日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String page_404 = "/404";
    private static final String page_login = "/login";
    private static final String page_register = "/register";

    private static String[] eliminate = { page_login, page_404, page_register }; // 需要过滤的页面

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        String currentURL = request.getRequestURI();
        String ctxPath = request.getContextPath();
        // 除掉项目名称时访问页面当前路径
        String targetURL = currentURL.substring(ctxPath.length());
        HttpSession session = request.getSession();
        logger.debug("LoginFilter:" + targetURL + ";ctxPath:" + ctxPath + ";currentURL:" + currentURL);

        // 对当前页面进行判断，如果当前页面不为登录页面 或表示如果当前请求是访问静态资源，则进行正常的处理
        for (int i = 0; i < eliminate.length; i++) {
            if (targetURL.contains(eliminate[i])) {
                arg2.doFilter(request, response);
                return;
            }
        }
        logger.debug("LoginFilter:" + targetURL + ";ctxPath:" + ctxPath + ";currentURL:" + currentURL);

        // 在不为登陆页面时，再进行判断，如果不是登陆页面也没有session则跳转到登录页面，
        if (BeanUtil.isBlank(session) || (BeanUtil.isBlank(session.getAttribute("userInfo")))) {
            response.sendRedirect(ctxPath + page_login ); // 未登录 - 跳转到登陆页面
            return;
        } else {
            // 这里表示正确，会去寻找下一个链，如果不存在，则进行正常的页面跳转
            arg2.doFilter(request, response);
            return;
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        // super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }
}
