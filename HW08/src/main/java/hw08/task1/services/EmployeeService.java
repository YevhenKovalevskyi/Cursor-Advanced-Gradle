package hw08.task1.services;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.EmployeeEditDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface EmployeeService {
    
    EmployeeDto create(EmployeeEditDto employeeToCreate);
    EmployeeDto update(Integer id, EmployeeEditDto employeeToUpdate);
    void deleteById(Integer id);
    List<EmployeeDto> findAll();
    EmployeeDto findById(Integer id);
}
