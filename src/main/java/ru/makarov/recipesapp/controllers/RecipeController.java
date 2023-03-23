package ru.makarov.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.FileService;
import ru.makarov.recipesapp.services.RecipeService;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Tag(name =  "Рецепты", description = "Операции по работе с рецептами.")
public class RecipeController {
    private final RecipeService recipeService;

    private final FileService fileService;

    public RecipeController(RecipeService recipeService, FileService fileService) {
        this.recipeService = recipeService;
        this.fileService = fileService;
    }

    @GetMapping("/test")
    @Operation(
            summary = "Тест для проверок.",
            description = "Тестовый endpoint для всякого"
    )
    public ResponseEntity<ArrayList<String>> test() {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("sdlfjksdf");
        strings.add("sdfsdf");

        return ResponseEntity.ok(strings);
    }
    @PostMapping
    @Operation(
            summary = "Добавление рецепта.",
            description = "Передаём рецепт и он добаляется в карту"
    )
    @Parameters({
            @Parameter(
                    name = "Рецепт",
                    schema = @Schema(implementation = Recipe.class),
                    description = "Описание рецепта в формате Json"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт добавлен.",
                    content = {
                            @Content(
                                    mediaType = "integer"
                            )
                    }
            )
    }
    )
    public ResponseEntity<Integer> addRecipe(@RequestBody Recipe recipe) {
        int id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение рецепта.",
            description = "Передаём id рецепта и получаем его представление"
    )
    @Parameters({
            @Parameter(
                    name = "ИД",
                    description = "ИД рецепта"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт получет.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )
                    }
            )
    }
    )
    public ResponseEntity<Recipe>  getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    @Operation(
            summary = "Получаем список рецептов.",
            description = "Получаем список всех рецептов, без параметров и отборов"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    }
    )
    public ResponseEntity<Map<Integer, Recipe>> getIngredientList() {
        return ResponseEntity.ok(recipeService.getRecipeList());
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Редактируем рецепт.",
            description = "Передаём ИД рецепта, и его новое описание, для внесения изменений"
    )
    @Parameters({
            @Parameter(
                    name = "ИД",
                    description = "ИД рецепта"
            ),
            @Parameter(
                    name = "Рецепт",
                    schema = @Schema(implementation = Recipe.class),
                    description = "Описание рецепта в формате Json"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт отредактирован",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )
                    }
            )
    }
    )
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe newRecipe) {
        Recipe recipe = recipeService.editRecipe(id, newRecipe);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаляем рецепт.",
            description = "Передаём ИД рецепта и удаляем его из карты"
    )
    @Parameters({
            @Parameter(
                    name = "ИД",
                    description = "ИД рецепта"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален"
            )
    }
    )
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            ResponseEntity.ok(true);
        }

        return ResponseEntity.notFound().build();
    }

}
 