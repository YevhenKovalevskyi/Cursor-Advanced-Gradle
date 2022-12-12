package hw09.task1.dto;

import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
public class GroupEditDto {
    
    private String name;
    private TeacherGroupDto teacher;
}
