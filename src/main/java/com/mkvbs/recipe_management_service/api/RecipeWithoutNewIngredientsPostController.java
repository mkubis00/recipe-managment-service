package com.mkvbs.recipe_management_service.api;

import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequestWithoutNewIngredients;
import com.mkvbs.recipe_management_service.model.api.recipe.response.RecipeDetailResponse;
import com.mkvbs.recipe_management_service.resource.api.ApiPath;
import com.mkvbs.recipe_management_service.service.recipe.RecipePostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RecipeWithoutNewIngredientsPostController implements RecipePostController<RecipeRequestWithoutNewIngredients, RecipeDetailResponse> {

    private final RecipePostService<RecipeRequestWithoutNewIngredients, RecipeDetailResponse> recipePostService;

    @PostMapping(ApiPath.RECIPE.CREATE_V1)
    public ResponseEntity<RecipeDetailResponse> postRecipe(@Valid @RequestBody RecipeRequestWithoutNewIngredients recipeRequest) {
        RecipeDetailResponse recipeDetailResponse = recipePostService.saveRecipe(recipeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeDetailResponse);
    }
}
