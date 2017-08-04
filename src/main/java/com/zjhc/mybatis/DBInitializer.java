package com.zjhc.mybatis;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author 漏水亦凡
 * @create 2017-03-19 15:07.
 */
@Order(2)
public class DBInitializer implements WebApplicationInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(DBInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOG.info("DBInitializer 配置");


        //servlet
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet(
                "statViewServlet", statViewServlet);
        servletRegistration.addMapping("/druid/*");
        servletRegistration.setInitParameter("allow", "127.0.0.1");
        servletRegistration.setInitParameter("loginUsername", "root");
        servletRegistration.setInitParameter("loginPassword", "root");
        servletRegistration.setInitParameter("resetEnable", "false");

        //filter
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(
                "webStatFilter", webStatFilter);
        filterRegistration.setInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistration.addMappingForUrlPatterns(null,true,"/*");

    }
}
