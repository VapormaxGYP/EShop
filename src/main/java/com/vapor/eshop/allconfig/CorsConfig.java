package com.vapor.eshop.allconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cors Config
 */

@Configuration

public class CorsConfig implements WebMvcConfigurer {
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addExposedHeader(CorsConfiguration.ALL);
        config.addAllowedOriginPattern(CorsConfiguration.ALL);
        //config.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
