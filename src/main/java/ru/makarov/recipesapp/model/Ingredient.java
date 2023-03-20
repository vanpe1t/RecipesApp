package ru.makarov.recipesapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private final String name;

    private int weight;

    private String measure;

}
