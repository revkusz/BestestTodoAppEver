package hu.elte.BestestTodoAppEver.repositories;

import hu.elte.BestestTodoAppEver.entities.TodoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {

    @Query("select f from TodoItem f where f.owner.username = ?1 ")
    List<TodoItem> findAllForUser(String user);

    @Query("select f from TodoItem f where f.owner.username = ?1 and f.category= ?2 ")
    List<TodoItem> findAllForUserbyCategory(String user, String category);

    @Query("select f from TodoItem f where f.owner.username = ?1 and f.id = ?2 ")
    TodoItem findByidforUser(String user , Long id);
}
