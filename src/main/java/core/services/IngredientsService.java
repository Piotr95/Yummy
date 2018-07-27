package core.services;

import core.models.Ingredient;
import core.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {
    private IngredientRepository ingredientRepository;
    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public Iterable<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }
}
