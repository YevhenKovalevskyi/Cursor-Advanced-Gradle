package hw08.task1.services.impl;

import hw08.task1.entities.Employee;
import hw08.task1.exceptions.DataNotFoundException;
import hw08.task1.exceptions.EmployeeNotFoundException;
import hw08.task1.mappers.EmployeeMapper;
import hw08.task1.messages.Messages;
import hw08.task1.repositories.EmployeeRepository;
import hw08.task1.services.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    
    private EmployeeRepository employeeRepository;
    
    public Employee findByIdIfExists(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.EMPLOYEE_NOT_FOUND.getLogMessage(), id);
            throw new EmployeeNotFoundException(
                    String.format(Messages.EMPLOYEE_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Employee save(Employee employee) {
        return employeeRepository.save(
                Employee.build(employee)
        );
    }
    
    public Employee save(Integer id, Employee newEmployee) {
        Employee currEmployee = findByIdIfExists(id);

        return employeeRepository.save(
                EmployeeMapper.getForUpdate(id, currEmployee, newEmployee)
        );
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        employeeRepository.deleteById(id);
    }
    
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll();
    
        if (employees.isEmpty()) {
            log.error(Messages.DATA_NOT_FOUND.getLogMessage());
            throw new DataNotFoundException(Messages.DATA_NOT_FOUND.getOutMessage());
        }
    
        return employees;
    }
    
    public Employee findById(Integer id) {
        return findByIdIfExists(id);
    }
}
