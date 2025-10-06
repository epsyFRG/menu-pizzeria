package com.pizzeria.menu.enums;

import lombok.Getter;

@Getter
public enum PizzaSize {
    NORMAL("Normale", 1.0, 1.0),
    XL("XL", 1.5, 1.3);

    private final String description;
    private final double priceMultiplier;
    private final double caloriesMultiplier;

    PizzaSize(String description, double priceMultiplier, double caloriesMultiplier) {
        this.description = description;
        this.priceMultiplier = priceMultiplier;
        this.caloriesMultiplier = caloriesMultiplier;
    }
}