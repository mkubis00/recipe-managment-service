package com.mkvbs.recipe_management_service.resource.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationMessage {

    public static final String INCORRECT_NAME_LEN = "name must be between 3 and 50 characters";
    public static final String NAME_NOT_NULL = "name cannot be null";

    public static final String DESCRIPTION_NOT_NULL = "description cannot be null";
    public static final String INCORRECT_DESCRIPTION_LEN = "name must be between 20 and 200 characters";

    public static final String MEAL_TYPE_NOT_NULL = "meal type cannot be null";

    public static final String PICTURES_LINKS_LEN = "pictures links length should be greater than 1 ";
    public static final String PICTURES_LINKS_NOT_NULL = "pictures links cannot be null";

    public static final String RECIPE_STEPS_LEN = "recipe steps length should be greater than 2";
    public static final String RECIPE_STEPS_NOT_NULL = "recipe steps cannot be null";

    public static final String INGREDIENTS_LEN = "ingredients length should be greater than 2";
    public static final String INGREDIENTS_NOT_NULL = "ingredients cannot be null";

    public static final String NEW_INGREDIENTS_LEN = "newIngredients length should be greater than 1";
    public static final String NEW_INGREDIENTS_NOT_NULL = "newIngredients cannot be null";

    public static final String TYPE_OF_QUA_NOT_NULL = "type of quantity cannot be null";
    public static final String ALLERGEN_NOT_NULL = "type of allergen cannot be null";

    public static final String QUANTITY_VALUE = "quantity value should be greater than 0.1";
    public static final String QUANTITY_NOT_NULL = "quantity cannot be null";

    public static final String HTTP_MESSAGE_NOT_READABLE = "Some of the values aren't assigned correctly. Check structure of the json.";
}