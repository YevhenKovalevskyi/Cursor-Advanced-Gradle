package hw04.task1.database.repositories;

import hw04.task1.database.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YevhenKovalevskyi
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
    @Query("select u from #{#entityName} u where u.email = ?1 and u.password = ?2")
    User findByEmailAndPassword(String email, String password);
}
