package com.mkvbs.recipe_management_service.factory.ingredient;

import com.mkvbs.recipe_management_service.factory.Factory;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.IngredientResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientsResponseFactory implements Factory<List<IngredientResponse>, Recipe> {

    @Override
    public List<IngredientResponse> create(Recipe resource) {
        List<IngredientResponse> response = new ArrayList<>();

        for (Ingredient ingredient : resource.getIngredients()) {
            if (resource.getIngredientsUuidAndQuantity().containsKey(ingredient.getId())) {
                response.add(createIngredientResponse(ingredient));
            }
        }
        return response;
    }

    private IngredientResponse createIngredientResponse(Ingredient ingredient) {
        return new IngredientResponse(ingredient.getQuantity(), ingredient.getName(),
                ingredient.getTypeOfQuantity(), ingredient.getAllergen());
    }
}
