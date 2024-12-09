package com.mkvbs.recipe_management_service.model.api.recipe.response;

import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientResponse;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class RecipeDetailResponse extends RecipeResponse {

    private final MealType mealType;
    private final List<String> recipeSteps;
    private List<IngredientResponse> ingredients;

    public RecipeDetailResponse(UUID id, String name, String description, List<String> picturesLinks, MealType mealType, List<String> recipeSteps, List<IngredientResponse> ingredients) {
        super(id, name, description, picturesLinks);
        this.mealType = mealType;
        this.recipeSteps = recipeSteps;
        this.ingredients = ingredients;
    }
}
