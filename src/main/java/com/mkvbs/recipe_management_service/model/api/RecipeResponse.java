package com.mkvbs.recipe_management_service.model.api;

import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class RecipeResponse {

    private final UUID id;
    private final String name;
    private final String description;
    private final MealType mealType;
    private final List<String> picturesLinks;
    private final List<String> recipeSteps;
    private List<Ingredient> ingredients;

    public static RecipeResponse convertFromRecipe(Recipe recipe) {
        return RecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .mealType(recipe.getMealType())
                .picturesLinks(recipe.getPicturesLinks())
                .recipeSteps(recipe.getRecipeSteps())
                .ingredients(recipe.getIngredients())
                .build();
    }
}
