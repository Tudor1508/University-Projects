package com.example.colo.Domain;

public class Recipe {
    private String name;
    private int time;
    private String ingredients;

    public Recipe(String name, int time, String ingredients) {
        this.name = name;
        this.time = time;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public String getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Time: %d min, Ingredients: %s", name, time, ingredients);
    }
}
