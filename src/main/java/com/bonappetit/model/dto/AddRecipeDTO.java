package com.bonappetit.model.dto;

import com.bonappetit.model.entity.CategoryEnum;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddRecipeDTO {

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @NotNull
    @Size(min = 2, max = 150)
    private String ingredients;

    @NotNull
    private CategoryEnum category;

    public AddRecipeDTO() {
    }

    public String getName() {
        return name;
    }

    public AddRecipeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public  String getIngredients() {
        return ingredients;
    }

    public AddRecipeDTO setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddRecipeDTO setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
