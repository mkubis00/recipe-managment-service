package com.mkvbs.recipe_management_service.mapper;

import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.entity.RecipeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RecipeEntityMapperTest {

    private RecipeEntityMapper recipeEntityMapper;
    private Recipe recipe;

    private UUID recipeId;
    private final String RECIPE_NAME = "recipeName";
    private final String RECIPE_DESCRIPTION = "recipeDescription";
    private final MealType MEAL_TYPE = MealType.DESSERT;
    private final List<String> pictureLinks = List.of("link1", "link2");
    private final List<String> steps = List.of("step1", "step2");

    private UUID firstIngredientId;
    private UUID secondIngredientId;
    private final double FIRST_INGREDIENT_QUANTITY = 1;
    private final double SECOND_INGREDIENT_QUANTITY = 2;
    private List<Ingredient> ingredients;

    @BeforeEach
    void setUp() {
        recipeId = UUID.randomUUID();
        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();
        ingredients = createIngredientList();
        recipeEntityMapper = new RecipeEntityMapper();

        recipe = new Recipe(recipeId, RECIPE_NAME, RECIPE_DESCRIPTION, MEAL_TYPE, pictureLinks, steps, ingredients);
    }

    private List<Ingredient> createIngredientList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient(firstIngredientId, "firstIngredientName", FIRST_INGREDIENT_QUANTITY, TypeOfQuantity.NONE, Allergen.NONE));
        ingredientList.add(new Ingredient(secondIngredientId, "secondIngredientName", SECOND_INGREDIENT_QUANTITY, TypeOfQuantity.NONE, Allergen.NONE));
        return ingredientList;
    }

    @Test
    void testMapToRecipeEntity() {
        RecipeEntity recipeEntity = recipeEntityMapper.map(recipe);

        assertThat(recipeEntity.getName()).isEqualTo(RECIPE_NAME);
        assertThat(recipeEntity.getDescription()).isEqualTo(RECIPE_DESCRIPTION);
        assertThat(recipeEntity.getPicturesLinks()).isEqualTo(pictureLinks);
        assertThat(recipeEntity.getRecipeSteps()).isEqualTo(steps);
        assertThat(recipeEntity.getMealType()).isEqualTo(MEAL_TYPE);

        Map<UUID, Double> ingredientsUuidAndQuantity = recipeEntity.getIngredientsUuidAndQuantity();

        assertThat(ingredientsUuidAndQuantity).containsKeys(firstIngredientId, secondIngredientId);
        assertThat(ingredientsUuidAndQuantity.get(firstIngredientId)).isEqualTo(FIRST_INGREDIENT_QUANTITY);
        assertThat(ingredientsUuidAndQuantity.get(secondIngredientId)).isEqualTo(SECOND_INGREDIENT_QUANTITY);
    }
}