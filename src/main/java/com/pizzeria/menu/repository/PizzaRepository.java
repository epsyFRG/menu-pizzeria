package com.pizzeria.menu.repository;

import com.pizzeria.menu.enums.PizzaSize;
import com.pizzeria.menu.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findBySize(PizzaSize size);

    List<Pizza> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Pizza p WHERE p.price < :maxPrice")
    List<Pizza> findPizzeCheNonCostanoTroppo(@Param("maxPrice") double maxPrice);

    @Query("SELECT p FROM Pizza p WHERE SIZE(p.toppings) >= :minToppings")
    List<Pizza> findPizzeConAlmenoNToppings(@Param("minToppings") int minToppings);
}