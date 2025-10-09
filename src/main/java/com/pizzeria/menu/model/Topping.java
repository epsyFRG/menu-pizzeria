package com.pizzeria.menu.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Topping extends Product {

    public Topping(String name, double price, int calories) {
        super(name, price, calories);
    }

    @Override
    public String toString() {
        return String.format("%s (+€%.2f, +%d kcal)",
                getName(), getPrice(), getCalories());
    }
}