package com.bonappetit.model.dto;

import com.bonappetit.model.entity.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddRecipeDTO {

    @NotNull
    @Size(min = 2, max = 40, message = "The recipe's name length must be between 2 and 40 characters!")
    private String name;

    @NotNull
    @Size(min = 2, max = 150, message = "The ingredients length must be between 2 and 150 characters!")
    private String ingredients;

    @NotNull(message = "You must select a category!")
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

    public String getIngredients() {
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
