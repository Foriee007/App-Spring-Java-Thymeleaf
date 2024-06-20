package com.bonappetit.repo;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryEnum;
import com.bonappetit.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Set<Recipe> findAllByCategory(Category category);
    List<Recipe> findAllByCategory_Name(CategoryEnum categoryEnum);
    //List<Recipe> findAllByAddedBy_FavouriteRecipes(Set<Recipe> addedBy_favouriteRecipes);
}
