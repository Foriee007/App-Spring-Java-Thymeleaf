package com.bonappetit.service.impl;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.CategoryService;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final LoggedUser loggedUser;

    public RecipeServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, CategoryService categoryService, RecipeRepository recipeRepository, UserService userService, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.loggedUser = loggedUser;
    }


    @Override
    public boolean collectRecipe(AddRecipeDTO addRecipeDTO) {
        if (!loggedUser.isLogged()) {
            return false;
        }
        Optional<User> userById = userRepository.findById(loggedUser.getId());
        if (userById.isEmpty()) {
            return false;
        }
        Optional<Category> byName = categoryRepository.findByName(addRecipeDTO.getCategory());
        if (byName.isEmpty()) {
            return false;
        }


                Recipe recipe = new Recipe();
                recipe.setName(addRecipeDTO.getName());
                recipe.setIngredients(addRecipeDTO.getIngredients());
                recipe.setCategory(byName.get());
                recipe.setAddedBy(userById.get());

                this.recipeRepository.save(recipe);
        return true;
    }

    public List<Recipe> mainDish() {
        return this.recipeRepository.findAllByCategory_Name(CategoryEnum.MAIN_DISH );
    }
    public List<Recipe> desert() {
        return this.recipeRepository.findAllByCategory_Name(CategoryEnum.DESSERT );
    }
    public List<Recipe> cocktail() {
        return this.recipeRepository.findAllByCategory_Name(CategoryEnum.COCKTAIL);
    }

}
