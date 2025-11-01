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
        dishes.add(new Dish("Soup","Rube Goldberg Soup","it's Soup", 20));
        dishes.add(new Dish("Stake", "24K Gold Stake","Grill",200));
        dishes.add(new Dish("Cake","Ultra Rare","Guess",50));
        dishes.add(new Dish("Turkey","Once in a Century Turkey","not the country",80));
        dishes.add(new Dish("Fries","Fried Potatoes","Fired",15));
        dishes.add(new Dish("Pizza", "Volcano Lava Pizza", "Hot and spicy", 60));
        dishes.add(new Dish("Pasta", "Carbonara Supreme", "Classic Italian", 45));
        dishes.add(new Dish("Burger", "Triple Decker Burger", "Stacked high", 70));
        dishes.add(new Dish("Salad", "Emerald Garden Mix", "Fresh delight", 25));
        dishes.add(new Dish("Ice Cream", "Galaxy Swirl", "Sweet chill", 30));
        dishes.add(new Dish("Stew", "Dragonfire Stew", "Very hot", 90));
        dishes.add(new Dish("Fish", "Golden Reef Salmon", "Deep sea magic", 120));
        dishes.add(new Dish("Bread", "Heavenly Loaf", "Soft and warm", 20));
        dishes.add(new Dish("Pancake", "Midnight Maple Stack", "Sweet dreams", 35));
        dishes.add(new Dish("Noodles", "Ancient Temple Ramen", "Mystic flavor", 50));
        dishes.add(new Dish("Coffee", "Stardust Espresso", "Wake up call", 15));
        dishes.add(new Dish("Smoothie", "Nebula Blend", "Fruit fusion", 25));
        dishes.add(new Dish("Sushi", "Samurai Special", "Precision cut", 80));
        dishes.add(new Dish("Curry", "Firestorm Curry", "Spice overload", 55));
        dishes.add(new Dish("Taco", "Cosmic Crunch", "Out of this world", 40));

        chefs =  new ArrayList<>();
        chefs.add(new Chef(1L,"John","Cook","Likes to clean",new ArrayList<>()));
        chefs.add(new Chef(2L,"Jane","Doe","likes to sing, a lot",dishes));
        chefs.add(new Chef(3L,"Amy","Santiago","Used to be a cop, but finds cooking to be more calculated",dishes));
        chefs.add(new Chef(4L,"Jake","Paralta","Just here to see Amy",dishes));
        chefs.add(new Chef(5L,"Kate","Middleton","Nobody knows who hired her, but she keeps coming to work",dishes));



    }
}
