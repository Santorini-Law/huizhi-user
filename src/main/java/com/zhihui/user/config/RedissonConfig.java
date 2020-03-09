package com.zhihui.user.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * redis config
 *
 * @author LDZ
 * @date 2020-02-17 10:16
 */
@Configuration
public class RedissonConfig {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private Integer redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    @Bean
    public RedissonClient redissonClient() {
        Config config = setConfig(redisHost, redisPort, redisPassword);
        return Redisson.create(config);
    }

    private Config setConfig(String host, Integer port, String password) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://" + host + ":" + port + "");
        if (!StringUtils.isEmpty(password)) {
            singleServerConfig.setPassword(password);
        }
        return config;
    }

}
