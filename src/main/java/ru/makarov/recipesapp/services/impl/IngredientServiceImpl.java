package ru.makarov.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.IngredientService;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private Map<Integer, Ingredient> recipeIngredient = new HashMap<>();
    private int lastId = 0;

    @Override
    public void addIngredient(String name, int quantity, String mesure) {
        Ingredient ingredient = new Ingredient(name, quantity, mesure);
        recipeIngredient.put(lastId++, ingredient);
    }

    @Override
    public Ingredient getIngredient(int id) {
        return recipeIngredient.get(id);
    }
}
