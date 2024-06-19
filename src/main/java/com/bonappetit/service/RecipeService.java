package com.bonappetit.service;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.Recipe;

import java.util.List;

public interface RecipeService {
    boolean collectRecipe(AddRecipeDTO addRecipeDTO);
    List<Recipe> mainDish();
    List<Recipe> desert();
    List<Recipe> cocktail();
}
