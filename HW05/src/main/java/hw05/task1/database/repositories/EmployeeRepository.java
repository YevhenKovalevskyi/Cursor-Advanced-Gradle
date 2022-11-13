package hw05.task1.database.repositories;

import hw05.task1.database.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author YevhenKovalevskyi
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
