package core.models.DTOs.recipe;

import core.models.DTOs.category.CategoryListDto;
import core.models.DTOs.ingredient.IngredientForRecipeDetailsDto;
import core.models.DTOs.review.ReviewDetailsDto;
import core.models.DTOs.user.UserInfoDto;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RecipeDetailsDto {
    private Long id;

    private UserInfoDto author;

    private String name;

    private String content;

    private String image;

    private Integer totalCalories;

    private Integer portions;

    private Integer preparationTime;

    private Boolean isFavourite;

    private List<CategoryListDto> categories;

    private Set<IngredientForRecipeDetailsDto> ingredients = new HashSet<>();

    private Set<ReviewDetailsDto> reviews = new HashSet<>();
}
