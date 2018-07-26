package core.controllers;

import core.models.Ingredient;
import core.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class IngredientController {
    private IngredientService ingredientService;
    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @GetMapping("/ingredient/all")
    public Iterable<Ingredient> getAllIngrdiants(){
       return ingredientService.getAllIngredients();
    }
}
