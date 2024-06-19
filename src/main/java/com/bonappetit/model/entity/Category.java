package com.bonappetit.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Recipe> recipes;

    //public Language() {this.words = new HashSet<>();}
    public Category(){
        this.recipes = new HashSet<>();
    }

    public Category(CategoryEnum name, String description) {
        super();
        this.name = name;
        this.description = description;
    }


    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
