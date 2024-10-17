package com.mkvbs.recipe_management_service.model.api;

import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IngredientRequest {

    private final String name;
    private final double quantity;
    private final TypeOfQuantity typeOfQuantity;
    private final Allergen allergen;

    public Ingredient createIngredient() {
        return Ingredient.builder()
                .name(name)
                .typeOfQuantity(typeOfQuantity)
                .typeOfQuantity(typeOfQuantity)
                .allergen(allergen)
                .build();
    }

    public Ingredient createIngredient(List<Ingredient> ingredients) {
        return Ingredient.builder()
                .name(name)
                .typeOfQuantity(typeOfQuantity)
                .typeOfQuantity(typeOfQuantity)
                .allergen(allergen)
                .build();
    }
}
