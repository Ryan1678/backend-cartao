package br.com.itb.miniprojetospring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Habilita CORS para todas as rotas
                .allowedOrigins("http://localhost:5173") // Permitir requisições do frontend (React)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permitir esses métodos
                .allowedHeaders("*") // Permitir todos os cabeçalhos
                .allowCredentials(true); // Permitir envio de cookies ou credenciais
    }
}
