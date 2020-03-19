package com.zhihui.user.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * github 配置
 *
 * @author LDZ
 * @date 2020-03-19 20:30
 */
@ConfigurationProperties(prefix = "huizhi.github")
@Configuration
@Data
public class GithubProperties {


    private String clientId;

    private String clientSecret;

}
