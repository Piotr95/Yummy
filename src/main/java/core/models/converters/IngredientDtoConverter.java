package core.models.converters;



import core.models.DTOs.ingredient.IngredientCreationDto;
import core.models.DTOs.ingredient.IngredientDetailsDto;
import core.models.DTOs.ingredient.IngredientListDto;
import core.models.DTOs.ingredient.IngredientUpdateDto;
import core.models.entities.Ingredient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class IngredientDtoConverter implements DtoConverter<Ingredient, IngredientListDto, IngredientDetailsDto, IngredientCreationDto, IngredientUpdateDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public IngredientDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Ingredient fromCreation(IngredientCreationDto ingredientCreationDto) {
        return modelMapper.map(ingredientCreationDto, Ingredient.class);
    }

    @Override
    public Ingredient fromUpdate(IngredientUpdateDto ingredientUpdateDto) {
        return modelMapper.map(ingredientUpdateDto, Ingredient.class);
    }

    @Override
    public IngredientListDto toList(Ingredient ingredient) {
        return modelMapper.map(ingredient, IngredientListDto.class);
    }

    @Override
    public IngredientDetailsDto toDetails(Ingredient ingredient) {
        return modelMapper.map(ingredient, IngredientDetailsDto.class);
    }

}
