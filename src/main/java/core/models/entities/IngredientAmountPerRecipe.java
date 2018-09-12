package core.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientAmountPerRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Ingredient ingredient;

    @Column(name = "amount", nullable = false)
    private String amount;

    public IngredientAmountPerRecipe(Ingredient ingredient, String amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientAmountPerRecipe that = (IngredientAmountPerRecipe) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getIngredient(), that.getIngredient()) &&
                Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIngredient(), getAmount());
    }
}
