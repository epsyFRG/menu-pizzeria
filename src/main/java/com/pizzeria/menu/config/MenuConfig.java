package com.pizzeria.menu.config;

import com.pizzeria.menu.enums.PizzaSize;
import com.pizzeria.menu.model.Drink;
import com.pizzeria.menu.model.Menu;
import com.pizzeria.menu.model.Pizza;
import com.pizzeria.menu.model.Topping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenuConfig {

    // toppings

    @Bean
    public Topping prosciutto() {
        return new Topping("Prosciutto", 1.50, 80);
    }

    @Bean
    public Topping ananas() {
        return new Topping("Ananas", 1.00, 50);
    }

    @Bean
    public Topping salame() {
        return new Topping("Salame", 1.50, 100);
    }

    @Bean
    public Topping funghi() {
        return new Topping("Funghi", 1.20, 30);
    }

    @Bean
    public Topping olive() {
        return new Topping("Olive", 0.80, 40);
    }

    @Bean
    public Topping cipolla() {
        return new Topping("Cipolla", 0.70, 25);
    }

    // pizze

    @Bean
    public Pizza margherita() {
        return new Pizza("Margherita", 5.00, 700, PizzaSize.NORMAL);
    }

    @Bean
    public Pizza margheritaXL() {
        return new Pizza("Margherita XL", 5.00, 700, PizzaSize.XL);
    }

    @Bean
    public Pizza hawaiian() {
        Pizza pizza = new Pizza("Hawaiian Pizza", 5.00, 700, PizzaSize.NORMAL);
        pizza.addTopping(prosciutto());
        pizza.addTopping(ananas());
        return pizza;
    }

    @Bean
    public Pizza hawaiianXL() {
        Pizza pizza = new Pizza("Hawaiian Pizza XL", 5.00, 700, PizzaSize.XL);
        pizza.addTopping(prosciutto());
        pizza.addTopping(ananas());
        return pizza;
    }

    @Bean
    public Pizza salamePizza() {
        Pizza pizza = new Pizza("Diavola", 5.00, 700, PizzaSize.NORMAL);
        pizza.addTopping(salame());
        pizza.addTopping(salame()); // Doppio salame!
        return pizza;
    }

    @Bean
    public Pizza capricciosa() {
        Pizza pizza = new Pizza("Capricciosa", 5.00, 700, PizzaSize.NORMAL);
        pizza.addTopping(prosciutto());
        pizza.addTopping(funghi());
        pizza.addTopping(olive());
        return pizza;
    }

    // bevande

    @Bean
    public Drink cocaCola() {
        return new Drink("Coca-Cola", 2.50, 140, 330);
    }

    @Bean
    public Drink acqua() {
        return new Drink("Acqua Naturale", 1.50, 0, 500);
    }

    @Bean
    public Drink birra() {
        return new Drink("Birra Peroni", 3.50, 200, 330);
    }

    @Bean
    public Drink sprite() {
        return new Drink("Sprite", 2.50, 120, 330);
    }

    // menu

    @Bean
    public Menu menu() {
        Menu menu = new Menu();

        // aggiungi pizze
        menu.addPizza(margherita());
        menu.addPizza(margheritaXL());
        menu.addPizza(hawaiian());
        menu.addPizza(hawaiianXL());
        menu.addPizza(salamePizza());
        menu.addPizza(capricciosa());

        // aggiungi toppings
        menu.addTopping(prosciutto());
        menu.addTopping(ananas());
        menu.addTopping(salame());
        menu.addTopping(funghi());
        menu.addTopping(olive());
        menu.addTopping(cipolla());

        // aggiungi bevande
        menu.addDrink(cocaCola());
        menu.addDrink(acqua());
        menu.addDrink(birra());
        menu.addDrink(sprite());

        return menu;
    }
}