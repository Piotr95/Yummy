package core.controllers;

import core.models.entities.Ingredient;
import core.services.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ingredients")
public class IngredientsController {
    private IngredientsService ingredientsService;

    @Autowired
    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public Iterable<Ingredient> getAllIngrdiants() {
        return ingredientsService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public Ingredient getById(@PathVariable("id") Long id) {
        return ingredientsService.findById(id);
    }

    @PostMapping
    public Ingredient create(@Valid @RequestBody Ingredient ingredient) {
        return ingredientsService.create(ingredient);
    }

    @PutMapping
    public Ingredient update(@Valid @RequestBody Ingredient ingredient) {
        return ingredientsService.update(ingredient);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        ingredientsService.delete(id);

        return ResponseEntity.ok().build();
    }

}
