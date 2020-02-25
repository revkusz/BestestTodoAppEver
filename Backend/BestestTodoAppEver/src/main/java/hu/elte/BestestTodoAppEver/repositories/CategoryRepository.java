package hu.elte.BestestTodoAppEver.repositories;

import hu.elte.BestestTodoAppEver.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
