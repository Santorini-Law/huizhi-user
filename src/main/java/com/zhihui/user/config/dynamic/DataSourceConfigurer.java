package com.zhihui.user.config.dynamic;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.zhihui.user.config.dynamic.datasources.*;
import com.zhihui.user.config.dynamic.sharding.DatabaseShardingAlgorithm;
import com.zhihui.user.config.dynamic.sharding.TableShardingAlgorithm;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingjdbc.core.jdbc.core.datasource.ShardingDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Multiple DataSource Configurer
 *
 * @author LDZ
 * @date 2020-03-09 22:07
 */
@Configuration
public class DataSourceConfigurer {

    @Resource
    private UserDatabase0Config userDatabase0Config;

    @Resource
    private UserDatabase1Config userDatabase1Config;

    @Resource
    private UserDatabase2Config userDatabase2Config;

    @Resource
    private UserDatabase3Config userDatabase3Config;

    @Resource
    private UserDatabase4Config userDatabase4Config;

    @Resource
    private UserDatabase5Config userDatabase5Config;

    @Resource
    private UserDatabase6Config userDatabase6Config;

    @Resource
    private UserDatabase7Config userDatabase7Config;


    private DataSource getShardingDataSource() {
        ShardingRuleConfiguration shardingRuleConfig;
        shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("user_base");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("uid", DatabaseShardingAlgorithm.class.getName()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("uid", TableShardingAlgorithm.class.getName()));
        // 分库设置
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        // 添加多个数据库
        dataSourceMap.put(userDatabase0Config.getDatabaseName(), userDatabase0Config.createDataSource());
        dataSourceMap.put(userDatabase1Config.getDatabaseName(), userDatabase1Config.createDataSource());
        dataSourceMap.put(userDatabase2Config.getDatabaseName(), userDatabase2Config.createDataSource());
        dataSourceMap.put(userDatabase3Config.getDatabaseName(), userDatabase3Config.createDataSource());
        dataSourceMap.put(userDatabase4Config.getDatabaseName(), userDatabase4Config.createDataSource());
        dataSourceMap.put(userDatabase5Config.getDatabaseName(), userDatabase5Config.createDataSource());
        dataSourceMap.put(userDatabase6Config.getDatabaseName(), userDatabase6Config.createDataSource());
        dataSourceMap.put(userDatabase7Config.getDatabaseName(), userDatabase7Config.createDataSource());
        try {
            return new ShardingDataSource(shardingRuleConfig.build(dataSourceMap));
        } catch (SQLException e) {
            return null;
        }
    }

    @Bean
    TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("user_base");
        orderTableRuleConfig.setActualDataNodes("user_${0..7}.user_base_${0..1}");
        orderTableRuleConfig.setKeyGeneratorColumnName("uid");
        return orderTableRuleConfig;
    }

    @Bean("sharding")
    public DataSource getDataSource() {
        return getShardingDataSource();
    }

    /**
     * master DataSource
     *
     * @return data source
     */
    @Bean("zebra")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource zebra() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Slave gamma data source.
     *
     * @return the data source
     */
    @Bean("zhihui")
    @ConfigurationProperties(prefix = "spring.datasource.other")
    public DataSource zhihui() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Dynamic data source.
     *
     * @return the data source
     */
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        dataSourceMap.put(DataSourceKey.zebra.name(), zebra());
        dataSourceMap.put(DataSourceKey.sharding.name(), getDataSource());
        dataSourceMap.put(DataSourceKey.zhihui.name(), zhihui());
        // Set master datasource as default
        dynamicRoutingDataSource.setDefaultTargetDataSource(getDataSource());
        // Set master and slave datasource as target datasource
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        // To put datasource keys into DataSourceContextHolder to judge if the datasource is exist
        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
        // To put slave datasource keys into DataSourceContextHolder to load balance
        DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
        DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.sharding.name());
        return dynamicRoutingDataSource;
    }

    /**
     * Sql session factory bean.
     * Here to config datasource for SqlSessionFactory
     * <p>
     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
     * {@code 'application.properties'} file, or there will appear invalid bond statement exception
     *
     * @return the sql session factory bean
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // Here to config mybatis
        sqlSessionFactoryBean.setTypeAliasesPackage("com.zhihui.user.dao");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mapper/mysql/*.xml"));
        // Here is very important, if don't config this, will can't switch datasource
        // put all datasource into SqlSessionFactoryBean, then will autoconfig SqlSessionFactory
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean;
    }

    /**
     * Transaction manager platform transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}

