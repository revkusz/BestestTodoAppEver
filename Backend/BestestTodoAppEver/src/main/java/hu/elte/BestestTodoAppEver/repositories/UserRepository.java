package hu.elte.BestestTodoAppEver.repositories;

import hu.elte.BestestTodoAppEver.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, String> {

}

