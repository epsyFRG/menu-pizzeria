package com.pizzeria.menu.model;

import com.pizzeria.menu.enums.PizzaSize;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pizza extends Product {
    private List<Topping> toppings;
    private PizzaSize size;

    public Pizza(String name, double basePrice, int baseCalories, PizzaSize size) {
        super(name, basePrice * size.getPriceMultiplier(),
                (int) (baseCalories * size.getCaloriesMultiplier()));
        this.toppings = new ArrayList<>();
        this.size = size;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
        setPrice(getPrice() + topping.getPrice());
        setCalories(getCalories() + topping.getCalories());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s) - €%.2f (Calorie: %d kcal)",
                getName(), size.getDescription(), getPrice(), getCalories()));

        if (!toppings.isEmpty()) {
            sb.append("\n  Ingredienti extra: ");
            for (Topping t : toppings) {
                sb.append(t.getName()).append(", ");
            }
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}