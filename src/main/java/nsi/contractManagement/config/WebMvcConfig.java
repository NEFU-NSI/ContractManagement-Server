package nsi.contractManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Tao
 * @Time: 2020/10/10 10:47
 * @ProjectName: contract-management
 * @FileName: WebMvcConfig.java
 * @IDE: IntelliJ IDEA
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://219.217.199.65:8088", "*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
