package com.mkvbs.recipe_management_service.mapper;

import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientResponse;
import com.mkvbs.recipe_management_service.model.api.recipe.response.RecipeDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RecipeDetailResponseMapper implements Mapper<Recipe, RecipeDetailResponse> {

    Mapper<List<Ingredient>, List<IngredientResponse>> ingredientResponseMapper;

    @Override
    public RecipeDetailResponse map(Recipe from) {
        List<IngredientResponse> ingredientResponses = mapIngredients(from.getIngredients());
        return new RecipeDetailResponse(
                from.getId(),
                from.getName(),
                from.getDescription(),
                from.getPicturesLinks(),
                from.getMealType(),
                from.getRecipeSteps(),
                ingredientResponses);
    }

    private List<IngredientResponse> mapIngredients(List<Ingredient> from) {
        return ingredientResponseMapper.map(from);
    }
}
