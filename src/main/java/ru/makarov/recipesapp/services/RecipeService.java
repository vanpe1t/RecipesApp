package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public interface RecipeService {

    int addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    File createAllRecipesFile() throws IOException;

    public LinkedHashMap<Integer, Recipe> getRecipeList();
}
