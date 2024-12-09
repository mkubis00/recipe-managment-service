package com.mkvbs.recipe_management_service.model.api.recipe.response;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class RecipeSummaryResponse extends RecipeResponse {
    public RecipeSummaryResponse(UUID id, String name, String description, List<String> picturesLinks) {
        super(id, name, description, picturesLinks);
    }
}