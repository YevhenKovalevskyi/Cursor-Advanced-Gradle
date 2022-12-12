package hw08.task1.mappers.impl;

import hw08.task1.dto.EmployeeDto;
import hw08.task1.dto.EmployeeEditDto;
import hw08.task1.entities.Employee;
import hw08.task1.mappers.EmployeeMapper;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Component
public class EmployeeMapperImpl implements EmployeeMapper {
    
    private ModelMapper modelMapper;
    
    public EmployeeDto toDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }
    
    public Employee toCreateEntity(EmployeeEditDto employeeToCreate) {
        Employee employee = modelMapper.map(employeeToCreate, Employee.class);
        employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return employee;
    }
    
    public Employee toUpdateEntity(Employee employeeCurrent, EmployeeEditDto employeeToUpdate) {
        Employee employee = modelMapper.map(employeeToUpdate, Employee.class);
        employee.setId(employeeCurrent.getId());
        employee.setCreatedAt(employeeCurrent.getCreatedAt());
        employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return employee;
    }
}
