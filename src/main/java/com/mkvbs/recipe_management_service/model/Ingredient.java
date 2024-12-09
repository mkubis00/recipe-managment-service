package com.mkvbs.recipe_management_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Ingredient {

    private final UUID id;
    private final String name;
    @Setter
    private double quantity;
    private final TypeOfQuantity typeOfQuantity;
    private final Allergen allergen;
}
