package com.bonappetit.model.dto;

public class RecipeInfoDTO {
    private long id;
    private String name;
    private String ingredients;

    public RecipeInfoDTO(long id, String name, String ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public RecipeInfoDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
