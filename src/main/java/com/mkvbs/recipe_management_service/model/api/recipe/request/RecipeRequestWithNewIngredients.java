package com.mkvbs.recipe_management_service.model.api.recipe.request;

import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientRequest;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class RecipeRequestWithNewIngredients extends RecipeRequest {

    private final List<IngredientRequest> newIngredients;

    public RecipeRequestWithNewIngredients(String name, String description, MealType mealType, List<String> picturesLinks, List<String> recipeSteps, Map<UUID, Double> ingredients, List<IngredientRequest> newIngredients) {
        super(name, description, mealType, picturesLinks, recipeSteps, ingredients);
        this.newIngredients = newIngredients;
    }
}
