package com.mkvbs.recipe_management_service.factory.recipe;

import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.api.RecipeRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeFactoryTest {

    private static final String recipeName = "recipeName";
    private static final String recipeDescription = "recipeDescription";
    private static final MealType mealType = MealType.APPETIZER;
    private final double firstIngredientQuantity = 1;
    private final double secondIngredientQuantity = 2;
    private final List<String> pictureLinks = List.of("link1", "link2");
    private final List<String> steps = List.of("step1", "step2");

    private UUID firstIngredientId;
    private UUID secondIngredientId;
    private Map<UUID, Double> ingredientMap;

    private RecipeRequest recipeRequest;
    private RecipeFactory factory;

    @BeforeAll
    void setUp() {
        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();

        ingredientMap = createIngredientMap();

        recipeRequest = new RecipeRequest(recipeName, recipeDescription, mealType, pictureLinks, steps, ingredientMap);

        factory = new RecipeFactory();
    }

    @Test
    void create() {
        Recipe recipe = factory.create(recipeRequest);

        assertThat(recipe.getName()).isEqualTo(recipeName);
        assertThat(recipe.getDescription()).isEqualTo(recipeDescription);
        assertThat(recipe.getPicturesLinks()).isEqualTo(pictureLinks);
        assertThat(recipe.getRecipeSteps()).isEqualTo(steps);
        assertThat(recipe.getMealType()).isEqualTo(mealType);

        Map<UUID, Double> ingredientsUuidAndQuantity = recipe.getIngredientsUuidAndQuantity();

        assertThat(ingredientsUuidAndQuantity).containsKeys(firstIngredientId, secondIngredientId);
        assertThat(ingredientsUuidAndQuantity.get(firstIngredientId)).isEqualTo(firstIngredientQuantity);
        assertThat(ingredientsUuidAndQuantity.get(secondIngredientId)).isEqualTo(secondIngredientQuantity);
    }

    private Map<UUID, Double> createIngredientMap() {
        return Map.of(firstIngredientId, firstIngredientQuantity, secondIngredientId, secondIngredientQuantity);
    }
}