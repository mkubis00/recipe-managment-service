package com.mkvbs.recipe_management_service.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequestWithoutNewIngredients;
import com.mkvbs.recipe_management_service.proxy.IngredientManagementProxy;
import com.mkvbs.recipe_management_service.resource.api.ApiPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecipeWithoutNewIngredientsPostControllerTest extends BasicIntegration {

    @MockBean
    private ResponseEntity<Ingredient> ingredientResponseEntity;
    @MockBean
    private IngredientManagementProxy ingredientManagementProxy;

    RecipeRequestWithoutNewIngredients request;

    private final String recipeName = "recipeName";
    private final String recipeDescription = "recipeDescriptionaaaaaaaaaaaaaaaaaaaa";
    private final MealType mealType = MealType.DESSERT;
    private final List<String> pictureLinks = List.of("link1", "link2");
    private final List<String> steps = List.of("step1", "step2");

    private Ingredient ingredient;
    private UUID ingredientId;
    private final String ingredientName = "ingredientName";
    private final TypeOfQuantity typeOfQuantity = TypeOfQuantity.NONE;
    private final Allergen allergen = Allergen.NONE;

    @Autowired
    private ObjectMapper objectMapper;

    public RecipeWithoutNewIngredientsPostControllerTest() {
        this.ingredientId = UUID.randomUUID();
        this.ingredient = new Ingredient(ingredientId, ingredientName, 1, typeOfQuantity, allergen);
    }

    @Test
    void testPostRecipeWithoutNewIngredients() throws Exception {
        when(ingredientResponseEntity.getBody()).thenReturn(ingredient);
        when(ingredientResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);

        request = new RecipeRequestWithoutNewIngredients(recipeName, recipeDescription, mealType, pictureLinks, steps, Map.of(ingredientId, 2.0));
        String jsonRecipe = objectMapper.writeValueAsString(request);


        when(ingredientManagementProxy.getIngredientById(ingredientId))
                .thenReturn(ingredientResponseEntity);

        mockMvc.perform(post(ApiPath.RECIPE.CREATE_V1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRecipe))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(recipeName))
                .andExpect(jsonPath("$.description").value(recipeDescription))
                .andExpect(jsonPath("$.mealType").value(mealType.toString()))
                .andExpect(jsonPath("$.picturesLinks", hasSize(2)))
                .andExpect(jsonPath("$.picturesLinks", hasItem(pictureLinks.get(0))))
                .andExpect(jsonPath("$.picturesLinks", hasItem(pictureLinks.get(1))))
                .andExpect(jsonPath("$.recipeSteps", hasSize(2)))
                .andExpect(jsonPath("$.recipeSteps", hasItem(steps.get(0))))
                .andExpect(jsonPath("$.recipeSteps", hasItem(steps.get(1))))
                .andExpect(jsonPath("$.ingredients", hasSize(1)))
                .andExpect(jsonPath("$.ingredients[0].id").value(ingredientId.toString()))
                .andExpect(jsonPath("$.ingredients[0].name").value(ingredientName))
                .andExpect(jsonPath("$.ingredients[0].quantity").value(2.0));
    }

    @Test
    void testPostRecipeWithNotExistingIngredient() throws Exception {
        when(ingredientManagementProxy.getIngredientById(ingredientId))
                .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        request = new RecipeRequestWithoutNewIngredients(recipeName, recipeDescription, mealType, pictureLinks, steps, Map.of(ingredientId, 2.0));
        String jsonRecipe = objectMapper.writeValueAsString(request);

        mockMvc.perform(post(ApiPath.RECIPE.CREATE_V1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRecipe))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(TestStringTemplates.JSON_ERROR).value("Ingredient with id " + ingredientId + " not found."));
    }

}
