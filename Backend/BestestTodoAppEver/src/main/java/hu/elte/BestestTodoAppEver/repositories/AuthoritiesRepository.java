package hu.elte.BestestTodoAppEver.repositories;

import hu.elte.BestestTodoAppEver.entities.Authorities;
import hu.elte.BestestTodoAppEver.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

    @Query("select DISTINCT  f.authority from Authorities f")
    List<String> findAllRole();

    @Query("select DISTINCT  f.authority from Authorities f where f.username.username = ?1")
    List<String> findAllUserRole(String userId);
}
