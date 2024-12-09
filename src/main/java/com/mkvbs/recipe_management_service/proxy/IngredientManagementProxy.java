package com.mkvbs.recipe_management_service.proxy;

import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "ingredient-management-service", url = "localhost:8100")
public interface IngredientManagementProxy {

    @GetMapping("/api/v1/ingredients/{id}")
    ResponseEntity<Ingredient> getIngredientById(@PathVariable UUID id);

    @GetMapping("/api/v1/ingredients/from_id_list/{idsList}")
    ResponseEntity<List<Ingredient>> getIngredientByIdList(@PathVariable List<UUID> uuidList);

    @PostMapping("/v1/post_ingredients")
    ResponseEntity<List<Ingredient>> postIngredientList(@RequestBody List<IngredientRequest> ingredientsRequest);

    @PostMapping("/v1/post_ingredient")
    ResponseEntity<Ingredient> postIngredient(@RequestBody IngredientRequest ingredientRequest);
}
