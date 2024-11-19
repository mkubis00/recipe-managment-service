package com.mkvbs.recipe_management_service.service;

import com.mkvbs.recipe_management_service.factory.Factory;
import com.mkvbs.recipe_management_service.factory.EntityFactory;
import com.mkvbs.recipe_management_service.factory.providers.DefaultFactoryProvider;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.IngredientRequest;
import com.mkvbs.recipe_management_service.model.api.RecipeRequest;
import com.mkvbs.recipe_management_service.model.api.RecipeResponse;
import com.mkvbs.recipe_management_service.model.api.SmallRecipeResponse;
import com.mkvbs.recipe_management_service.proxy.IngredientManagementProxy;
import com.mkvbs.recipe_management_service.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientManagementProxy proxy;
    private final Factory<SmallRecipeResponse, Recipe> smallRecipeResponseFactory;
    private final Factory<RecipeResponse, Recipe> recipeResponseFactory;
    private final EntityFactory<Recipe, RecipeRequest, List<Ingredient>> recipeFactory;
    private final EntityFactory<Ingredient, IngredientRequest> ingredientFactory;

    public RecipeService(RecipeRepository recipeRepository, IngredientManagementProxy proxy, DefaultFactoryProvider defaultFactoryProvider) {
        this.recipeRepository = recipeRepository;
        this.proxy = proxy;
        this.smallRecipeResponseFactory = defaultFactoryProvider.getSmallRecipeResponseFactory();
        this.recipeResponseFactory = defaultFactoryProvider.getRecipeResponseFactory();
        this.recipeFactory = defaultFactoryProvider.getRecipesFactory();
        this.ingredientFactory = defaultFactoryProvider.getIngredientFactory();
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

        List<Ingredient> savedIngredients = new ArrayList<>();
        recipeRequest.getIngredients().forEach(ingredientRequest -> {
            Ingredient ingredient = proxy.postIngredient(ingredientRequest).getBody();
            ingredient.setQuantity(ingredientRequest.getQuantity());
            savedIngredients.add(ingredient);
        });

//        Recipe recipe = recipeFactory.createRecipe(recipeRequest, savedIngredients);
        Recipe recipe = Recipe.builder().build();
        return recipeRepository.save(recipe);
    }
}
