package com.mkvbs.recipe_management_service.factory.recipe;

import com.mkvbs.recipe_management_service.factory.Factory;
import com.mkvbs.recipe_management_service.factory.ingredient.IngredientsResponseFactory;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.IngredientResponse;
import com.mkvbs.recipe_management_service.model.api.RecipeResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class RecipeResponseFactory implements Factory<RecipeResponse, Recipe> {

    @Autowired
    private final IngredientsResponseFactory ingredientsResponseFactory;

    @Override
    public RecipeResponse create(Recipe resource) {
        List<IngredientResponse> ingredientsResponse = ingredientsResponseFactory.create(resource);
        return new RecipeResponse(resource.getId(),
                resource.getName(),
                resource.getDescription(),
                resource.getMealType(),
                resource.getPicturesLinks(),
                resource.getRecipeSteps(),
                ingredientsResponse);
    }
}
