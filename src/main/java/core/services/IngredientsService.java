package core.services;

import com.google.common.collect.Lists;
import core.exeptions.ResourceNotFoundException;
import core.models.entities.Ingredient;
import core.repositories.IngredientsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientsService {
    private IngredientsRepository ingredientsRepository;

    @Autowired
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<Ingredient> getAllIngredients() {
   return Lists.newArrayList(ingredientsRepository.findAll());
    }

    public Ingredient get(Long id) {
        return ingredientsRepository.findById(id).get();


    }

    public Ingredient create(@Valid Ingredient ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    public Ingredient update(@Valid Ingredient ingredient) {
        if (ingredientsRepository.existsById(ingredient.getId())) {
            return ingredientsRepository.save(ingredient);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public void delete(Long id) {
        if (ingredientsRepository.existsById(id)) {
            ingredientsRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }
}




