package com.mkvbs.recipe_management_service.model.api;

import com.mkvbs.recipe_management_service.model.Recipe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SmallRecipeResponse {

    private final UUID id;
    private final String name;
    private final String description;
    private final List<String> picturesLinks;

    public static SmallRecipeResponse convertFromRecipe(Recipe recipe) {
        return SmallRecipeResponse.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .picturesLinks(recipe.getPicturesLinks())
                .build();
    }
}
