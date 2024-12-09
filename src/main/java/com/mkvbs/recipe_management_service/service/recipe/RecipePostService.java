package com.mkvbs.recipe_management_service.service.recipe;

import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequest;
import com.mkvbs.recipe_management_service.model.api.recipe.response.RecipeResponse;

public interface RecipePostService<Request extends RecipeRequest, Response extends RecipeResponse> {

    Response saveRecipe(Request request);
}
