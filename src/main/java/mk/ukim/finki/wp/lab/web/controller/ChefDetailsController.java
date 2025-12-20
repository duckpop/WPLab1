package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/chefDetails")
public class ChefDetailsController {

    private final ChefService chefService;
    private final DishService dishService;

    public ChefDetailsController(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @PostMapping
    public String addDishToChef(
            @RequestParam Long chefId,
            @RequestParam String dishId,
            Model model) {

        if (chefId == null || dishId == null || dishId.isEmpty()) {
            return "redirect:/error";
        }

        // Add dish to chef (service handles transaction)
        chefService.addDishToChef(chefId, dishId);

        // Re-load chef with dishes
        Chef chef = chefService.findById(chefId);
        if (chef == null) {
            return "redirect:/error";
        }

        List chefDishes = List.copyOf(chef.getDishes());

        model.addAttribute("selectedChef", chef);
        model.addAttribute("dishes", chefDishes);
        model.addAttribute("bodyContent", "chefDetails");

        return "master-template";
    }
}
