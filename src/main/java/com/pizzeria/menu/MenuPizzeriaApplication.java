package com.pizzeria.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MenuPizzeriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MenuPizzeriaApplication.class, args);
        // Il CommandLineRunner si avvierà automaticamente!
    }
}