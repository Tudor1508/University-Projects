package com.example.colo.Services;

import com.example.colo.Domain.Recipe;
import com.example.colo.Repo.RecipeRepository;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> getAllRecipes() {
        return repository.getAllRecipes().stream()
                .sorted(Comparator
                        .comparingInt((Recipe recipe) -> recipe.getIngredients().split(";").length)
                        .thenComparing(Recipe::getName))
                .collect(Collectors.toList());
    }

    public List<Recipe> filterRecipesByIngredients(List<String> ingredients) {
        return repository.getAllRecipes().stream()
                .filter(recipe -> {
                    List<String> recipeIngredients = Arrays.asList(recipe.getIngredients().split(";"));
                    return ingredients.stream()
                            .allMatch(inputIngredient ->
                                    recipeIngredients.stream()
                                            .anyMatch(recipeIngredient ->
                                                    recipeIngredient.trim().equalsIgnoreCase(inputIngredient.trim())));
                })
                .sorted(Comparator
                        .comparingInt((Recipe recipe) -> recipe.getIngredients().split(";").length)
                        .thenComparing(Recipe::getName))
                .collect(Collectors.toList());
    }

    public void addRecipe(Recipe recipe) {
        repository.addRecipe(recipe);
    }
}
