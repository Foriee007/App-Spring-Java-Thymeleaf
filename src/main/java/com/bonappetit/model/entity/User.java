package com.bonappetit.model.entity;



import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "addedBy")
    private Set<Recipe> addedRecipes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Recipe> favouriteRecipes;

    public Set<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public User setFavouriteRecipes(Set<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
        return this;
    }

    public Set<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    public User setAddedRecipes(Set<Recipe> recipes){
        this.addedRecipes = recipes;
        return this;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void addFavourite(Recipe recipe) {
        this.favouriteRecipes.add(recipe);
    }
}
