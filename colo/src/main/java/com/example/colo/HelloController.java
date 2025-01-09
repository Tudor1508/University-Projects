package com.example.colo;

import com.example.colo.Domain.Recipe;
import com.example.colo.Repo.RecipeRepository;
import com.example.colo.Services.RecipeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    private ListView<String> recipeListView;
    @FXML
    private ListView<String> shoppingListView;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextField ingredientsTextField;
    @FXML
    private TextField filterIngredientsTextField;

    private RecipeService service;

    @FXML
    public void initialize() {
        RecipeRepository repository = new RecipeRepository();
        service = new RecipeService(repository);
        loadRecipes(service.getAllRecipes());

        // pentru selectarea mai multor ingrediente(functionalitate 4)
        recipeListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    }

    @FXML
    private void addRecipe() {
        try {
            String name = nameTextField.getText();
            int time = Integer.parseInt(timeTextField.getText());
            String ingredients = ingredientsTextField.getText();

            if (time <= 0) throw new IllegalArgumentException("Cooking time must be positive!");

            Recipe recipe = new Recipe(name, time, ingredients);
            service.addRecipe(recipe);
            loadRecipes(service.getAllRecipes());
        } catch (Exception e) {
            showError("Error adding recipe: " + e.getMessage());
        }
    }

    @FXML
    private void filterRecipes() {
        try {
            String input = filterIngredientsTextField.getText();
            List<String> ingredients = Arrays.asList(input.split(","));
            List<Recipe> filtered = service.filterRecipesByIngredients(ingredients);
            loadRecipes(filtered);
        } catch (Exception e) {
            showError("Error filtering recipes: " + e.getMessage());
        }
    }

    @FXML
    private void createShoppingList() {
        try {

            ObservableList<String> selectedItems = recipeListView.getSelectionModel().getSelectedItems();

            List<Recipe> selectedRecipes = service.getAllRecipes().stream()
                    .filter(recipe -> selectedItems.contains(recipe.toString()))
                    .collect(Collectors.toList());

            List<String> shoppingList = selectedRecipes.stream()
                    .flatMap(recipe -> Arrays.stream(recipe.getIngredients().split(";")))
                    .map(String::trim)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            ObservableList<String> shoppingItems = FXCollections.observableArrayList(shoppingList);
            shoppingListView.setItems(shoppingItems);
        } catch (Exception e) {
            showError("Error creating shopping list: " + e.getMessage());
        }
    }

    private void loadRecipes(List<Recipe> recipes) {
        ObservableList<String> recipeList = FXCollections.observableArrayList();
        for (Recipe recipe : recipes) {
            recipeList.add(recipe.toString());
        }
        recipeListView.setItems(recipeList);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}
