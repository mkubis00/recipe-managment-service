package com.mkvbs.recipe_management_service.model.api;

import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class RecipeRequest {

    private final String name;
    private final String description;
    private final MealType mealType;
    private final List<String> picturesLinks;
    private final List<String> recipeSteps;
    private List<IngredientRequest> ingredients;

    public Recipe convertToRecipe() {
        return Recipe.builder()
                .name(name)
                .description(description)
                .mealType(mealType)
                .picturesLinks(picturesLinks)
                .recipeSteps(recipeSteps)
                .ingredients(ingredients.stream().map(IngredientRequest::createIngredient).toList())
                .build();
    }
}
