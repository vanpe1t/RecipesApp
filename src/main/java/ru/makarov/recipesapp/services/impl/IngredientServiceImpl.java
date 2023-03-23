package ru.makarov.recipesapp.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.model.Ingredient;
import ru.makarov.recipesapp.services.FileService;
import ru.makarov.recipesapp.services.IngredientService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private LinkedHashMap<Integer, Ingredient> recipeIngredients = new LinkedHashMap<>();
    private int lastId = 0;

    private final FileService fileService;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @PostConstruct
    private void init () {
        readFromFile();
    }

    @Override
    public int addIngredient(Ingredient ingredint) {
        recipeIngredients.put(lastId, ingredint);
        saveToFile();
        return lastId++;
    }

    @Override
    public Ingredient getIngredient(int id) {
        return recipeIngredients.get(id);
    }

    @Override
    public LinkedHashMap<Integer, Ingredient> getIIngredientList() {
        return recipeIngredients;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (recipeIngredients.containsKey(id)) {
            recipeIngredients.put(id, ingredient);
            saveToFile();
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


    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeIngredients);
            fileService.SaveToFile(json, "ingredient");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = fileService.ReadFromFile("ingredient");
            if (json != null) {
                recipeIngredients = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Integer, Ingredient>>() {
                });
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
