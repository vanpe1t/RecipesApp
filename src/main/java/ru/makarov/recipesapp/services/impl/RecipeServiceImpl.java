package ru.makarov.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.RecipeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private int lastId = 0;
    @Override
    public void addRecipe(String name, int coockingTime) {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<String> steps = new ArrayList<>();
        Recipe recipe = new Recipe(name, coockingTime, steps, ingredients);
        recipeMap.put(lastId++, recipe);
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }
}
