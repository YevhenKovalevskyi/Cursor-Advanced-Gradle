package hw08.task1.dto;

import hw08.task1.entities.Employee;
import hw08.task1.mappers.ShopMapper;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class EmployeeDto {
    
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private ShopLightDto shop;
    
    public static EmployeeDto build(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .gender(employee.getGender())
                .age(employee.getAge())
                .shop(
                        ShopMapper.getForShowLight(employee.getShop())
                )
                .build();
    }
}
