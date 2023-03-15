package ru.makarov.recipesapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.IngredientService;
import ru.makarov.recipesapp.services.RecipeService;

@RestController
public class MainController {

    private RecipeService recipeService;

    private IngredientService ingredientService;

    public MainController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/")
    public String GetString() {
        return "Приложение запущено";
    }

    @GetMapping ("/recipe/put")
    public void PutRecipe(@RequestParam String name, @RequestParam int cookingTime) {
        recipeService.addRecipe(name, cookingTime);
    }
    @GetMapping("/recipe/get")
    public Recipe GetRecipe(@RequestParam int id) {
        return recipeService.getRecipe(id);
    }

    @GetMapping ("/ingredient/put")
    public void PutIngredient(@RequestParam String name, @RequestParam int quantity, @RequestParam String mesure) {
        ingredientService.addIngredient(name, quantity, mesure);
    }
    @GetMapping("/ingredient/get")
    public Ingredient GetIngredient(@RequestParam int id) {
        return ingredientService.getIngredient(id);
    }

}
 