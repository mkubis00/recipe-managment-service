package com.mkvbs.recipe_management_service.factory.recipe;

import com.mkvbs.recipe_management_service.factory.Factory;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.RecipeRequest;

public class RecipeFactory implements Factory<Recipe, RecipeRequest> {
    @Override
    public Recipe create(RecipeRequest resource) {
        return new Recipe(
                resource.getName(),
                resource.getDescription(),
                resource.getMealType(),
                resource.getPicturesLinks(),
                resource.getRecipeSteps(),
                resource.getIngredients());
    }
}
