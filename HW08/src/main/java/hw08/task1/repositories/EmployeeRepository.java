package hw08.task1.repositories;

import hw08.task1.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
