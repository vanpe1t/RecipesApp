package ru.makarov.recipesapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.RecipeService;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/test")
    public ResponseEntity<ArrayList<String>> test() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("sdlfjksdf");
        strings.add("sdfsdf");

        return ResponseEntity.ok(strings);
    }

    @PostMapping
    public ResponseEntity<Integer> addRecipe(@RequestBody Recipe recipe) {
        int id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe>  getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Recipe>> getIngredientList() {
        return ResponseEntity.ok(recipeService.getRecipeList());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeService.editRecipe(id, newRecipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            ResponseEntity.ok(true);
        }

        return ResponseEntity.notFound().build();
    }
}
 