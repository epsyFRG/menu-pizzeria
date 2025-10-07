package com.pizzeria.menu.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pizzeria")
@Getter
@Setter
public class AppConfig {
    private Costo costo;
    private String nome;

    @Getter
    @Setter
    public static class Costo {
        private double coperto;
    }
}