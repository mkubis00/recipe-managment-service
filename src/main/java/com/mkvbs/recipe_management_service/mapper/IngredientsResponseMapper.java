package com.mkvbs.recipe_management_service.mapper;

import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class IngredientsResponseMapper implements Mapper<List<Ingredient>, List<IngredientResponse>> {

    @Override
    public List<IngredientResponse> map(List<Ingredient> from) {
        return from.stream()
                .map(this::mapIngredient)
                .collect(Collectors.toList());
    }

    private IngredientResponse mapIngredient(Ingredient ingredient) {
        return new IngredientResponse(
                ingredient.getId(),
                ingredient.getQuantity(),
                ingredient.getName(),
                ingredient.getTypeOfQuantity(),
                ingredient.getAllergen());
    }
}
