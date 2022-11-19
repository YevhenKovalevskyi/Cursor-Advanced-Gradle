package hw08.task1.dto;

import hw08.task1.entities.Employee;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class EmployeeLightDto {
    
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    
    public static EmployeeLightDto build(Employee employee) {
        return EmployeeLightDto.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .age(employee.getAge())
                .build();
    }
}
