package com.mkvbs.recipe_management_service.factory.ingredient;

import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.api.IngredientResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IngredientsResponseFactoryTest {

    private IngredientsResponseFactory factory;

    private final double firstIngredientQuantity = 1;
    private final double secondIngredientQuantity = 2;

    private UUID firstIngredientId;
    private UUID secondIngredientId;

    @BeforeAll
    void setUp() {
        factory = new IngredientsResponseFactory();

        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();
    }

    @Test
    void testCreate() {
        Map<UUID, Double> ingredientsMap = createIngredientMap();
        List<Ingredient> ingredientList = createIngredientList();
        Recipe recipe = new Recipe(null, null, null, null, null, null, ingredientsMap, ingredientList);

        List<IngredientResponse> ingredientResponses = factory.create(recipe);
        assertThat(ingredientResponses.size()).isEqualTo(2);

        Map<UUID, Double> uuids = ingredientResponses.stream().collect(Collectors.toMap(IngredientResponse::getId, IngredientResponse::getQuantity));
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