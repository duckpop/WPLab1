package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.enums.Rank;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dish")
public class DishFilterController {

    private final DishService dishService;
    private final ChefService chefService;

    public DishFilterController(DishService dishService, ChefService chefService) {
        this.dishService = dishService;
        this.chefService = chefService;
    }

    @PostMapping
    public String filterDishesByChef(
            @RequestParam Long chefId,
            Model model) {

        Chef selectedChef = chefService.findById(chefId);
        List dishes = dishService.listDishes();

        model.addAttribute("selectedChef", selectedChef);
        model.addAttribute("dishes", dishes);
        model.addAttribute("ranks", Rank.values());
        model.addAttribute("chefId", chefId);
        model.addAttribute("bodyContent", "dishesList");

        return "master-template";
    }

    @GetMapping
    public String getDishPage(
            @RequestParam(required = false) Long chefId,
            @RequestParam(required = false) String SearchName,           // ✅ ДОДАДЕНО
            @RequestParam(required = false) Integer preparationTime,    // ✅ ДОДАДЕНО
            @RequestParam(required = false) Rank rank,                  // ✅ ДОДАДЕНО
            @RequestParam(required = false) Double rating,              // ✅ ДОДАДЕНО
            Model model) {

        // GET /dish прикажува dishes (со chef ако е даден ID)
        if(chefId != null) {
            Chef selectedChef = chefService.findById(chefId);
            model.addAttribute("selectedChef", selectedChef);
        }

        // ✅ Користи filter методот од dishService
        List dishes = dishService.find(SearchName, preparationTime, rank, rating);

        model.addAttribute("dishes", dishes);
        model.addAttribute("ranks", Rank.values());
        model.addAttribute("nameOrCuisine", SearchName);              // ✅ ДОДАДЕНО
        model.addAttribute("preparationTime", preparationTime);        // ✅ ДОДАДЕНО
        model.addAttribute("rank", rank);                              // ✅ ДОДАДЕНО
        model.addAttribute("rating", rating);                          // ✅ ДОДАДЕНО
        model.addAttribute("bodyContent", "dishesList");

        return "master-template";
    }
}
