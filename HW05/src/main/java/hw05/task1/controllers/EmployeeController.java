package hw05.task1.controllers;

import hw05.task1.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    private EmployeeService employeeService;
    
}
