package com.zjhc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 入口
 *
 * @author 漏水亦凡
 * @create 2017-03-17 16:48.
 */
@Order(1)
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final Logger LOG = LoggerFactory.getLogger(WebInitializer.class);

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        LOG.info("WebInitializer 配置");

        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 映射 DispatcherServlet 到 "/"
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 注册过滤器，映射路径与DispatcherServlet一致，
     * 路径不一致的过滤器需要注册到另外的WebApplicationInitializer中
     *
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return new Filter[]{encodingFilter};
    }


}
