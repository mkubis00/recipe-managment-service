package com.mkvbs.recipe_management_service.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "recipe")
@Data
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id;
    private final String name;
    private final String description;
    private final MealType mealType;
    @ElementCollection
    private final List<String> picturesLinks;
    @ElementCollection
    private final List<String> recipeSteps;
    @ElementCollection
    private final Map<UUID, Double> ingredientsUuidAndQuantity;
    @Transient
    private List<Ingredient> ingredients;
}
