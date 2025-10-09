package com.pizzeria.menu.repository;

import com.pizzeria.menu.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    List<Drink> findByVolume(int volume);

    List<Drink> findByPriceBetween(double minPrice, double maxPrice);
}