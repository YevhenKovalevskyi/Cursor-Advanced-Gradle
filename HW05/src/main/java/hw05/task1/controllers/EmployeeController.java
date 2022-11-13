package hw05.task1.controllers;

import hw05.task1.database.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YevhenKovalevskyi
 */
@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    
}
