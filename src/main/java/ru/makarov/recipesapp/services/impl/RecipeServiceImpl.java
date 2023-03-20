package ru.makarov.recipesapp.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.IngredientService;
import ru.makarov.recipesapp.services.RecipeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Integer, Recipe> recipeMap = new HashMap<>();
    private int lastId = 0;



    @Override
    public int addRecipe(Recipe recipe) {
        recipeMap.put(lastId, recipe);
        IngredientService ingredientService = new IngredientServiceImpl();
        for (Ingredient ingredient : recipe.getIngredients()) {
            ingredient.setName(StringUtils.trim(ingredient.getName()));
            ingredientService.addIngredient(ingredient);
        }
        return lastId++;
    }

    @Override
    public Map<Integer, Recipe> getRecipeList() {
        return recipeMap;
    }
    @Override
    public Recipe getRecipe(int id) {
        return recipeMap.get(id);
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
         if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;
    }
}
