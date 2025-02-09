package com.trustrace.survey_time;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String ipAddress = "127.0.0.1";
                try {
                    InetAddress ip = InetAddress.getLocalHost();
                    ipAddress = ip.getHostAddress();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                registry.addMapping("/**")  // Apply to all endpoints
                        .allowedOrigins("http://127.0.0.1:5500", "http://" + ipAddress + ":5500") // Allow your frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow common methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true); // Allow credentials if needed
            }
        };
    }
}
