package com.mkvbs.recipe_management_service.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
@AllArgsConstructor
public class RecipeBasicOperationController {

//    private final RecipeService recipeService;
//
//    @GetMapping("/v1/get_recipe_by_id/{id}")
//    public ResponseEntity<Recipe> getRecipeById(@PathVariable UUID id) {
//        Recipe recipe = recipeService.findRecipeById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(recipe);
//    }
//
//    @PostMapping("/v1/post_recipe")
//    public ResponseEntity<Recipe> postRecipe(@RequestBody RecipeRequest recipeRequest) {
//        Recipe recipe = recipeService.saveRecipe(recipeRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(recipe);
//    }
}
