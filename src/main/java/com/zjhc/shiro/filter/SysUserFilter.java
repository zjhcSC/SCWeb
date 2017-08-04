package com.zjhc.shiro.filter;

import com.zjhc.mybatis.service.UserService;
import com.zjhc.shiro.ShiroConfig;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 将用户 添加到 session
 */
public class SysUserFilter extends AccessControlFilter {

    private static Logger logger = LoggerFactory.getLogger(SysUserFilter.class);

    @Autowired
    UserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = getSubject(request, response);
        if (subject == null) {
            // 没有登录
            logger.debug("未登录");
            return false;
        }

        //保存username
        String username = (String) subject.getPrincipal();
        request.setAttribute(ShiroConfig.SYS_USERNAME, username);

        //保存user
        HttpSession session = WebUtils.toHttp(request).getSession();
        Object user = session.getAttribute(ShiroConfig.SYS_USER);
        if (user == null) {
            logger.debug("session中用户为空，加载用户信息");

            user = userService.findByUsername(username);

            session.setAttribute(ShiroConfig.SYS_USER, user);
        }


        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }
}
