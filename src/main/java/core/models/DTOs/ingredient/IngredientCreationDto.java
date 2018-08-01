package core.models.DTOs.ingredient;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class IngredientCreationDto {
    @NotNull(message = "Name cannot be empty!")
    @Size(message = "Name should be between 2 and 100 characters long.", min = 2, max = 100)
    private String ingredientName;

}
