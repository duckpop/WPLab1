package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init(){
        dishes = new ArrayList<>();

        chefs =  new ArrayList<>();
        chefs.add(new Chef(1L,"John","Cook","Likes to clean",new ArrayList<>()));
        chefs.add(new Chef(2L,"Jane","Doe","likes to sing, a lot",new ArrayList<>()));
        chefs.add(new Chef(3L,"Amy","Santiago","Used to be a cop, but finds cooking to be more calculated",new ArrayList<>()));
        chefs.add(new Chef(4L,"Jake","Paralta","Just here to see Amy",new ArrayList<>()));
        chefs.add(new Chef(5L,"Kate","Middleton","Nobody knows who hired her, but she keeps coming to work",new ArrayList<>()));



    }
}
