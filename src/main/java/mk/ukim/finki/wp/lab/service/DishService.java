package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.enums.Rank;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();

    Dish findByDishId(String dishId);
    Dish findById(Long Id);
    List<Dish> find(String SearchName, Integer preparationTime, Rank rank, Double rating);
    Dish create(String dishId, String name, String cuisine, Integer preparationTime, Rank rank, Double rating);
    Dish update(Long id,String dishId, String name, String cuisine, Integer preparationTime, Rank rank, Double rating);
    void delete(Long id);
}