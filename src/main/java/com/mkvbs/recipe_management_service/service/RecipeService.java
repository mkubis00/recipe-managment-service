package com.mkvbs.recipe_management_service.service;

import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.api.RecipeRequest;
import com.mkvbs.recipe_management_service.proxy.IngredientManagementProxy;
import com.mkvbs.recipe_management_service.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientManagementProxy proxy;
    private final IngredientManagementProxy ingredientManagementProxy;

    public Recipe findRecipeById(UUID id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
//        ResponseEntity<List<Ingredient>> ingredients = ingredientManagementProxy.getIngredientByIdList(recipe.getIngredientsUuid());
//        recipe.setIngredients(ingredients.getBody());
        return recipe;
    }

    public Recipe saveRecipe(RecipeRequest recipeRequest) {
        Map<UUID, Double> savedRecipes = new HashMap<>();
        recipeRequest.getIngredients().forEach(ingredientRequest -> {
            Ingredient savedIngredient = proxy.postIngredient(ingredientRequest).getBody();
            savedRecipes.put(savedIngredient.getId(), ingredientRequest.getQuantity());
        });
        Recipe recipe = Recipe.builder().build();
        return recipeRepository.save(recipe);
    }
}
