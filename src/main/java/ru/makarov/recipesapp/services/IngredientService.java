package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;



public interface IngredientService {

    void addIngredient(String name, int quantity, String mesure);

    Ingredient getIngredient(int id);

}
