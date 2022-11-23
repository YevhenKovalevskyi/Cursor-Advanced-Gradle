package hw09.task1.dto;

import hw09.task1.entities.Teacher;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class TeacherDto {
    
    private Integer id;
    private String name;
    
    public static TeacherDto build(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .build();
    }
}
