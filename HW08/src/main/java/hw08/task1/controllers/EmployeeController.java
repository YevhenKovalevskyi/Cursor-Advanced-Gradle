package hw08.task1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.actions.RequestAction;
import hw08.task1.actions.ResponseAction;
import hw08.task1.dto.EmployeeDto;
import hw08.task1.entities.Employee;
import hw08.task1.mappers.EmployeeMapper;
import hw08.task1.services.EmployeeService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;
    private ObjectMapper mapper;
    
    /**
     * Create Employee from FormData
     */
    @PostMapping
    public void create(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = RequestAction.getRequestBody(request, mapper, Employee.class);
        EmployeeDto employeeDto = EmployeeMapper.getForShow(employeeService.save(employee));
    
        ResponseAction.setResponse(response, mapper, HttpStatus.CREATED, employeeDto);
    }
    
    /**
     * Update Employee by ID from FormData
     */
    @PostMapping("/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Integer employeeId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        Employee employee = RequestAction.getRequestBody(request, mapper, Employee.class);
        EmployeeDto employeeDto = EmployeeMapper.getForShow(employeeService.save(employeeId, employee));
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeeDto);
    }
    
    /**
     * Delete Employee by ID
     */
    @DeleteMapping("/{id}")
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Integer employeeId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        
        employeeService.deleteById(employeeId);
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, "Deleted!");
    }
    
    /**
     * Get all Employees
     */
    @GetMapping
    public void getAll(HttpServletResponse response) {
        List<EmployeeDto> employeesDto = employeeService.findAll()
                .stream().map(EmployeeMapper::getForShow).toList();
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeesDto);
    }
    
    /**
     * Get Employee by ID
     */
    @GetMapping("/{id}")
    public void getOne(HttpServletRequest request, HttpServletResponse response) {
        Integer employeeId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        EmployeeDto employeeDto = EmployeeMapper.getForShow(employeeService.findById(employeeId));
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeeDto);
    }
}
