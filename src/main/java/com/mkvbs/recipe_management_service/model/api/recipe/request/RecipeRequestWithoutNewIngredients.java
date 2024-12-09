package com.mkvbs.recipe_management_service.model.api.recipe.request;

import com.mkvbs.recipe_management_service.model.MealType;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class RecipeRequestWithoutNewIngredients extends RecipeRequest {
    public RecipeRequestWithoutNewIngredients(String name, String description, MealType mealType, List<String> picturesLinks, List<String> recipeSteps, Map<UUID, Double> ingredients) {
        super(name, description, mealType, picturesLinks, recipeSteps, ingredients);
    }
}
