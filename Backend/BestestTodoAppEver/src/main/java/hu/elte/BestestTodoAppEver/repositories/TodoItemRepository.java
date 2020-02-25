package hu.elte.BestestTodoAppEver.repositories;

import hu.elte.BestestTodoAppEver.entities.TodoItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {
}
