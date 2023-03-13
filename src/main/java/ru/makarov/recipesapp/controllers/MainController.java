package ru.makarov.recipesapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String GetString() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String GetInfo() {
        return "Makarov Maxim; " + "Project name: Recipes application; " + "Creation date: 01-03-2023; " +
                "Description: приложение по работе с рецептами."   ;
    }

}
