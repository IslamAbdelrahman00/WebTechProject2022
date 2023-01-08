package de.htwberlin.webtech.webproject.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://to-do-app-frontend.herokuapp.com"
                );
//        registry.addMapping("*")
//                .allowedMethods("*")
//                .allowedHeaders("Access-Control-Allow-Origin")
//                .allowedOrigins(
//                        "http://localhost:3000",
//                        "https://to-do-app-frontend.herokuapp.com"
//                );
//        registry.addMapping("/*/*")
//                .allowedMethods("*")
//                .allowedHeaders("Access-Control-Allow-Origin")
//                .allowedOrigins(
//                        "http://localhost:3000",
//                        "https://to-do-app-frontend.herokuapp.com"
//                );
//        registry.addMapping("/*/*/*")
//                .allowedMethods("*")
//                .allowedHeaders("Access-Control-Allow-Origin")
//                .allowedOrigins(
//                        "http://localhost:3000",
//                        "https://to-do-app-frontend.herokuapp.com"
//                );
//        registry.addMapping("/*/*/*/*")
//                .allowedMethods("*")
//                .allowedHeaders("Access-Control-Allow-Origin")
//                .allowedOrigins(
//                        "http://localhost:3000",
//                        "https://to-do-app-frontend.herokuapp.com"
//                );

    }

}