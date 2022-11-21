package hw08.task1.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.entities.Employee;
import hw08.task1.exceptions.EmployeeNotFoundException;
import hw08.task1.exceptions.RequestAlreadyTakenException;
import hw08.task1.mappers.EmployeeMapper;
import hw08.task1.messages.Messages;
import hw08.task1.repositories.EmployeeRepository;
import hw08.task1.services.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    
    private EmployeeRepository employeeRepository;
    private ObjectMapper mapper;
    
    public Employee getEntityIfExists(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.EMPLOYEE_NOT_FOUND.getLogMessage(), id);
            throw new EmployeeNotFoundException(
                    String.format(Messages.EMPLOYEE_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    private Employee getDataFromRequest(HttpServletRequest request) {
        try {
            return mapper.readValue(request.getInputStream(), Employee.class);
        } catch (IOException e) {
            log.error(Messages.REQUEST_ALREADY_TAKEN.getLogMessage());
            throw new RequestAlreadyTakenException(Messages.REQUEST_ALREADY_TAKEN.getOutMessage());
        }
    }
    
    public Employee save(HttpServletRequest request) {
        return employeeRepository.save(
                Employee.build(getDataFromRequest(request))
        );
    }
    
    public Employee save(Integer id, HttpServletRequest request) {
        Employee currEmployee = getEntityIfExists(id);
        Employee newEmployee = getDataFromRequest(request);

        return employeeRepository.save(
                EmployeeMapper.getForUpdate(id, currEmployee, newEmployee)
        );
    }
    
    public void deleteById(Integer id) {
        getEntityIfExists(id);
        employeeRepository.deleteById(id);
    }
    
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    
    public Employee findById(Integer id) {
        return getEntityIfExists(id);
    }
}
