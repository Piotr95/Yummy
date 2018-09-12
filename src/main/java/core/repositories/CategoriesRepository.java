package core.repositories;

import core.models.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Long> {
    List<Category> findCategoryByChildCategoriesEmpty();
}
