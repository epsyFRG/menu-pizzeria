package com.pizzeria.menu.repository;

import com.pizzeria.menu.model.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {

    List<Topping> findByPriceLessThan(double price);

    List<Topping> findByCaloriesLessThanOrderByPriceAsc(int calories);
}