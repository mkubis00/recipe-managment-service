package com.mkvbs.recipe_management_service.factory;

import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequest;
import com.mkvbs.recipe_management_service.model.entity.RecipeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeFactory {

    public Recipe createFromRequest(RecipeRequest resource, List<Ingredient> ingredients) {
        return new Recipe(
                resource.getName(),
                resource.getDescription(),
                resource.getMealType(),
                resource.getPicturesLinks(),
                resource.getRecipeSteps(),
                ingredients);
    }

    public Recipe createFromRecipeEntity(RecipeEntity resource, List<Ingredient> ingredients) {
        return new Recipe(
                resource.getId(),
                resource.getName(),
                resource.getDescription(),
                resource.getMealType(),
                resource.getPicturesLinks(),
                resource.getRecipeSteps(),
                ingredients);
    }
}
