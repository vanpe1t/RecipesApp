package ru.makarov.recipesapp.controllers;

import org.springframework.web.bind.annotation.*;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.services.IngredientService;

@RestController
public class IngredientsController {

    private IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping ("/ingredient/put")
    public void putIngredient(@RequestBody Ingredient ingredint) {
        ingredientService.addIngredient(ingredint);
    }
    @GetMapping("/ingredient/get")
    public Ingredient getIngredient(@RequestParam int id) {
        return ingredientService.getIngredient(id);
    }


}
