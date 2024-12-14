package com.mkvbs.recipe_management_service.model.api.recipe.request;

import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientRequest;
import com.mkvbs.recipe_management_service.resource.api.ValidationMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class RecipeRequestWithNewIngredients extends RecipeRequest {

    @Size(min = 2, message = ValidationMessage.NEW_INGREDIENTS_LEN)
    @NotNull(message = ValidationMessage.NEW_INGREDIENTS_NOT_NULL)
    private final List<IngredientRequest> newIngredients;

    public RecipeRequestWithNewIngredients(String name, String description, MealType mealType, List<String> picturesLinks, List<String> recipeSteps, Map<UUID, Double> ingredients, List<IngredientRequest> newIngredients) {
        super(name, description, mealType, picturesLinks, recipeSteps, ingredients);
        this.newIngredients = newIngredients;
    }
}
