package ru.makarov.recipesapp.services;

import java.io.IOException;
import java.nio.file.Path;

public interface FileService {
    boolean SaveToFile(String jason, String nameOfType);

    String ReadFromFile(String nameOfType);

    void CleanDataFile(Path path);
}
