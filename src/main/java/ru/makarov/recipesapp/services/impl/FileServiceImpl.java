package ru.makarov.recipesapp.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.makarov.recipesapp.services.FileService;

import java.io.File;
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

    @Override
    public Path getPath(String nameOfType) {
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
    public Path createRecipesFile() {

        Path path = Path.of(dataFilePath, "recipes");

        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }


    @Override
    public boolean saveToFile(String jason, String nameOfType) {

        Path path =  getPath(nameOfType);
        if (path == null) {
            return false;
        }

        cleanDataFile(path);

        try {
            Files.writeString(path, jason);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile(String nameOfType) {

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
    public void cleanDataFile(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File getFile(String nameOfType) {
        return new File(getPath(nameOfType).toString());
    }

}
