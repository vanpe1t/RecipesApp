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
import ru.makarov.recipesapp.services.IngredientService;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name =  "Ингридиенты", description = "Операции по работе с ингридиентами.")
public class IngredientsController {

    private final IngredientService ingredientService;

    public IngredientsController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление ингридента.",
            description = "Передаём ингридеиент и он добаляется в карту"
    )
    @Parameters({
            @Parameter(
                    name = "Ингридиент",
                    schema = @Schema(implementation = Ingredient.class),
                    description = "Описание ингридиента в формате Json"
            )
    }
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Ингридиент добавлен.",
                content = {
                        @Content(
                                mediaType = "integer"
                        )
                }
        )
    }
    )
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) {
        int id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(id);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингридиента.",
            description = "Передаём id ингридиента и получаем его представление"
    )
    @Parameters({
            @Parameter(
                    name = "ИД",
                    description = "ИД ингридиента"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент получет.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            )
    }
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @GetMapping
    @Operation(
            summary = "Получаем список ингридиентов.",
            description = "Получаем список всех ингридиентов, без параметров и отборов"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список получен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    }
    )
    public ResponseEntity<Map<Integer, Ingredient>> getIngredientList() {
        return ResponseEntity.ok(ingredientService.getIIngredientList());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактируем ингридиент.",
            description = "Передаём ИД ингридиента, и его новое описание, для внесения изменений"
    )
    @Parameters({
            @Parameter(
                    name = "ИД",
                    description = "ИД ингридиента"
            ),
            @Parameter(
                    name = "Рецепт",
                    schema = @Schema(implementation = Ingredient.class),
                    description = "Описание ингридиента в формате Json"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент отредактирован",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            )
    }
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient newIngredient) {
        Ingredient ingredient = ingredientService.editIngredient(id, newIngredient);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаляем ингридиент.",
            description = "Передаём ИД ингридиента и удаляем его из карты"
    )
    @Parameters({
            @Parameter(
                    name = "ИД",
                    description = "ИД ингридиента"
            )
    }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиент удален"
            )
    }
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            ResponseEntity.ok(true);
        }

        return ResponseEntity.notFound().build();
    }

}
