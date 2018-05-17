package com.member.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "")
public class ViewController {

    /**
     * 输出日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 页面前缀
     */
    private final String JSP_PATH_PREFIX = "views";

    /**
     * Spring转发未知控制器
     * /jsp1/jsp2  转换成  /views/user/jsp.jsp
     *
     * @param module
     * @param jsp
     * @return
     */
    @RequestMapping(value = "/views/{module}/{jsp}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String jspViews(@PathVariable(value = "module") String module, @PathVariable(value = "jsp") String jsp) {
        String JSP = "views/" + module + "/" + jsp;
        logger.debug("访问页面:/" + JSP + ".jsp");
        return JSP;
    }


    /**
     * Spring转发未知控制器
     * /jsp2  转换成  /views/user/jsp.jsp
     *
     * @param jsp
     * @return
     */
    @RequestMapping(value = "/views/{jsp}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String jspViews(@PathVariable(value = "jsp") String jsp) {
        String JSP = "views/" + jsp;
        logger.debug("访问页面:/" + JSP + ".jsp");
        return JSP;
    }


    /**
     * Spring转发未知控制器
     * /jsp2  转换成  /user/jsp.jsp
     *
     * @param jsp
     * @return
     */
    @RequestMapping(value = "/{jsp}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String jsp(@PathVariable(value = "jsp") String jsp) {
        String JSP = jsp;
        logger.debug("访问页面:/" + JSP + ".jsp");
        return JSP;
    }
}
