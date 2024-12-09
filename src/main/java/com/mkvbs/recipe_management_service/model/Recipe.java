package com.mkvbs.recipe_management_service.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Getter
public class Recipe {

    private final UUID id;
    private final String name;
    private final String description;
    private final MealType mealType;
    private final List<String> picturesLinks;
    private final List<String> recipeSteps;
    private final List<Ingredient> ingredients;

    public Recipe(UUID id, String name, String description, MealType mealType, List<String> picturesLinks, List<String> recipeSteps, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mealType = mealType;
        this.picturesLinks = picturesLinks;
        this.recipeSteps = recipeSteps;
        this.ingredients = new ArrayList<>(new HashSet<>(ingredients));
    }

    public Recipe(String name, String description, MealType mealType, List<String> picturesLinks, List<String> recipeSteps, List<Ingredient> ingredients) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.mealType = mealType;
        this.picturesLinks = picturesLinks;
        this.recipeSteps = recipeSteps;
        this.ingredients = new ArrayList<>(new HashSet<>(ingredients));
    }
}
