package com.pizzeria.menu.model;

import com.pizzeria.menu.enums.TableState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Table {
    private int number;
    private int maxSeats;
    private TableState state;

    @Override
    public String toString() {
        return String.format("Tavolo %d (max %d coperti) - %s",
                number, maxSeats, state);
    }
}