package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> listDishes() {
        dishRepository.findAll();
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return this.dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElse(null);  // use inherited findById
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        Dish d = new Dish(dishId,name,cuisine,preparationTime);
        this.dishRepository.save(d);
        return d;
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish d = this.findById(id);
        d.setDishId(dishId);
        d.setName(name);
        d.setCuisine(cuisine);
        d.setPreparationTime(preparationTime);
        this.dishRepository.save(d);
        return d;
    }

    @Override
    public void delete(Long id) {
        Dish d = this.findById(id);
        this.dishRepository.delete(d);
    }
}
