package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;

import java.util.Map;

public interface RecipeService {

    int addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    public Map<Integer, Recipe> getRecipeList();
}
