package hu.elte.BestestTodoAppEver.repositories;

import hu.elte.BestestTodoAppEver.entities.Category;
import hu.elte.BestestTodoAppEver.entities.TodoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("select f from Category f where f.owner.username = ?1 or f.showAll = true ")
    List<Category> findAllForUser(String user);
}
