package hw08.task1.services;

import hw08.task1.entities.Employee;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface EmployeeService {
    
    Employee save(Employee employee);
    Employee save(Integer id, Employee employee);
    void deleteById(Integer id);
    List<Employee> findAll();
    Employee findById(Integer id);
}
