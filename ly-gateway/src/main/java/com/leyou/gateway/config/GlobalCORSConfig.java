package com.leyou.gateway.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author wangxin
 * @date 2020/2/11 17:45
 * @description: 网关配置解决跨域访问问题
 * GOOD LUCK！
 */
@Configuration
@EnableConfigurationProperties(CORSProperties.class)
public class GlobalCORSConfig {
    @Bean
    public CorsFilter corsFilter(CORSProperties prop) {
//        //1.添加CORS配置信息
//        CorsConfiguration config = new CorsConfiguration();
//        //1) 允许的域,不要写*，否则cookie就无法使用了
//        config.addAllowedOrigin("http://manage.leyou.com");
//        config.addAllowedOrigin("http://www.leyou.com");
//        //2) 是否发送Cookie信息
//        config.setAllowCredentials(true);
//        //3) 允许的请求方式
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("DELETE");
//        // 4）允许的头信息
//        config.addAllowedHeader("*");
//        // 5）有效期 单位秒
//        config.setMaxAge(3600L);
//        //2.添加映射路径，我们拦截一切请求
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration("/**", config);
//        //3.返回新的CORSFilter
//        return new CorsFilter(configSource);

        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        prop.getAllowedOrigins().forEach(config::addAllowedOrigin);
        //2) 是否发送Cookie信息
//        config.setAllowCredentials(prop.getAllowCredentials());
        config.setAllowCredentials(prop.getAllowedCredentials());
        //3) 允许的请求方式
        prop.getAllowedMethods().forEach(config::addAllowedMethod);
        // 4）允许的头信息
        prop.getAllowedHeaders().forEach(config::addAllowedHeader);
        // 5）配置有效期 单位秒
        config.setMaxAge(prop.getMaxAge());
        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration(prop.getFilterPath(), config);
        //3.返回新的CORSFilter.
        return new CorsFilter(configSource);
    }
}
