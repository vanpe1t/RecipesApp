package ru.makarov.recipesapp.services.impl;

import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.services.IngredientService;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private Map<Integer, Ingredient> recipeIngredients = new HashMap<>();
    private int lastId = 0;

    @Override
    public int addIngredient(Ingredient ingredint) {
        recipeIngredients.put(lastId, ingredint);
        return lastId++;
    }

    @Override
    public Ingredient getIngredient(int id) {
        return recipeIngredients.get(id);
    }

    @Override
    public Map<Integer, Ingredient> getIIngredientList() {
        return recipeIngredients;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (recipeIngredients.containsKey(id)) {
            recipeIngredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (recipeIngredients.containsKey(id)) {
            recipeIngredients.remove(id);
            return true;
        }
        return false;
    }


}
