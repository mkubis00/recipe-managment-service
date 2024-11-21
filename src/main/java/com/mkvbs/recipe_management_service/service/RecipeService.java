package com.mkvbs.recipe_management_service.service;

import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.RecipeRequest;
import com.mkvbs.recipe_management_service.proxy.IngredientManagementProxy;
import com.mkvbs.recipe_management_service.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientManagementProxy proxy;


    public RecipeService(RecipeRepository recipeRepository, IngredientManagementProxy proxy) {
        this.recipeRepository = recipeRepository;
        this.proxy = proxy;
    }

    public Recipe findRecipeById(UUID id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
//        ResponseEntity<List<Ingredient>> ingredients = ingredientManagementProxy.getIngredientByIdList(recipe.getIngredientsUuid());
//        recipe.setIngredients(ingredients.getBody());
        return recipe;
    }

    public Recipe saveRecipe(RecipeRequest recipeRequest) {

//        Map<Ingredient, Double> savedRecipes = recipeRequest.getIngredients().stream()
//                .collect(Collectors.toMap(
//                        ingredientRequest -> proxy.postIngredient(ingredientRequest).getBody(),
//                        IngredientRequest::getQuantity
//                ));
//
//        List<Ingredient> savedIngredients = recipeRequest.getIngredients().stream().collect(
//                Collectors.toList(ingredientRequest -> proxy.postIngredient(ingredientRequest).getBody())
//        );

//        List<Ingredient> savedIngredients = new ArrayList<>();
//        recipeRequest.getIngredients().forEach(ingredientRequest -> {
//            Ingredient ingredient = proxy.postIngredient(ingredientRequest).getBody();
//            ingredient.setQuantity(ingredientRequest.getQuantity());
//            savedIngredients.add(ingredient);
//        });

//        Recipe recipe = recipeFactory.createRecipe(recipeRequest, savedIngredients);
//        Recipe recipe = Recipe.builder().build();
        return recipeRepository.save(null);
    }
}
