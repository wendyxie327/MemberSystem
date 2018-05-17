package com.member.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.common.dto.ControllerResult;
import com.member.common.util.JsonUtil;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 自定义异常处理器
 */
public class CommonExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) {
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            ModelAndView modelAndView = new ModelAndView();
            if (ex instanceof AccessDeniedException) {
                modelAndView.setViewName("warn");
            } else {
                modelAndView.setViewName("error");
            }
            return modelAndView;
        } else {// JSON格式返回
            try {
                ControllerResult<Object> result = null;
                if (ex instanceof AccessDeniedException) {
                    result = new ControllerResult<Object>(false, "您没有权限访问此功能");
                } else {
                    ex.printStackTrace();
                    result = new ControllerResult<Object>(false, "发生异常,请稍后再试!");
                }
                PrintWriter writer = response.getWriter();
                writer.write(JsonUtil.entityToJson(result));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
