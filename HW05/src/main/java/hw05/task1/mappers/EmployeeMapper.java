package hw05.task1.mappers;

import hw05.task1.entities.Employee;

/**
 * @author YevhenKovalevskyi
 */
public class EmployeeMapper {
    
    public static Employee getForUpdate(Integer id, Employee currentEmployee, Employee newEmployee) {
        newEmployee.setCreatedAt(currentEmployee.getCreatedAt());
        
        return Employee.build(id, newEmployee);
    }
}
