package hw08.task1.services;

import hw08.task1.entities.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface EmployeeService {
    
    Employee save(HttpServletRequest request);
    Employee save(Integer id, HttpServletRequest request);
    void deleteById(Integer id);
    List<Employee> findAll();
    Employee findById(Integer id);
}
