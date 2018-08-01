package core.models.DTOs.ingredient;

import lombok.Data;

@Data
public class IngredientDetailsDto {
    private Long id;

    private String ingredientName;

    private boolean isAnimalProduct;
}
