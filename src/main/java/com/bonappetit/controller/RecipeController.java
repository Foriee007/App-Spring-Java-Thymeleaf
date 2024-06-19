package com.bonappetit.controller;

// {
import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final LoggedUser loggedUser;
    private final RecipeService recipeService;

    public RecipeController(LoggedUser loggedUser, RecipeService recipeService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;
    }

    @ModelAttribute("addRecipeData")
    public AddRecipeDTO addRecipeData(){
        return new AddRecipeDTO();
    }
    @GetMapping("/recipe-add")
    String addRecipe(){
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        return "recipe-add";
    }

    @PostMapping("/recipe-add")
    String addRecipe(@Valid AddRecipeDTO data,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes){
        if (!loggedUser.isLogged()) {
            return "redirect:/users/login";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addRecipeData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRecipeData", bindingResult);
            return "redirect:/recipes/recipe-add";
        }
        boolean success = recipeService.collectRecipe(data);

        if (!success) {
            // show generic error? duplicate name
            redirectAttributes.addFlashAttribute("addRecipeData", data);

            return "redirect:/recipes/recipe-add";
        }
        return "redirect:/home";
    }


}
