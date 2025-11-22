package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream().filter(d -> d.getDishId().equals(dishId)).findFirst().orElse(null);
    }

    @Override
    public Optional<Dish> findByID(Long ID) {
        return DataHolder.dishes.stream().filter(d -> d.getID().equals(ID)).findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        if(DataHolder.dishes.contains(dish)){
            DataHolder.dishes.remove(dish);
            DataHolder.dishes.add(dish);
        }else {
            DataHolder.dishes.add(dish);
        }
        return dish;
    }

    @Override
    public void delete(Dish dish) {
        DataHolder.dishes.remove(dish);
    }

}
