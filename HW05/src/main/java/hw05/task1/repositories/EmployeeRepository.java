package hw05.task1.repositories;

import hw05.task1.entities.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
