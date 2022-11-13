package hw05.task1.database.mappers;

import hw05.task1.database.entities.Employee;

/**
 * @author YevhenKovalevskyi
 */
public class EmployeeMapper {
    
    public static Employee getForUpdate(Employee currentEmployee, Employee newEmployee, Integer id) {
        newEmployee.setCreatedAt(currentEmployee.getCreatedAt());
        
        return Employee.build(newEmployee, id);
    }
}
