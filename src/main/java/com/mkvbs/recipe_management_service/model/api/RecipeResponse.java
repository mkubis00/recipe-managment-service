package com.mkvbs.recipe_management_service.model.api;

import com.mkvbs.recipe_management_service.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RecipeResponse {

    private final UUID id;
    private final String name;
    private final String description;
    private final MealType mealType;
    private final List<String> picturesLinks;
    private final List<String> recipeSteps;
    private List<IngredientResponse> ingredients;
}
