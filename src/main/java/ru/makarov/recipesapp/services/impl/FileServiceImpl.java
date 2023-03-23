package ru.makarov.recipesapp.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileServiceImpl implements FileService {

    @Value("${path.to.data.file}")
    private String dataFilePath;

    @Value("${name.of.data.file.recipe}")
    private String recipeFileName;

    @Value("${name.of.data.file.ingredient}")
    private String ingredientFileName;

    private Path getPath(String nameOfType) {
        Path path;
        if (nameOfType.equals("recipe")) {
            path = Path.of(dataFilePath, recipeFileName);
        } else if (nameOfType.equals("ingredient")) {
            path = Path.of(dataFilePath, ingredientFileName);
        } else {
            return null;
        }
        return path;
    }

    @Override
    public boolean SaveToFile(String jason, String nameOfType) {

        Path path =  getPath(nameOfType);
        if (path == null) {
            return false;
        }

        CleanDataFile(path);

        try {
            Files.writeString(path, jason);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String ReadFromFile(String nameOfType) {

        Path path =  getPath(nameOfType);
        if (path == null) {
            return null;
        }

        try {
            return Files.readString(path);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void CleanDataFile(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
