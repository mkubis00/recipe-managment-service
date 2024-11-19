package com.mkvbs.recipe_management_service.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class SmallRecipeResponse {

    private final UUID id;
    private final String name;
    private final String description;
    private final List<String> picturesLinks;
}
