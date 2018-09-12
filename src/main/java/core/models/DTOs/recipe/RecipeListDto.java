package core.models.DTOs.recipe;

import lombok.Data;

@Data
public class RecipeListDto {
    private Long id;

    private String name;

    private String image;

    private Integer totalCalories;

    private Integer portions;

    private Integer preparationTime;

    private Double averageRating;
}
