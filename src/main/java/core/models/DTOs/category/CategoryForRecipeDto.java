package core.models.DTOs.category;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryForRecipeDto {
    @NotNull
    private Long id;
}
