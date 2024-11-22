package com.mkvbs.recipe_management_service.service;

import com.mkvbs.recipe_management_service.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecipeGetService {

    private final RecipeRepository repository;
}
