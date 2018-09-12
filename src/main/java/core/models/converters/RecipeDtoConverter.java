package core.models.converters;

import core.models.DTOs.recipe.RecipeCreationDto;
import core.models.DTOs.recipe.RecipeDetailsDto;
import core.models.DTOs.recipe.RecipeListDto;
import core.models.DTOs.recipe.RecipeUpdateDto;
import core.models.entities.Recipe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeDtoConverter implements DtoConverter<Recipe, RecipeListDto, RecipeDetailsDto, RecipeCreationDto, RecipeUpdateDto> {

    private final ModelMapper modelMapper;

    @Autowired
    public RecipeDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Recipe fromCreation(RecipeCreationDto recipeCreationDto) {
        Recipe recipe = modelMapper.map(recipeCreationDto, Recipe.class);
        recipe.getIngredients().forEach(i -> i.setId(null));
        return recipe;
    }

    @Override
    public Recipe fromUpdate(RecipeUpdateDto recipeUpdateDto) {
        Recipe recipe = modelMapper.map(recipeUpdateDto, Recipe.class);
        recipe.getIngredients().forEach(i -> i.setId(null));
        return recipe;
    }

    @Override
    public RecipeListDto toList(Recipe recipe) {
        return modelMapper.map(recipe, RecipeListDto.class);
    }

    @Override
    public RecipeDetailsDto toDetails(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDetailsDto.class);
    }
}
