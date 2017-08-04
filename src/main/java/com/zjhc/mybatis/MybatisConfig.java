package com.zjhc.mybatis;

import com.github.pagehelper.PageInterceptor;
import com.zjhc.mybatis.util.MyMapper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 漏水亦凡
 * @create 2017-03-17 9:52.
 */

@Configuration
@Import({DBConfig.class, TXConfig.class})
public class MybatisConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisConfig.class);

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
            factoryBean.setMapperLocations(resources);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("配置SqlSessionFactory中mapper/*.xml文件出错", e);
        }

        factoryBean.setTypeAliasesPackage("com.zjhc");

        Interceptor[] interceptors = {pageInterceptor()};
        factoryBean.setPlugins(interceptors);

        return factoryBean;
    }

    /**
     * PageHelper插件
     *
     * @return
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        properties.setProperty("autoRuntimeDialect", "true");
        interceptor.setProperties(properties);
        return interceptor;
    }

    /**
     * 继承于mybatis的 tk包中的扫描配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.zjhc.**.mapper");
        configurer.setMarkerInterface(MyMapper.class);
        return configurer;
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }

}
