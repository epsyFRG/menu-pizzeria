package com.pizzeria.menu.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Menu {
    private List<Pizza> pizzas;
    private List<Drink> drinks;
    private List<Topping> toppings;

    public Menu() {
        this.pizzas = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.toppings = new ArrayList<>();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void print() {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("                   MENU PIZZERIA                             ");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        System.out.println("═══════════════ PIZZE ═══════════════");
        for (Pizza pizza : pizzas) {
            System.out.println("• " + pizza);
        }

        System.out.println("\n═══════════════ TOPPINGS EXTRA ═══════════════");
        for (Topping topping : toppings) {
            System.out.println("• " + topping);
        }

        System.out.println("\n═══════════════ BEVANDE ═══════════════");
        for (Drink drink : drinks) {
            System.out.println("• " + drink);
        }

        System.out.println("\n══════════════════════════════════════════════════════════");
    }
}