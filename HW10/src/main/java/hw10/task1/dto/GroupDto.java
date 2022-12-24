package hw10.task1.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor
@Data
public class GroupDto {
    
    private Integer id;
    private String name;
    private TeacherGroupDto teacher;
}
