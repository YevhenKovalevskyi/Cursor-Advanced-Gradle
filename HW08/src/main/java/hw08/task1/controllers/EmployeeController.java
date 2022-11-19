package hw08.task1.controllers;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.mappers.EmployeeMapper;
import hw08.task1.services.EmployeeService;
import hw08.task1.utils.HttpServletRequestUtil;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;
    
    /**
     * Create Employee from FormData
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto create(HttpServletRequest request) {
        return EmployeeMapper.getForShow(
                employeeService.save(request)
        );
    }
    
    /**
     * Update Employee by ID from FormData
     */
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto update(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        
        return EmployeeMapper.getForShow(employeeService.save(id, request));
    }
    
    /**
     * Delete Employee by ID
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        employeeService.deleteById(id);
    }
    
    /**
     * Get all Employees
     */
    @GetMapping
    public List<EmployeeDto> getAll() {
        return employeeService.findAll()
                .stream().map(EmployeeMapper::getForShow).toList();
    }
    
    /**
     * Get Employee by ID
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getOne(HttpServletRequest request) {
        Integer id = Integer.valueOf(HttpServletRequestUtil.getAttributeByKey(request, "id"));
        
        return EmployeeMapper.getForShow(employeeService.findById(id));
    }
}
