package core.models.DTOs.ingredient;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IngredientForRecipeDto {
    @NotNull
    private Long ingredientId;

    @NotNull
    private String amount;
}
