package com.pizzeria.menu;

import com.pizzeria.menu.config.AppConfig;
import com.pizzeria.menu.enums.OrderState;
import com.pizzeria.menu.enums.PizzaSize;
import com.pizzeria.menu.enums.TableState;
import com.pizzeria.menu.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class PizzeriaTests {

    private Menu menu;
    private AppConfig appConfig;
    private Table table;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        appConfig = new AppConfig();
        AppConfig.Costo costo = new AppConfig.Costo();
        costo.setCoperto(2.50);
        appConfig.setCosto(costo);
        appConfig.setNome("Test Pizzeria");
        table = new Table(1, 4, TableState.LIBERO);
    }

    @Test
    void testPizzaBase() {
        Pizza margherita = new Pizza("Margherita", 5.00, 700, PizzaSize.NORMAL);

        assertNotNull(margherita);
        assertEquals("Margherita", margherita.getName());
        assertEquals(5.00, margherita.getPrice(), 0.01);
        assertEquals(700, margherita.getCalories());
        assertEquals(PizzaSize.NORMAL, margherita.getSize());
        assertTrue(margherita.getToppings().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "NORMAL, 5.00, 700",
            "XL, 7.50, 910"
    })
    void testSizeMultiplier(PizzaSize size, double expectedPrice, int expectedCalories) {
        Pizza pizza = new Pizza("Test", 5.00, 700, size);

        assertEquals(expectedPrice, pizza.getPrice(), 0.01);
        assertEquals(expectedCalories, pizza.getCalories());
    }

    @Test
    void testAggiungiToppings() {
        Pizza pizza = new Pizza("Capricciosa", 5.00, 700, PizzaSize.NORMAL);
        Topping prosciutto = new Topping("Prosciutto", 1.50, 80);
        Topping funghi = new Topping("Funghi", 1.20, 30);

        double prezzoBase = pizza.getPrice();
        int calorieBase = pizza.getCalories();

        pizza.addTopping(prosciutto);
        pizza.addTopping(funghi);

        assertEquals(2, pizza.getToppings().size());
        assertEquals(prezzoBase + 2.70, pizza.getPrice(), 0.01);
        assertEquals(calorieBase + 110, pizza.getCalories());
    }

    @Test
    void testCalcoloOrdine() {
        Order ordine = new Order(table, 4, appConfig.getCosto().getCoperto());

        Pizza margherita = new Pizza("Margherita", 5.00, 700, PizzaSize.NORMAL);
        Pizza hawaiian = new Pizza("Hawaiian", 5.00, 700, PizzaSize.XL);
        hawaiian.addTopping(new Topping("Prosciutto", 1.50, 80));

        Drink cocaCola = new Drink("Coca-Cola", 2.50, 140, 330);

        ordine.addItem(margherita);
        ordine.addItem(hawaiian);
        ordine.addItem(cocaCola);

        assertEquals(3, ordine.getItems().size());
        assertEquals(OrderState.IN_CORSO, ordine.getState());

        double totale = 5.00 + (7.50 + 1.50) + 2.50 + (4 * 2.50);
        assertEquals(totale, ordine.getTotalAmount(), 0.01);
    }

    @ParameterizedTest
    @EnumSource(OrderState.class)
    void testStatoOrdine(OrderState stato) {
        Table tavolo = new Table(5, 6, TableState.LIBERO);
        Order ordine = new Order(tavolo, 3, 2.50);

        Pizza pizza = new Pizza("Diavola", 5.00, 700, PizzaSize.NORMAL);
        ordine.addItem(pizza);

        tavolo.setState(TableState.OCCUPATO);
        ordine.setState(stato);

        assertEquals(stato, ordine.getState());
        assertEquals(1, ordine.getItems().size());
        assertTrue(ordine.getOrderNumber() > 0);

        if (stato == OrderState.SERVITO) {
            tavolo.setState(TableState.LIBERO);
            assertEquals(TableState.LIBERO, tavolo.getState());
        }
    }
}