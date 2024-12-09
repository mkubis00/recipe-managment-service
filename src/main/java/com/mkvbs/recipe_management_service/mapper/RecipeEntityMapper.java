package com.mkvbs.recipe_management_service.mapper;

import com.mkvbs.recipe_management_service.model.Ingredient;
import com.mkvbs.recipe_management_service.model.Recipe;
import com.mkvbs.recipe_management_service.model.entity.RecipeEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class RecipeEntityMapper implements Mapper<Recipe, RecipeEntity> {

    @Override
    public RecipeEntity map(Recipe from) {
        Map<UUID, Double> ingredientsMap = ingredintsMap(from.getIngredients());
        return new RecipeEntity(
                from.getId(),
                from.getName(),
                from.getDescription(),
                from.getMealType(),
                from.getPicturesLinks(),
                from.getRecipeSteps(),
                ingredientsMap
        );
    }

    private Map<UUID, Double> ingredintsMap(List<Ingredient> ingredients) {
        return ingredients.stream().collect(Collectors.toMap(Ingredient::getId, Ingredient::getQuantity));
    }
}
