package ru.makarov.recipesapp.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface FileService {
    Path getPath(String nameOfType);

    Path createRecipesFile();

    boolean saveToFile(String jason, String nameOfType);

    String readFromFile(String nameOfType);

    void cleanDataFile(Path path);

    File getFile(String nameOfType);
}
