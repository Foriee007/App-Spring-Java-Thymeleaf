package com.bonappetit.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String ingredients;

    @ManyToOne(fetch = FetchType.EAGER)//(optional = false)Bankin
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)//(optional = false)Bankin
    private User addedBy;

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public Recipe setName(String name) {
        this.name = name;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Recipe setCategory(Category category) {
        this.category = category;
        return this;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public Recipe setAddedBy(User addedBy) {
        this.addedBy = addedBy;
        return this;
    }
}
