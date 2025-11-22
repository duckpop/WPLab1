package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private static Long id;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
        id = 1L;
    }

    @Override
    public List<Dish> listDishes() {
        if(dishRepository.findAll() != null) {
            return dishRepository.findAll();
        }
        return new ArrayList<>();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return this.dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long ID) {
        return this.dishRepository.findByID(ID).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        Dish d = new Dish(dishId,name,cuisine,preparationTime);
        d.setID(id++);
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
