package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    private ChefService chefService;

    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping()
    public String getChefPage(
            @RequestParam(required = false) String error,
            Model model
    ) {
        if(error != null) {
            model.addAttribute("error", error);
        }

        List<Chef> Chefs = chefService.listChefs();
        model.addAttribute("chefs", Chefs);
        return "listChefs";
    }

    @GetMapping("/chef-form/{ID}")
    public String getEditChefForm(
            @PathVariable Long ID,
            Model model
    ){
        Chef d = chefService.findById(ID);
        model.addAttribute("chef",d);
        return "chef-form";
    }

    @GetMapping("/chef-form")
    public String getCreateChefForm(
            Model model
    ){
        model.addAttribute("chef",new Chef());
        return "chef-form";
    }

    @PostMapping()
    public String saveChef(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String bio
    ) {
        chefService.create(firstname, lastname, bio);
        return "redirect:/chefs";
    }

    @PostMapping("/edit/{id}")
    public String editChef(
            @PathVariable Long id,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String bio
    ){
        chefService.update(id, firstname, lastname, bio);
        return "redirect:/chefs";
    }

    @PostMapping("/delete/{id}")
    public String deleteChef(
            @PathVariable Long id
    ){
        chefService.delete(id);
        return "redirect:/chefs";
    }


}
