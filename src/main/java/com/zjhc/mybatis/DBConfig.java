package com.zjhc.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 漏水亦凡
 * @create 2017-03-17 10:05.
 */
@Configuration
@PropertySource({"classpath:db.properties"})
public class DBConfig {
    private static final Logger LOG = LoggerFactory.getLogger(DBConfig.class);

    @Autowired
    private Environment env;

    private static final String P_DB_DRIVER = "db.driver";
    private static final String P_DB_PASSWORD = "db.password";
    private static final String P_DB_URL = "db.url";
    private static final String P_DB_USERNAME = "db.username";

    private static final String P_DB_MAXACTIVE = "db.maxActive";
    private static final String P_DB_INITSIZE = "db.initialSize";
    private static final String P_DB_MAXWAIT = "db.maxWait";
    private static final String P_DB_MINIDLE = "db.minIdle";
    private static final String P_DB_TBERM = "db.timeBetweenEvictionRunsMillis";
    private static final String P_DB_MEITM = "db.minEvictableIdleTimeMillis";
    private static final String P_DB_VQ = "db.validationQuery";
    private static final String P_DB_TESTWHILEIDEL = "db.testWhileIdle";
    private static final String P_DB_TESTONBORROW = "db.testOnBorrow";
    private static final String P_DB_TESTONRETURN = "db.testOnReturn";
    private static final String P_DB_FILTERS = "db.filters";

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(P_DB_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(P_DB_URL));
        dataSource.setUsername(env.getRequiredProperty(P_DB_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(P_DB_PASSWORD));

        dataSource.setMaxActive(env.getRequiredProperty(P_DB_MAXACTIVE, Integer.class));
        dataSource.setInitialSize(env.getRequiredProperty(P_DB_INITSIZE, Integer.class));
        dataSource.setMaxWait(env.getRequiredProperty(P_DB_MAXWAIT, Long.class));
        dataSource.setMinIdle(env.getRequiredProperty(P_DB_MINIDLE, Integer.class));
        dataSource.setTimeBetweenEvictionRunsMillis(env.getRequiredProperty(P_DB_TBERM, Long.class));
        dataSource.setMinEvictableIdleTimeMillis(env.getRequiredProperty(P_DB_MEITM, Long.class));
        dataSource.setValidationQuery(env.getRequiredProperty(P_DB_VQ));
        dataSource.setTestWhileIdle(env.getRequiredProperty(P_DB_TESTWHILEIDEL, Boolean.class));
        dataSource.setTestOnBorrow(env.getRequiredProperty(P_DB_TESTONBORROW, Boolean.class));
        dataSource.setTestOnReturn(env.getRequiredProperty(P_DB_TESTONRETURN, Boolean.class));
        try {
            dataSource.setFilters(env.getRequiredProperty(P_DB_FILTERS));
        } catch (SQLException e) {
            LOG.error("创建数据源出错",e);
        }
        return dataSource;
    }



}
