package hw05.task1.services;

import hw05.task1.entities.Employee;
import hw05.task1.repositories.EmployeeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Slf4j
@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;

}
