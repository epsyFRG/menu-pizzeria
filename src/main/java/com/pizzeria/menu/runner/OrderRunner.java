package com.pizzeria.menu.runner;

import com.pizzeria.menu.config.AppConfig;
import com.pizzeria.menu.enums.PizzaSize;
import com.pizzeria.menu.model.Drink;
import com.pizzeria.menu.model.Pizza;
import com.pizzeria.menu.model.Topping;
import com.pizzeria.menu.repository.DrinkRepository;
import com.pizzeria.menu.repository.PizzaRepository;
import com.pizzeria.menu.repository.ToppingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class OrderRunner implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private ToppingRepository toppingRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("=".repeat(60));
        log.info("avvio sistema gestione ordini - {}", appConfig.getNome());
        log.info("=".repeat(60));

        salvaMenuNelDatabase();

        testQuery();
    }

    private void salvaMenuNelDatabase() {
        log.info("\nsalvataggio toppings nel database...");
        Topping prosciutto = context.getBean("prosciutto", Topping.class);
        Topping ananas = context.getBean("ananas", Topping.class);
        Topping salame = context.getBean("salame", Topping.class);
        Topping funghi = context.getBean("funghi", Topping.class);
        Topping olive = context.getBean("olive", Topping.class);
        Topping cipolla = context.getBean("cipolla", Topping.class);

        toppingRepository.save(prosciutto);
        toppingRepository.save(ananas);
        toppingRepository.save(salame);
        toppingRepository.save(funghi);
        toppingRepository.save(olive);
        toppingRepository.save(cipolla);

        log.info("toppings salvati: {}", toppingRepository.count());

        log.info("\nsalvataggio pizze nel database...");
        Pizza margherita = context.getBean("margherita", Pizza.class);
        Pizza margheritaXL = context.getBean("margheritaXL", Pizza.class);
        Pizza hawaiian = context.getBean("hawaiian", Pizza.class);
        Pizza hawaiianXL = context.getBean("hawaiianXL", Pizza.class);
        Pizza salamePizza = context.getBean("salamePizza", Pizza.class);
        Pizza capricciosa = context.getBean("capricciosa", Pizza.class);

        pizzaRepository.save(margherita);
        pizzaRepository.save(margheritaXL);
        pizzaRepository.save(hawaiian);
        pizzaRepository.save(hawaiianXL);
        pizzaRepository.save(salamePizza);
        pizzaRepository.save(capricciosa);

        log.info("pizze salvate: {}", pizzaRepository.count());

        log.info("\nsalvataggio bevande nel database...");
        Drink cocaCola = context.getBean("cocaCola", Drink.class);
        Drink acqua = context.getBean("acqua", Drink.class);
        Drink birra = context.getBean("birra", Drink.class);
        Drink sprite = context.getBean("sprite", Drink.class);

        drinkRepository.save(cocaCola);
        drinkRepository.save(acqua);
        drinkRepository.save(birra);
        drinkRepository.save(sprite);

        log.info("bevande salvate: {}", drinkRepository.count());
    }

    private void testQuery() {
        log.info("\n--- TEST QUERY ---");

        log.info("\npizze xl:");
        List<Pizza> pizzeXL = pizzaRepository.findBySize(PizzaSize.XL);
        pizzeXL.forEach(p -> log.info("- {}", p.getName()));

        log.info("\npizze sotto 8 euro:");
        List<Pizza> pizzeEconomiche = pizzaRepository.findPizzeCheNonCostanoTroppo(8.0);
        pizzeEconomiche.forEach(p -> log.info("- {} (€{})", p.getName(), p.getPrice()));

        log.info("\ntoppings economici (sotto 1 euro):");
        List<Topping> toppingsEconomici = toppingRepository.findByPriceLessThan(1.0);
        toppingsEconomici.forEach(t -> log.info("- {} (€{})", t.getName(), t.getPrice()));

        log.info("\nbevande da 330ml:");
        List<Drink> drinks330 = drinkRepository.findByVolume(330);
        drinks330.forEach(d -> log.info("- {}", d.getName()));
    }
}