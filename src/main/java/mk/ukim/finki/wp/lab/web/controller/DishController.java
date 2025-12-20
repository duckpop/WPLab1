package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.enums.Rank;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishController {

    private DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping()
    public String getDishesPage(
            @RequestParam(required = false) String error,
            @RequestParam(required = false) String SearchName,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) Integer preparationTime,
            @RequestParam(required = false) Rank rank,
            @RequestParam(required = false) Double rating,
            Model model) {

        if(error != null) {
            model.addAttribute("error", error);
        }

        List<Dish> dishes = dishService.find(SearchName, preparationTime, rank, rating);
        model.addAttribute("dishes", dishes);
        model.addAttribute("ranks", Rank.values());
        model.addAttribute("nameOrCuisine", SearchName);
        model.addAttribute("preparationTime", preparationTime);
        model.addAttribute("rank", rank);
        model.addAttribute("rating", rating);
        model.addAttribute("bodyContent", "listDishes");  // ✅ ДОДАДЕНО
        return "master-template";  // ✅ ПРОМЕНЕНО
    }

    @GetMapping("/dish-form/{ID}")
    public String getEditDishForm(
            @PathVariable Long ID,
            Model model) {

        Dish dish = dishService.findById(ID);
        model.addAttribute("dish", dish);
        model.addAttribute("ranks", Rank.values());
        model.addAttribute("bodyContent", "dish-form");  // ✅ ДОДАДЕНО
        return "master-template";  // ✅ ПРОМЕНЕНО
    }

    @GetMapping("/dish-form")
    public String getCreateDishForm(Model model) {

        model.addAttribute("dish", new Dish());
        model.addAttribute("ranks", Rank.values());
        model.addAttribute("bodyContent", "dish-form");  // ✅ ДОДАДЕНО
        return "master-template";  // ✅ ПРОМЕНЕНО
    }

    @GetMapping("/listChefs")
    public String getChefsPage(Model model) {

        model.addAttribute("bodyContent", "listChefs");  // ✅ ДОДАДЕНО
        return "master-template";  // ✅ ПРОМЕНЕНО
    }

    @PostMapping()
    public String saveDish(
            @RequestParam String dishId,
            @RequestParam String name,
            @RequestParam String cuisine,
            @RequestParam int preparationTime,
            @RequestParam Rank rank,
            @RequestParam double rating) {

        dishService.create(dishId, name, cuisine, preparationTime, rank, rating);
        return "redirect:/dishes";
    }

    @PostMapping("/edit/{ID}")
    public String editDish(
            @PathVariable Long ID,
            @RequestParam String dishId,
            @RequestParam String name,
            @RequestParam String cuisine,
            @RequestParam int preparationTime,
            @RequestParam Rank rank,
            @RequestParam double rating) {

        dishService.update(ID, dishId, name, cuisine, preparationTime, rank, rating);
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{ID}")
    public String deleteDish(@PathVariable Long ID) {

        dishService.delete(ID);
        return "redirect:/dishes";
    }
}
