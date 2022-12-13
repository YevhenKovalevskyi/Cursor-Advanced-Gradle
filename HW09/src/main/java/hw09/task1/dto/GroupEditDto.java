package hw09.task1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@Data
public class GroupEditDto {
    
    private String name;
    private TeacherGroupDto teacher;
}
