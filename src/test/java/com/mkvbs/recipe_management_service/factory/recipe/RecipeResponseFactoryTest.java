package com.mkvbs.recipe_management_service.factory.recipe;

import com.mkvbs.recipe_management_service.factory.ingredient.IngredientsResponseFactory;
import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.api.IngredientResponse;
import com.mkvbs.recipe_management_service.model.api.RecipeResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RecipeResponseFactoryTest {

    private RecipeResponseFactory recipeResponseFactory;
    private Recipe recipe;
    private IngredientsResponseFactory ingredientsResponseFactory;

    private UUID recipeId;
    private List<Ingredient> ingredients;
    private List<IngredientResponse> ingredientsResponse;
    private Map<UUID, Double> ingredientMap;

    private final List<String> pictureLinks = List.of("link1", "link2");
    private final List<String> steps = List.of("step1", "step2");
    private final double firstIngredientQuantity = 1;
    private final double secondIngredientQuantity = 2;

    private UUID firstIngredientId;
    private UUID secondIngredientId;

    private static final String recipeName = "recipeName";
    private static final String recipeDescription = "recipeDescription";
    private static final MealType mealType = MealType.APPETIZER;

    @BeforeAll
    public void setUp() {
        ingredientsResponseFactory = new IngredientsResponseFactory();
        recipeResponseFactory = new RecipeResponseFactory(ingredientsResponseFactory);

        recipeId = UUID.randomUUID();
        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();

        ingredients = createIngredientList();
        ingredientMap = createIngredientMap();

        recipe = new Recipe(recipeId, recipeName, recipeDescription, mealType, pictureLinks, steps, ingredientMap, ingredients);
    }

    @Test
    void testCreate() {
        RecipeResponse recipeResponse = recipeResponseFactory.create(recipe);

        assertThat(recipeResponse.getId()).isEqualTo(recipeId);
        assertThat(recipeResponse.getName()).isEqualTo(recipeName);
        assertThat(recipeResponse.getDescription()).isEqualTo(recipeDescription);
        assertThat(recipeResponse.getPicturesLinks()).isEqualTo(pictureLinks);
        assertThat(recipeResponse.getRecipeSteps()).isEqualTo(steps);
        assertThat(recipeResponse.getMealType()).isEqualTo(mealType);

        Map<UUID, Double> uuids = recipeResponse.getIngredients().stream().collect(Collectors.toMap(IngredientResponse::getId, IngredientResponse::getQuantity));

        assertThat(uuids).containsKeys(firstIngredientId, secondIngredientId);
        assertThat(uuids.get(firstIngredientId)).isEqualTo(firstIngredientQuantity);
        assertThat(uuids.get(secondIngredientId)).isEqualTo(secondIngredientQuantity);
    }

    private List<Ingredient> createIngredientList() {
        Ingredient ingredient1 = new Ingredient(firstIngredientId, "name1", firstIngredientQuantity, TypeOfQuantity.NONE, Allergen.NONE);
        Ingredient ingredient2 = new Ingredient(secondIngredientId, "name2", secondIngredientQuantity, TypeOfQuantity.NONE, Allergen.NONE);
        return List.of(ingredient1, ingredient2);
    }

    private Map<UUID, Double> createIngredientMap() {
        return Map.of(firstIngredientId, firstIngredientQuantity, secondIngredientId, secondIngredientQuantity);
    }
}