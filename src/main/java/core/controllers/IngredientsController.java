package core.controllers;

import core.models.DTOs.ingredient.IngredientCreationDto;
import core.models.DTOs.ingredient.IngredientDetailsDto;
import core.models.DTOs.ingredient.IngredientListDto;
import core.models.DTOs.ingredient.IngredientUpdateDto;
import core.models.converters.IngredientDtoConverter;
import core.services.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ingredients")
public class IngredientsController {
    private IngredientsService ingredientsService;
    private IngredientDtoConverter ingredientDtoConverter;

    @Autowired
    public IngredientsController(IngredientsService ingredientsService, IngredientDtoConverter ingredientDtoConverter) {
        this.ingredientsService = ingredientsService;
        this.ingredientDtoConverter = ingredientDtoConverter;
    }

    @GetMapping
    public List<IngredientListDto> getAllIngrdiants() {
        return ingredientsService.getAllIngredients().stream()
                .map(ingredientDtoConverter::toList).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public IngredientDetailsDto getById(@PathVariable("id") Long id) {

            return ingredientDtoConverter.toDetails(ingredientsService.get(id));
    }

    @PostMapping
    public IngredientDetailsDto create(@RequestBody IngredientCreationDto ingredient) {
        return ingredientDtoConverter.toDetails(ingredientsService.create(ingredientDtoConverter.fromCreation(ingredient)));
    }

    @PutMapping
    public IngredientDetailsDto update(@RequestBody IngredientUpdateDto ingredient) {
        return ingredientDtoConverter.toDetails(ingredientsService.update(ingredientDtoConverter.fromUpdate(ingredient)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        ingredientsService.delete(id);

        return ResponseEntity.ok().build();
    }

}
