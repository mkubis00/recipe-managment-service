package com.mkvbs.recipe_management_service.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Ingredient {

    private final UUID id;
    private final String name;
    private final double quantity;
    private final TypeOfQuantity typeOfQuantity;
    private final Allergen allergen;
}
