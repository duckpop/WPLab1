package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.enums.Rank;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();

    Dish findByDishId(String dishId);
    Dish findById(Long Id);
    List<Dish> find(String name, String cuisine, int preparationTime, Rank rank, double rating, int pageNum, int pageSize);
    Dish create(String dishId, String name, String cuisine, int preparationTime, Rank rank, double rating);
    Dish update(Long id,String dishId, String name, String cuisine, int preparationTime, Rank rank, double rating);
    void delete(Long id);
}