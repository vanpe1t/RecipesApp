package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;



public interface IngredientService {

    void addIngredient(Ingredient ingredint);

    Ingredient getIngredient(int id);

}
