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

    @NotNull(message = ValidationMessage.MEAL_TYPE_NOT_NULL)
    private final MealType mealType;

    @Size(min = 2, message = ValidationMessage.PICTURES_LINKS_LEN)
    @NotNull(message = ValidationMessage.PICTURES_LINKS_NOT_NULL)
    private final List<String> picturesLinks;

    @Size(min = 3, message = ValidationMessage.RECIPE_STEPS_LEN)
    @NotNull(message = ValidationMessage.RECIPE_STEPS_NOT_NULL)
    private final List<String> recipeSteps;

    @Size(min = 3, message = ValidationMessage.INGREDIENTS_LEN)
    @NotNull(message = ValidationMessage.INGREDIENTS_NOT_NULL)
    private final Map<UUID, Double> ingredients;
}