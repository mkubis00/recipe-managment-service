package com.mkvbs.recipe_management_service.mapper;

import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class IngredientsResponseMapperTest {

    private IngredientsResponseMapper ingredientsResponseMapper;

    private UUID firstIngredientId;
    private UUID secondIngredientId;
    private final double FIRST_INGREDIENT_QUANTITY = 1;
    private final double SECOND_INGREDIENT_QUANTITY = 2;
    private final String FIRST_INGREDIENT_NAME = "firstIngredientName";
    private final String SECOND_INGREDIENT_NAME = "secondIngredientName";
    private List<Ingredient> ingredients;

    @BeforeEach
    void setUp() {
        ingredientsResponseMapper = new IngredientsResponseMapper();

        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();
        ingredients = createIngredientList();
    }

    private List<Ingredient> createIngredientList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient(firstIngredientId, FIRST_INGREDIENT_NAME, FIRST_INGREDIENT_QUANTITY, TypeOfQuantity.NONE, Allergen.NONE));
        ingredientList.add(new Ingredient(secondIngredientId, SECOND_INGREDIENT_NAME, SECOND_INGREDIENT_QUANTITY, TypeOfQuantity.NONE, Allergen.NONE));
        return ingredientList;
    }

    @Test
    void testMapIngredientsResponseFromIngredients() {
        List<IngredientResponse> ingredientResponses = ingredientsResponseMapper.map(ingredients);

        assertThat(ingredientResponses).hasSize(2);
        assertThat(ingredientResponses).extracting(IngredientResponse::getId).containsExactly(firstIngredientId, secondIngredientId);
        assertThat(ingredientResponses).extracting(IngredientResponse::getName).containsExactly(FIRST_INGREDIENT_NAME, SECOND_INGREDIENT_NAME);
        assertThat(ingredientResponses).extracting(IngredientResponse::getQuantity).containsExactly(FIRST_INGREDIENT_QUANTITY, SECOND_INGREDIENT_QUANTITY);
        assertThat(ingredientResponses).extracting(IngredientResponse::getTypeOfQuantity).contains(TypeOfQuantity.NONE);
        assertThat(ingredientResponses).extracting(IngredientResponse::getAllergen).contains(Allergen.NONE);
    }
}