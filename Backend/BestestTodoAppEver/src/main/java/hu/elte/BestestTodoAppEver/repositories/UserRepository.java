package hu.elte.BestestTodoAppEver.repositories;

import hu.elte.BestestTodoAppEver.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}

