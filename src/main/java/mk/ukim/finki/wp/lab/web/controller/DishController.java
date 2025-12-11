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
            @RequestParam String name,
            @RequestParam String cuisine,
            @RequestParam int preparationTime,
            @RequestParam Rank rank,
            @RequestParam double rating,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            Model model
    ) {
        if(error != null) {
            model.addAttribute("error", error);
        }

        List<Dish> Dishes = dishService.find(name,cuisine,preparationTime,rank,rating,pageNum-1,pageSize);
        model.addAttribute("dishes", Dishes);
        model.addAttribute("ranks", Rank.values());
        return "listDishes";
    }

    @GetMapping("/dish-form/{ID}")
    public String getEditDishForm(
            @PathVariable Long ID,
            Model model
    ){
        Dish d = dishService.findById(ID);
        System.out.println(d.toString());
        model.addAttribute("dish",dishService.findById(ID));
        model.addAttribute("ranks", Rank.values());
        return "dish-form";
    }

    @GetMapping("/listChefs")
    public String getChefsPage(){
        return "listChefs";
    }

    @GetMapping("/dish-form")
    public String getEditDishForm(
            Model model
    ){
        model.addAttribute("dish",new Dish());
        return "dish-form";
    }

    @PostMapping()
    public String saveDish(
            @RequestParam String dishId,
            @RequestParam String name,
            @RequestParam String cuisine,
            @RequestParam int preparationTime,
            @RequestParam Rank rank,
            @RequestParam double rating
    ) {
        dishService.create(dishId, name, cuisine, preparationTime,rank,rating);
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
            @RequestParam double rating
    ){
        System.out.println(ID + " " + dishId + " " + name + " " + cuisine + " " + preparationTime);
        dishService.update(ID, dishId, name, cuisine, preparationTime,rank,rating);
        return "redirect:/dishes";
    }

    @PostMapping("/delete/{ID}")
    public String deleteDish(
            @PathVariable Long ID
    ){
        dishService.delete(ID);
        return "redirect:/dishes";
    }

}
