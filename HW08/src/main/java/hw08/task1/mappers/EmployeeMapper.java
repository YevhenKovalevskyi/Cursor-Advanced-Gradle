package hw08.task1.mappers;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.EmployeeEditDto;
import hw08.task1.entities.Employee;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    
    EmployeeDto toDto(Employee employee);
    Employee toCreateEntity(EmployeeEditDto employeeToCreate);
    Employee toUpdateEntity(Employee employeeCurrent, EmployeeEditDto employeeToUpdate);
}
