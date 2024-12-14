package com.mkvbs.recipe_management_service.model.api.ingredient;

import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.resource.api.ValidationMessage;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class IngredientRequest {

    @DecimalMin(value = "0.1", message = ValidationMessage.QUANTITY_VALUE)
    @NotNull(message = ValidationMessage.QUANTITY_NOT_NULL)
    private final double quantity;

    @Size(min = 3, max = 50, message = ValidationMessage.INCORRECT_NAME_LEN)
    @NotNull(message = ValidationMessage.NAME_NOT_NULL)
    private final String name;

    @NotNull(message = ValidationMessage.TYPE_OF_QUA_NOT_NULL)
    private final TypeOfQuantity typeOfQuantity;

    @NotNull(message = ValidationMessage.ALLERGEN_NOT_NULL)
    private final Allergen allergen;
}
