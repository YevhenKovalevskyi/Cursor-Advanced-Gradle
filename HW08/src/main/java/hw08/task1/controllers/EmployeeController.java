package hw08.task1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import hw08.task1.actions.RequestAction;
import hw08.task1.actions.ResponseAction;
import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.EmployeeEditDto;
import hw08.task1.services.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        EmployeeEditDto employeeToCreate = RequestAction.getRequestBody(request, mapper, EmployeeEditDto.class);
        
        EmployeeDto employeeCreated = employeeService.create(employeeToCreate);
        
        ResponseAction.setResponse(response, mapper, HttpStatus.CREATED, employeeCreated);
    }
    
    /**
     * Update Employee by ID from FormData
     */
    @PostMapping("/{id}")
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Integer employeeId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        EmployeeEditDto employeeToUpdate = RequestAction.getRequestBody(request, mapper, EmployeeEditDto.class);
        
        EmployeeDto employeeUpdated = employeeService.update(employeeId, employeeToUpdate);
        
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeeUpdated);
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
        List<EmployeeDto> employeesAll = employeeService.findAll();
    
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeesAll);
    }
    
    /**
     * Get Employee by ID
     */
    @GetMapping("/{id}")
    public void getOne(HttpServletRequest request, HttpServletResponse response) {
        Integer employeeId = Integer.valueOf(RequestAction.getRequestParam(request, "id"));
        
        EmployeeDto employeeById = employeeService.findById(employeeId);
        
        ResponseAction.setResponse(response, mapper, HttpStatus.OK, employeeById);
    }
}
