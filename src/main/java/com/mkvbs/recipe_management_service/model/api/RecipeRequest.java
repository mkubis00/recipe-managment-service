package com.mkvbs.recipe_management_service.model.api;

import com.mkvbs.recipe_management_service.model.MealType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RecipeRequest {

    private final String name;
    private final String description;
    private final MealType mealType;
    private final List<String> picturesLinks;
    private final List<String> recipeSteps;
    private final Map<UUID, Double> ingredients;
}
