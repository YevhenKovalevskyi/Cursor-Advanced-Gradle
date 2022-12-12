package hw08.task1.services.impl;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.EmployeeEditDto;
import hw08.task1.entities.Employee;
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
    private EmployeeMapper employeeMapper;
    
    public Employee findByIdIfExists(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.EMPLOYEE_NOT_FOUND.getLogMessage(), id);
            throw new EmployeeNotFoundException(
                    String.format(Messages.EMPLOYEE_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public EmployeeDto create(EmployeeEditDto employeeToCreate) {
        Employee employeeCreated = employeeRepository.save(
                employeeMapper.toCreateEntity(employeeToCreate)
        );
        
        return employeeMapper.toDto(employeeCreated);
    }
    
    public EmployeeDto update(Integer id, EmployeeEditDto employeeToUpdate) {
        Employee employeeCurrent = findByIdIfExists(id);
        Employee employeeUpdated = employeeRepository.save(
                employeeMapper.toUpdateEntity(employeeCurrent, employeeToUpdate)
        );
        
        return employeeMapper.toDto(employeeUpdated);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        employeeRepository.deleteById(id);
    }
    
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> employeeMapper.toDto(employee))
                .toList();
    }
    
    public EmployeeDto findById(Integer id) {
        return employeeMapper.toDto(findByIdIfExists(id));
    }
}
