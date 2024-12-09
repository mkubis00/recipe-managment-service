package com.mkvbs.recipe_management_service.api;

import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequest;
import com.mkvbs.recipe_management_service.model.api.recipe.response.RecipeResponse;
import org.springframework.http.ResponseEntity;

public interface RecipePostController<Request extends RecipeRequest, Response extends RecipeResponse> {

    ResponseEntity<Response> postRecipe(Request recipeRequest);
}