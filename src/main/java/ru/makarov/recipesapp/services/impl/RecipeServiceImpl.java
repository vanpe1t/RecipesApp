package ru.makarov.recipesapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.model.Recipe;
import ru.makarov.recipesapp.services.FileService;
import ru.makarov.recipesapp.services.RecipeService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private LinkedHashMap<Integer, Recipe> recipeMap = new LinkedHashMap<>();
    private int lastId = 0;

    private final FileService fileService;

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init () {
        readFromFile();
    }

    @Override
    public int addRecipe(Recipe recipe) {
        recipeMap.put(lastId, recipe);
        saveToFile();
        return lastId++;
    }

    @Override
    public File createAllRecipesFile() throws IOException {
        Path path = fileService.createRecipesFile();
        for (Recipe recipe: recipeMap.values()) {
            try (Writer writer =  Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(recipe.getName());
                writer.append("\n");
                writer.append("Время приготовления :" + recipe.getCookingTime());
                writer.append("\n");
                writer.append("Ингридиенты :");
                writer.append("\n");
                if (recipe.getIngredients() != null) {
                    for (Ingredient ingredient : recipe.getIngredients()) {
                        writer.append(" * " + ingredient.getName() + "-" + ingredient.getWeight() + " " + ingredient.getMeasure());
                        writer.append("\n");
                    }
                }
                writer.append("Инструкция приготовления:");
                writer.append("\n");
                int count = 0;
                if (recipe.getSteps() != null) {
                    for (String step : recipe.getSteps()) {
                        count++;
                        writer.append(count + ". " + step);
                        writer.append("\n");
                    }
                }
            }
        }
        return new File(path.toString());
    }

    @Override
    public LinkedHashMap<Integer, Recipe> getRecipeList() {
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
            saveToFile();
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
         if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }


    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json, "recipe");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.readFromFile("recipe");
            if (json != null) {
                recipeMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Recipe>>() {
                });
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
