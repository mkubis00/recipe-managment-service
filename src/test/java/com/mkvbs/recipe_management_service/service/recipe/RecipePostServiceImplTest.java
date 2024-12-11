package com.mkvbs.recipe_management_service.service.recipe;

import com.mkvbs.recipe_management_service.factory.RecipeFactory;
import com.mkvbs.recipe_management_service.mapper.IngredientsResponseMapper;
import com.mkvbs.recipe_management_service.mapper.Mapper;
import com.mkvbs.recipe_management_service.mapper.RecipeDetailResponseMapper;
import com.mkvbs.recipe_management_service.mapper.RecipeEntityMapper;
import com.mkvbs.recipe_management_service.model.Allergen;
import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.MealType;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.TypeOfQuantity;
import com.mkvbs.recipe_management_service.model.api.ingredient.IngredientResponse;
import com.mkvbs.recipe_management_service.model.api.recipe.request.RecipeRequestWithoutNewIngredients;
import com.mkvbs.recipe_management_service.model.api.recipe.response.RecipeDetailResponse;
import com.mkvbs.recipe_management_service.model.entity.RecipeEntity;
import com.mkvbs.recipe_management_service.proxy.IngredientManagementProxy;
import com.mkvbs.recipe_management_service.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipePostServiceImplTest {

    private RecipePostServiceImpl recipePostService;
    private RecipeRequestWithoutNewIngredients recipeRequest;

    private RecipeRepository recipeRepository;
    private RecipeFactory recipeFactory;
    private Mapper<Recipe, RecipeEntity> recipeEntityMapper;
    private Mapper<Recipe, RecipeDetailResponse> recipeResponseMapper;
    private IngredientManagementProxy ingredientProxy;
    Mapper<List<Ingredient>, List<IngredientResponse>> ingredientResponseMapper;

    private UUID firstIngredientId;
    private UUID secondIngredientId;
    private final String firstIngredientName = "firstIngredientName";
    private final String secondIngredientName = "secondIngredientName";
    private final TypeOfQuantity typeOfQuantity = TypeOfQuantity.NONE;
    private final Allergen allergen = Allergen.NONE;
    Ingredient firstIngredient;
    Ingredient secondIngredient;

    RecipeRequestWithoutNewIngredients request;
    private UUID recipeId;
    private final String recipeName = "recipeName";
    private final String recipeDescription = "recipeDescription";
    private final MealType mealType = MealType.DESSERT;
    private final List<String> pictureLinks = List.of("link1", "link2");
    private final List<String> steps = List.of("step1", "step2");

    private RecipeEntity recipeEntity;

    @BeforeEach
    void setUp() {
        recipeRepository = mock(RecipeRepository.class);
        recipeFactory = new RecipeFactory();
        recipeEntityMapper = new RecipeEntityMapper();
        ingredientResponseMapper = new IngredientsResponseMapper();
        recipeResponseMapper = new RecipeDetailResponseMapper(ingredientResponseMapper);
        ingredientProxy = mock(IngredientManagementProxy.class);

        recipePostService = new RecipePostServiceImpl(recipeRepository, recipeFactory, recipeEntityMapper, recipeResponseMapper, ingredientProxy);

        createIngredients();

        recipeId = UUID.randomUUID();
        Map<UUID, Double> recipeIngredients = Map.of(firstIngredientId, 2.0, secondIngredientId, 3.5);
        request = new RecipeRequestWithoutNewIngredients(recipeName, recipeDescription, mealType, pictureLinks, steps, recipeIngredients);
        recipeEntity = new RecipeEntity(recipeId, recipeName, recipeDescription, mealType, pictureLinks, steps, recipeIngredients);

        ResponseEntity<Ingredient> firstResponseEntity = mock(ResponseEntity.class);
        ResponseEntity<Ingredient> secondResponseEntity = mock(ResponseEntity.class);

        when(ingredientProxy.getIngredientById(firstIngredientId)).thenReturn(firstResponseEntity);
        when(ingredientProxy.getIngredientById(secondIngredientId)).thenReturn(secondResponseEntity);

        when(firstResponseEntity.getBody()).thenReturn(firstIngredient);
        when(secondResponseEntity.getBody()).thenReturn(secondIngredient);
        when(firstResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(secondResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(recipeRepository.save(any())).thenReturn(recipeEntity);
    }

    private void createIngredients() {
        firstIngredientId = UUID.randomUUID();
        secondIngredientId = UUID.randomUUID();

        firstIngredient = new Ingredient(firstIngredientId, firstIngredientName, 0.0, typeOfQuantity, allergen);
        secondIngredient = new Ingredient(secondIngredientId, secondIngredientName, 0.0, typeOfQuantity, allergen);
    }

    @Test
    void testSaveRecipe() {
        RecipeDetailResponse recipeResponse = recipePostService.saveRecipe(request);

        assertThat(recipeResponse.getId()).isEqualTo(recipeId);
        assertThat(recipeResponse.getName()).isEqualTo(recipeName);
        assertThat(recipeResponse.getDescription()).isEqualTo(recipeDescription);
        assertThat(recipeResponse.getIngredients()).size().isEqualTo(2);

        List<IngredientResponse> ingredients = recipeResponse.getIngredients();

        assertThat(ingredients).extracting(IngredientResponse::getName).contains(firstIngredientName, secondIngredientName);
        assertThat(ingredients).extracting(IngredientResponse::getId).contains(firstIngredientId, secondIngredientId);
    }
}