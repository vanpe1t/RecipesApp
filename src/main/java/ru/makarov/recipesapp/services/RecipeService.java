package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Recipe;

public interface RecipeService {

    void addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

}
