//package com.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * ClassName: CorsConfig
// * Package: com.demo.config
// * Description: 跨域设置
// *
// * @Author: 王方舟
// * @Create: 2023-06-24 - 21:41
// * @Version: v1.0
// */
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")
//                .allowedHeaders(CorsConfiguration.ALL)
//                .allowedMethods(CorsConfiguration.ALL)
//                .allowCredentials(true)
//                .maxAge(3600);//一小时之内不要做检验(发Options请求),前端会在发正式请求之前先发一个options请求预先检查功能
//    }
//}
