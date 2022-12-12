package hw08.task1.dto;

import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
public class EmployeeDto {
    
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private ShopEmployeeDto shop;
}
