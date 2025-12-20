package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/listChefs")
public class ChefListController {

    private final ChefService chefService;

    public ChefListController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping
    public String listChefs(
            @RequestParam(required = false) String errorMessage,
            Model model) {

        model.addAttribute("chefs", chefService.listChefs());
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("bodyContent", "listChefs");

        return "master-template";
    }

    @PostMapping
    public String addDishToChef(
            @RequestParam Long chefId,
            @RequestParam String dishId) {

        try {
            chefService.addDishToChef(chefId, dishId);
            return "redirect:/listChefs";
        } catch (IllegalArgumentException e) {
            return "redirect:/listChefs?errorMessage=Invalid input for category";
        }
    }
}
