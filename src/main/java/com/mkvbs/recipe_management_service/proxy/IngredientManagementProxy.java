package com.mkvbs.recipe_management_service.proxy;

import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.api.IngredientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "ingredient-management-service", url = "localhost:8100/ingredients")
public interface IngredientManagementProxy {

    @GetMapping("/v1/get_ingredient/{uuid}")
    ResponseEntity<Ingredient> getIngredientById(@PathVariable UUID uuid);

    @GetMapping("/v1/get_ingredients_from_uuid_list/{uuidList}")
    public ResponseEntity<List<Ingredient>> getIngredientByIdList(@PathVariable List<UUID> uuidList);

    @PostMapping("/v1/post_ingredients")
    public ResponseEntity<List<Ingredient>> postIngredientList(@RequestBody List<IngredientRequest> ingredientsRequest);

    @PostMapping("/v1/post_ingredient")
    public ResponseEntity<Ingredient> postIngredient(@RequestBody IngredientRequest ingredientRequest);
}
