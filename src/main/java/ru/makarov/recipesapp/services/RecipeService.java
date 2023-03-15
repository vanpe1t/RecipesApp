package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Recipe;

public interface RecipeService {

    void addRecipe(String name, int coockingTime);

    Recipe getRecipe(int id);

}
