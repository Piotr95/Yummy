package core.repositories;

import core.models.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends CrudRepository<Ingredient, Long> {
}
