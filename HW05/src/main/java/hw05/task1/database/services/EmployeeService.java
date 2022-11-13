package hw05.task1.database.services;

import hw05.task1.database.entities.Employee;
import hw05.task1.database.entities.Shop;
import hw05.task1.database.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public void deleteById(Integer id) {
        employeeRepository.deleteById(id);
    }
    
    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }
    
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
