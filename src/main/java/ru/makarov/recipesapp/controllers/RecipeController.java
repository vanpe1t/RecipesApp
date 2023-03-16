package ru.makarov.recipesapp.controllers;

import org.springframework.web.bind.annotation.*;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.IngredientService;
import ru.makarov.recipesapp.services.RecipeService;

@RestController
public class RecipeController {
    private RecipeService recipeService;
    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String GetString() {
        return "Приложение запущено";
    }

    @PutMapping("/recipe/put")
    public void putRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
    }
    @GetMapping("/recipe/get")
    public Recipe getRecipe(@RequestParam int id) {
        return recipeService.getRecipe(id);
    }


}
 