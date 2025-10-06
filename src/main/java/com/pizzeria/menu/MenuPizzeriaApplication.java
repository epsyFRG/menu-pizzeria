package com.pizzeria.menu;

import com.pizzeria.menu.model.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MenuPizzeriaApplication {

    public static void main(String[] args) {
        // avvia il contesto Spring
        ApplicationContext context = SpringApplication.run(MenuPizzeriaApplication.class, args);

        // recupera il bean Menu dal contesto
        Menu menu = context.getBean(Menu.class);

        // stampa il menu
        menu.print();
    }
}