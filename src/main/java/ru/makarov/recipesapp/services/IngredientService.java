package ru.makarov.recipesapp.services;

import ru.makarov.recipesapp.model.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;

public interface IngredientService {

    int addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);
    public LinkedHashMap<Integer, Ingredient> getIIngredientList();

}
