package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findAll();
    Dish findByDishId(String dishId);
    Dish save(Dish dish);
    void delete(Dish dish);
    List<Dish> findAllByChef_Id(Long chefId);
}