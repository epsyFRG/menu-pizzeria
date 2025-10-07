package com.pizzeria.menu.runner;

import com.pizzeria.menu.config.AppConfig;
import com.pizzeria.menu.enums.OrderState;
import com.pizzeria.menu.enums.PizzaSize;
import com.pizzeria.menu.enums.TableState;
import com.pizzeria.menu.model.Menu;
import com.pizzeria.menu.model.Order;
import com.pizzeria.menu.model.Pizza;
import com.pizzeria.menu.model.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRunner implements CommandLineRunner {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private Menu menu;

    @Override
    public void run(String... args) throws Exception {
        log.info("=".repeat(60));
        log.info("avvio sistema gestione ordini - {}", appConfig.getNome());
        log.info("costo coperto configurato: €{}", appConfig.getCosto().getCoperto());
        log.info("=".repeat(60));

        // 1. Stampa il menu
        log.info("\n menu disponibile:");
        menu.print();


        log.info("\n creazione tavoli");
        Table tavolo1 = new Table(1, 4, TableState.LIBERO);
        Table tavolo5 = new Table(5, 6, TableState.LIBERO);
        Table tavolo10 = new Table(10, 2, TableState.LIBERO);

        log.info("creato: {}", tavolo1);
        log.info("creato: {}", tavolo5);
        log.info("creato: {}", tavolo10);


        log.info("\n creazione ordine");
        tavolo5.setState(TableState.OCCUPATO);

        Order ordine1 = createSampleOrder(tavolo5, 4);

        log.info("ordine creato con successo");
        log.info(ordine1.toString());


        log.info("\n aggiornamento stato ordine");
        ordine1.setState(OrderState.PRONTO);
        log.info("ordine #{} aggiornato a: {}", ordine1.getOrderNumber(), ordine1.getState());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ordine1.setState(OrderState.SERVITO);
        log.info("ordine #{} aggiornato a: {}", ordine1.getOrderNumber(), ordine1.getState());
        tavolo5.setState(TableState.LIBERO);
        log.info("tavolo {} è ora: {}", tavolo5.getNumber(), tavolo5.getState());


        log.info("\n creazione secondo ordine ");
        tavolo1.setState(TableState.OCCUPATO);

        Order ordine2 = createComplexOrder(tavolo1, 3);
        log.info(ordine2.toString());

        log.info("\n test completato con successo");
    }

    private Order createSampleOrder(Table table, int covers) {
        Order order = new Order(table, covers, appConfig.getCosto().getCoperto());


        if (!menu.getPizzas().isEmpty()) {
            order.addItem(menu.getPizzas().get(0));
            if (menu.getPizzas().size() > 2) {
                order.addItem(menu.getPizzas().get(2));
            }
        }


        if (!menu.getDrinks().isEmpty()) {
            order.addItem(menu.getDrinks().get(0));
            order.addItem(menu.getDrinks().get(0));
        }

        return order;
    }

    private Order createComplexOrder(Table table, int covers) {
        Order order = new Order(table, covers, appConfig.getCosto().getCoperto());


        Pizza customPizza = new Pizza("margherita Personalizzata", 5.00, 700, PizzaSize.NORMAL);
        if (!menu.getToppings().isEmpty()) {
            customPizza.addTopping(menu.getToppings().get(0));
            if (menu.getToppings().size() > 1) {
                customPizza.addTopping(menu.getToppings().get(1));
            }
        }
        order.addItem(customPizza);


        if (menu.getPizzas().size() > 1) {
            order.addItem(menu.getPizzas().get(1));
        }


        if (menu.getDrinks().size() >= 2) {
            order.addItem(menu.getDrinks().get(0));
            order.addItem(menu.getDrinks().get(1));
            order.addItem(menu.getDrinks().get(0));
        }

        return order;
    }
}