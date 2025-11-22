package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
    Optional<Dish> findByID(Long dishId);
    Dish save(Dish dish);
    void delete(Dish dish);
}