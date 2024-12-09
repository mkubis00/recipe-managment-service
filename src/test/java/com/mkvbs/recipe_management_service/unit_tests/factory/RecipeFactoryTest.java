package com.mkvbs.recipe_management_service.unit_tests.factory;

import com.mkvbs.recipe_management_service.factory.RecipeFactory;
import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequestWithoutNewIngredients;
import com.mkvbs.recipe_management_service.model.entity.RecipeEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeFactoryTest {

    private static final MealType mealType = MealType.APPETIZER;
    private final double FIRST_INGREDIENT_QUANTITY = 1;
    private final double SECOND_INGREDIENT_QUANTITY = 2;
    private final List<String> pictureLinks = List.of("link1", "link2");
    private final List<String> steps = List.of("step1", "step2");
    private List<Ingredient> ingredients;

    private UUID firstIngredientId;
    private UUID secondIngredientId;
    private UUID recipeEntityId;

    private final String RECIPE_NAME = "recipeName";
    private final String RECIPE_DESCRIPTION = "recipeDescription";

    private RecipeRequestWithoutNewIngredients recipeRequest;
    private RecipeEntity recipeEntity;

    private RecipeFactory factory;

    @BeforeAll
    void setUp() {
        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();
        recipeEntityId = UUID.randomUUID();

        ingredients = createIngredientList();

        recipeRequest = new RecipeRequestWithoutNewIngredients(RECIPE_NAME, RECIPE_DESCRIPTION, mealType, pictureLinks, steps, new HashMap<>());
        recipeEntity = new RecipeEntity(recipeEntityId, RECIPE_NAME, RECIPE_DESCRIPTION, mealType, pictureLinks, steps, new HashMap<>());

        factory = new RecipeFactory();
    }

    private List<Ingredient> createIngredientList() {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient(firstIngredientId, "firstIngredientName", FIRST_INGREDIENT_QUANTITY, TypeOfQuantity.NONE, Allergen.NONE));
        ingredientList.add(new Ingredient(secondIngredientId, "secondIngredientName", SECOND_INGREDIENT_QUANTITY, TypeOfQuantity.NONE, Allergen.NONE));
        return ingredientList;
    }

    @Test
    void testCreateRecipeFromRecipeWithoutIngredients() {
        Recipe recipe = factory.createFromRequest(recipeRequest, ingredients);

        assertThat(recipe.getName()).isEqualTo(RECIPE_NAME);
        assertThat(recipe.getDescription()).isEqualTo(RECIPE_DESCRIPTION);
        assertThat(recipe.getPicturesLinks()).isEqualTo(pictureLinks);
        assertThat(recipe.getRecipeSteps()).isEqualTo(steps);
        assertThat(recipe.getMealType()).isEqualTo(mealType);
        assertThat(recipe.getIngredients()).hasSize(2);
    }

    @Test
    void testCreateRecipeFromRecipeEntity() {
        Recipe recipe = factory.createFromRecipeEntity(recipeEntity, ingredients);

        assertThat(recipe.getName()).isEqualTo(RECIPE_NAME);
        assertThat(recipe.getDescription()).isEqualTo(RECIPE_DESCRIPTION);
        assertThat(recipe.getPicturesLinks()).isEqualTo(pictureLinks);
        assertThat(recipe.getRecipeSteps()).isEqualTo(steps);
        assertThat(recipe.getMealType()).isEqualTo(mealType);
        assertThat(recipe.getIngredients()).hasSize(2);
    }
}