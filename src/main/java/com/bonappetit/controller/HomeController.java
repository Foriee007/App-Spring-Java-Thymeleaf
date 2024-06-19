package com.bonappetit.controller;

//
import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.impl.UserServiceImpl;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(name = "/")
public class HomeController {
    private final LoggedUser loggedUser;
    private final UserServiceImpl userService;
    private final RecipeService recipeService;

    public HomeController(LoggedUser loggedUser, UserServiceImpl userService, RecipeService recipeService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index(){ // for Not logged user
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){ // if user is logged
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId()).orElse(null);
        model.addAttribute("currentUserInfo", user);

        List<Recipe> allMainDish = recipeService.mainDish();
        List<Recipe> allCocktail = recipeService.cocktail();
        List<Recipe> allDesert = recipeService.desert();
        List<RecipeInfoDTO> favourites =
                userService.findFavourites(loggedUser.getId())
                        .stream()
                        .map((RecipeInfoDTO id) -> new RecipeInfoDTO())
                        .toList();

        model.addAttribute("allMainDish", allMainDish);
        model.addAttribute("allDesert", allDesert);
        model.addAttribute("allCocktail", allCocktail);
        model.addAttribute("favouritesData", favourites);

//        long allCount = wordService.getAllCount();
//        model.addAttribute("allCount", allCount);

        return "home";
    }

    @PostMapping("/add-to-favourites/{recipeId}")
    public String addToFavourites(@PathVariable long recipeId) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        recipeService.addToFavourites(loggedUser.getId(), recipeId);

        return "redirect:/home";
    }
}
