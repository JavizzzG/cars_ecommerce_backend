package com.javiz.cars_ecommerce_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") //Esto es para que aplique a todas las rutas de la API
                .allowedOrigins("https://cars-ecommerce-frontend-git-develop-javizzzs-projects.vercel.app/", "http://localhost:4200/") // Para elegir los dominios que pueden consumir
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Son los métodos permitidos
                .allowedHeaders("*") // permite cualquier header
                .allowCredentials(true);
    }

}
