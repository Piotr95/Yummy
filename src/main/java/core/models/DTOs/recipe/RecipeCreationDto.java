package core.models.DTOs.recipe;

import core.models.DTOs.category.CategoryForRecipeDto;
import core.models.DTOs.ingredient.IngredientForRecipeDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class RecipeCreationDto {
    @NotNull
    private String name;
    @NotNull
    private String content;
    @NotNull
    private String image;
    @NotNull
    private Integer totalCalories;
    @NotNull
    private Integer portions;
    @NotNull
    private Integer preparationTime;
    @NotEmpty
    private List<CategoryForRecipeDto> categories;
    @NotEmpty
    private Set<IngredientForRecipeDto> ingredients;
}
