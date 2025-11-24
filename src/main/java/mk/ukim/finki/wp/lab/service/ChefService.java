package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;

import java.util.List;

public interface ChefService{
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);
    Chef create(String firstName,String lastname,String bio);
    Chef update(Long id, String firstname, String lastname,String bio);
    void delete(Long id);
}
