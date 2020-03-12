package com.zhihui.user.config;

import com.zhihui.utils.api.config.BaseWebMvcConfig;
import com.zhihui.utils.api.exception.BaseExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Config for application
 *
 * @author LDZ
 * @date 2020-03-12 15:35
 */

@Configuration
public class WebMvcConfigurer extends BaseWebMvcConfig {

    @Bean
    public BaseExceptionHandler baseExceptionHandler() {
        return new BaseExceptionHandler();
    }
}
