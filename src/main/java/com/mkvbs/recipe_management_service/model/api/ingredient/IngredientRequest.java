package com.mkvbs.recipe_management_service.model.api.ingredient;

import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class IngredientRequest {

    private final double quantity;
    private final String name;
    private final TypeOfQuantity typeOfQuantity;
    private final Allergen allergen;
}
