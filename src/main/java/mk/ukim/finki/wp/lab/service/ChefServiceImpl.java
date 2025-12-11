package mk.ukim.finki.wp.lab.service;

import org.springframework.transaction.annotation.Transactional;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.model.Chef;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ChefServiceImpl implements ChefService {
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Chef> listChefs() {
        return this.chefRepository.findAll();
    }

    @Override
    @Transactional
    public Chef findById(Long id) {
        Chef chef = chefRepository.findById(id).orElse(null);
        if (chef != null) {
            chef.getDishes().size(); // initialize lazy collection inside transaction
        }
        return chef;
    }

    @Override
    @Transactional
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = this.chefRepository.findById(chefId).orElse(null);
        Dish dish = this.dishRepository.findByDishId(dishId);
        if(chef!=null && dish!=null) {
            chef.getDishes().add(dish);
            chefRepository.save(chef);
            dish.setChef(chef);
            dishRepository.save(dish);
        }
        return chef;
    }

    @Override
    public Chef create(String firstName, String lastname, String bio) {
        Chef c = new Chef(firstName,lastname,bio);
        chefRepository.save(c);
        return c;
    }

    @Override
    public Chef update(Long id, String firstname, String lastname, String bio) {
        Chef c = chefRepository.findById(id).orElse(null);
        if(c != null){
            c.setFirstName(firstname);
            c.setLastName(lastname);
            c.setBio(bio);
            chefRepository.save(c);
        }
        return c;
    }

    @Override
    public void delete(Long id) {
        Chef c = chefRepository.findById(id).orElse(null);
        if(c != null){
            chefRepository.delete(c);
        }
    }
}
