package com.example.colo.Repo;

import com.example.colo.Domain.Recipe;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private final String DB_URL = "jdbc:sqlite:src/main/resources/recipes.db";
    private Connection connection;

    public RecipeRepository() {
        openConnection();
        createTable();
        initializeData();
    }

    private void openConnection() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(DB_URL);
        try {
            if (connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database: " + e.getMessage());
        }
    }

    private void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS recipes (
                    name TEXT PRIMARY KEY,
                    time INTEGER,
                    ingredients TEXT
                );
                """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating table: " + e.getMessage());
        }
    }

    private void initializeData() {
        if (getAllRecipes().isEmpty()) {
            addRecipe(new Recipe("Chiftelute de linte", 50, "linte;ulei de masline;salota;usturoi;oua;condimente"));
            addRecipe(new Recipe("Pizza", 45, "faina;drojdie;apa;ulei de masline;sare;sos de rosii;mozzarella"));
            addRecipe(new Recipe("Tarta Lorraine", 60, "oua;faina;apa;smantana;lapte;Emmental"));
            addRecipe(new Recipe("Quesadilla", 35, "tortilla;carne de pui;porumb;sos;cascaval;condimente"));
            addRecipe(new Recipe("Supa de legume", 40, "mix de legume;usturoi;telina;ceapa;apa;rosii"));
        }
    }

    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes ORDER BY name ASC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                recipes.add(new Recipe(
                        rs.getString("name"),
                        rs.getInt("time"),
                        rs.getString("ingredients")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving recipes: " + e.getMessage());
        }
        return recipes;
    }

    public void addRecipe(Recipe recipe) {
        String sql = "INSERT INTO recipes (name, time, ingredients) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, recipe.getName());
            stmt.setInt(2, recipe.getTime());
            stmt.setString(3, recipe.getIngredients());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding recipe: " + e.getMessage());
        }
    }
}
