package com.john.miaosha.order.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.john.miaosha.order.mapper", sqlSessionFactoryRef  = "msSqlSessionFactory")
public class DataSourceConfig {

    private final String sqlmap = "classpath*:mappers/*.xml";


    @Bean(name = "msDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.miaosha")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "msSqlSessionFactory")
    @Primary
    public SqlSessionFactory sourcegetSqlSessionFactory(@Qualifier("msDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(sqlmap));
        //保证jar模式运行
        bean.setVfs(SpringBootVFS.class);
        return bean.getObject();
    }

    @Bean(name = "msTransactionManager")
    @Primary
    public DataSourceTransactionManager sourcegetTransactionManager(@Qualifier("msDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
