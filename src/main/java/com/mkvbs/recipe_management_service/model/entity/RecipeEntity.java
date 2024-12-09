package com.mkvbs.recipe_management_service.model.entity;

import com.mkvbs.recipe_management_service.model.MealType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "recipe")
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RecipeEntity {

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
}
