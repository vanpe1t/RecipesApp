package ru.makarov.recipesapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String name;

    private int cookingTime;

    private ArrayList<String> steps;

    private ArrayList<Ingredient> ingredients;


}
