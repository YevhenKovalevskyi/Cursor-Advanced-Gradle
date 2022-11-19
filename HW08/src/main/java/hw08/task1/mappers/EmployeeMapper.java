package hw08.task1.mappers;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.EmployeeLightDto;
import hw08.task1.entities.Employee;

/**
 * @author YevhenKovalevskyi
 */
public class EmployeeMapper {
    
    public static Employee getForUpdate(Integer id, Employee currentEmployee, Employee newEmployee) {
        newEmployee.setCreatedAt(currentEmployee.getCreatedAt());
        
        return Employee.build(id, newEmployee);
    }
    
    public static EmployeeDto getForShow(Employee employee) {
        return EmployeeDto.build(employee);
    }
    
    public static EmployeeLightDto getForShowLight(Employee employee) {
        return EmployeeLightDto.build(employee);
    }
}
