package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.enums.Rank;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public DataHolder(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {

        if (chefRepository.count() == 0) {

            // Create chefs (WITHOUT manual IDs — JPA will assign them)
            Chef c1 = new Chef(null, "Gordon", "Ramsay",
                    "World-famous chef known for Michelin-star restaurants.",
                    new ArrayList<>());

            Chef c2 = new Chef(null, "Jamie", "Oliver",
                    "British chef focused on simple home-cooking.",
                    new ArrayList<>());

            Chef c3 = new Chef(null, "Heston", "Blumenthal",
                    "Experimental chef known for molecular gastronomy.",
                    new ArrayList<>());

            Chef c4 = new Chef(null, "Massimo", "Bottura",
                    "Italian chef famous for Osteria Francescana.",
                    new ArrayList<>());

            Chef c5 = new Chef(null, "Salt", "Bae",
                    "Internet-famous chef known for dramatic seasoning.",
                    new ArrayList<>());

            chefRepository.save(c1);
            chefRepository.save(c2);
            chefRepository.save(c3);
            chefRepository.save(c4);
            chefRepository.save(c5);
            // -------------------------
            // Create dishes and assign chefs
            // -------------------------
            if (dishRepository.count() == 0) {

                Dish d1 = new Dish("d1", "Pasta Carbonara", "Italian", 20, Rank.SILVER,2.2);
                d1.setChef(c1);

                Dish d2 = new Dish("d2", "Chicken Teriyaki", "Japanese", 25,Rank.BRONZE, 1.5);
                d2.setChef(c2);

                Dish d3 = new Dish("d3", "Tacos Al Pastor", "Mexican", 15,Rank.SILVER,2.0);
                d3.setChef(c3);

                Dish d4 = new Dish("d4", "Pad Thai", "Thai", 18,Rank.PLATINUM, 5.0);
                d4.setChef(c4);

                Dish d5 = new Dish("d5", "Greek Salad", "Greek", 10, Rank.GOLD, 3.5);
                d5.setChef(c5);

                dishRepository.save(d1);
                dishRepository.save(d2);
                dishRepository.save(d3);
                dishRepository.save(d4);
                dishRepository.save(d5);
            }
        }
    }
}