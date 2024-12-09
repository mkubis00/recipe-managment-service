package com.mkvbs.recipe_management_service.mapper;

import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientResponse;
import com.mkvbs.recipe_management_service.model.api.recipe.response.RecipeDetailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeDetailResponseMapperTest {

    private RecipeDetailResponseMapper recipeDetailResponseMapper;
    Mapper<List<Ingredient>, List<IngredientResponse>> ingredientResponseMapper;

    List<Ingredient> ingredients = new ArrayList<>();
    List<IngredientResponse> ingredientResponses;
    private final List<String> pictureLinks = List.of("link1", "link2");
    private final List<String> steps = List.of("step1", "step2");
    private UUID recipeId;
    private final String RECIPE_NAME = "recipeName";
    private final String RECIPE_DESCRIPTION = "recipeDescription";
    private final MealType MEAL_TYPE = MealType.DESSERT;
    private Recipe recipe;

    private final double FIRST_INGREDIENT_QUANTITY = 1;
    private final double SECOND_INGREDIENT_QUANTITY = 2;
    private final String FIRST_INGREDIENT_NAME = "firstIngredientName";
    private final String SECOND_INGREDIENT_NAME = "secondIngredientName";

    private UUID firstIngredientId;
    private UUID secondIngredientId;

    @BeforeEach
    void setUp() {
        recipeId = UUID.randomUUID();
        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();
        ingredientResponses = createIngredientList();

        ingredientResponseMapper = mock(IngredientsResponseMapper.class);
        recipeDetailResponseMapper = new RecipeDetailResponseMapper(ingredientResponseMapper);

        recipe = new Recipe(recipeId, RECIPE_NAME, RECIPE_DESCRIPTION, MEAL_TYPE, pictureLinks, steps, ingredients);

        when(ingredientResponseMapper.map(ingredients)).thenReturn(ingredientResponses);
    }

    private List<IngredientResponse> createIngredientList() {
        List<IngredientResponse> ingredientList = new ArrayList<>();
        ingredientList.add(new IngredientResponse(firstIngredientId, FIRST_INGREDIENT_QUANTITY, FIRST_INGREDIENT_NAME, TypeOfQuantity.NONE, Allergen.NONE));
        ingredientList.add(new IngredientResponse(secondIngredientId, SECOND_INGREDIENT_QUANTITY, SECOND_INGREDIENT_NAME, TypeOfQuantity.NONE, Allergen.NONE));
        return ingredientList;
    }

    @Test
    void mapRecipeToRecipeDetailResponse() {
        RecipeDetailResponse recipeDetailResponse = recipeDetailResponseMapper.map(recipe);

        assertThat(recipeDetailResponse.getName()).isEqualTo(RECIPE_NAME);
        assertThat(recipeDetailResponse.getDescription()).isEqualTo(RECIPE_DESCRIPTION);
        assertThat(recipeDetailResponse.getPicturesLinks()).isEqualTo(pictureLinks);
        assertThat(recipeDetailResponse.getRecipeSteps()).isEqualTo(steps);
        assertThat(recipeDetailResponse.getMealType()).isEqualTo(MEAL_TYPE);
        assertThat(recipeDetailResponse.getIngredients()).hasSize(2);

        List<IngredientResponse> ingredientsResponses = recipeDetailResponse.getIngredients();

        assertThat(ingredientsResponses).extracting(IngredientResponse::getId).containsExactly(firstIngredientId, secondIngredientId);
        assertThat(ingredientsResponses).extracting(IngredientResponse::getName).containsExactly(FIRST_INGREDIENT_NAME, SECOND_INGREDIENT_NAME);
        assertThat(ingredientsResponses).extracting(IngredientResponse::getQuantity).containsExactly(FIRST_INGREDIENT_QUANTITY, SECOND_INGREDIENT_QUANTITY);
        assertThat(ingredientsResponses).extracting(IngredientResponse::getTypeOfQuantity).contains(TypeOfQuantity.NONE);
        assertThat(ingredientsResponses).extracting(IngredientResponse::getAllergen).contains(Allergen.NONE);
    }
}