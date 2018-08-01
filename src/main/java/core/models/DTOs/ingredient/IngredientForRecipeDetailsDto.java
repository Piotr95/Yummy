package core.models.DTOs.ingredient;


import lombok.Data;

@Data
public class IngredientForRecipeDetailsDto {
    private IngredientDetailsDto ingredient;
    private String amount;

    public IngredientForRecipeDetailsDto() {

    }

    public IngredientDetailsDto getIngredient() {
        return ingredient;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setIngredient(IngredientDetailsDto ingredient) {
        this.ingredient = ingredient;
    }

    public IngredientForRecipeDetailsDto(IngredientDetailsDto ingredient, String amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }
}
