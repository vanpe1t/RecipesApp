package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Ingredient;

import java.util.Map;

public interface IngredientService {

    int addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);
    public Map<Integer, Ingredient> getIIngredientList();

}
