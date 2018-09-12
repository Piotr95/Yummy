package core.repositories;

import core.models.entities.IngredientAmountPerRecipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientAmountPerRecipeRepository extends CrudRepository<IngredientAmountPerRecipe, Long> {
}
