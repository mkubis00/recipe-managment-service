package com.mkvbs.recipe_management_service.service.recipe;

import com.mkvbs.recipe_management_service.factory.RecipeFactory;
import com.mkvbs.recipe_management_service.mapper.Mapper;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequestWithoutNewIngredients;
import com.mkvbs.recipe_management_service.model.api.recipe.response.RecipeDetailResponse;
import com.mkvbs.recipe_management_service.model.entity.RecipeEntity;
import com.mkvbs.recipe_management_service.proxy.IngredientManagementProxy;
import com.mkvbs.recipe_management_service.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RecipePostServiceImpl implements RecipePostService<RecipeRequestWithoutNewIngredients, RecipeDetailResponse> {


    private final RecipeRepository recipeRepository;
    private final RecipeFactory recipeFactory;
    private final Mapper<Recipe, RecipeEntity> recipeEntityMapper;
    private final Mapper<Recipe, RecipeDetailResponse> recipeResponseMapper;
    private final IngredientManagementProxy ingredientProxy;

    public RecipeDetailResponse saveRecipe(RecipeRequestWithoutNewIngredients recipeRequest) {
        List<Ingredient> ingredients = getRecipeIngredients(recipeRequest.getIngredients());
        Recipe recipe = recipeFactory.createFromRequest(recipeRequest, ingredients);

        RecipeEntity recipeEntity = recipeEntityMapper.map(recipe);
        RecipeEntity savedRecipeEntity = recipeRepository.save(recipeEntity);

        Recipe savedRecipe = recipeFactory.createFromRecipeEntity(savedRecipeEntity, ingredients);
        return recipeResponseMapper.map(savedRecipe);
    }

    private List<Ingredient> getRecipeIngredients(Map<UUID, Double> ingredintsMap) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Map.Entry<UUID, Double> entry : ingredintsMap.entrySet()) {
            Ingredient ingredient = getIngredientById(entry.getKey());
            ingredient.setQuantity(entry.getValue());
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    private Ingredient getIngredientById(UUID id) {
        return ingredientProxy.getIngredientById(id).getBody();
    }
}
