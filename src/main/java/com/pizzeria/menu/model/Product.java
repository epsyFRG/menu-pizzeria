package com.pizzeria.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Product {
    private String name;
    private double price;
    private int calories;

    @Override
    public String toString() {
        return String.format("%s - €%.2f (Calorie: %d kcal)",
                name, price, calories);
    }
}