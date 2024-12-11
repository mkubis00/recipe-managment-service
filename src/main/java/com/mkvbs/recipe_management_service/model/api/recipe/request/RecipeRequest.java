package com.mkvbs.recipe_management_service.model.api.recipe.request;

import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.resource.api.ValidationMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class RecipeRequest {

    @Size(min = 3, max = 50, message = ValidationMessage.INCORRECT_NAME_LEN)
    @NotNull(message = ValidationMessage.NAME_NOT_NULL)
    private final String name;
    @Size(min = 20, max = 200, message = ValidationMessage.INCORRECT_DESCRIPTION_LEN)
    @NotNull(message = ValidationMessage.DESCRIPTION_NOT_NULL)
    private final String description;
    private final MealType mealType;
    private final List<String> picturesLinks;
    private final List<String> recipeSteps;
    private final Map<UUID, Double> ingredients;
}