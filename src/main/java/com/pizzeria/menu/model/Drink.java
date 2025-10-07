package com.pizzeria.menu.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drink extends Product {
    private int volume;

    public Drink(String name, double price, int calories, int volume) {
        super(name, price, calories);
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format("%s (%dml) - €%.2f (Calorie: %d kcal)",
                getName(), volume, getPrice(), getCalories());
    }
}