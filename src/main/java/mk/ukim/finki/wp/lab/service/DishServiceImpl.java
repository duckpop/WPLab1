package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.enums.Rank;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static mk.ukim.finki.wp.lab.service.FieldFilterSpecification.*;

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
    public List<Dish> find( String name, String cuisine, int preparationTime, Rank rank, double rating, int pageNum, int pageSize){
        Specification<Dish> specification = Specification.allOf(
                filterContainsText(Dish.class, "name",name)
        ).or(Specification.allOf(
                filterContainsText(Dish.class, "cuisine", cuisine)
        )).and(Specification.not(
                greaterThan(Dish.class, "preparationTime", preparationTime)
        )).and(Specification.allOf(
                filterEqualsV(Dish.class, "rank", rank),
                greaterThan(Dish.class, "rating", rating)
        ));

        return this.dishRepository.findAll(
                specification, PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "name")));

    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Rank rank, double rating) {
        Dish d = new Dish(dishId,name,cuisine,preparationTime,rank,rating);
        this.dishRepository.save(d);
        return d;
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Rank rank, double rating) {
        Dish d = this.findById(id);
        d.setDishId(dishId);
        d.setName(name);
        d.setCuisine(cuisine);
        d.setPreparationTime(preparationTime);
        d.setRank(rank);
        d.setRating(rating);
        this.dishRepository.save(d);
        return d;
    }

    @Override
    public void delete(Long id) {
        Dish d = this.findById(id);
        this.dishRepository.delete(d);
    }
}
