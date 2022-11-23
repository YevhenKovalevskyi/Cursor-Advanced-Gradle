package hw09.task1.dto;

import hw09.task1.entities.Student;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class StudentLightDto {
    
    private Integer id;
    private String name;
    
    public static StudentLightDto build(Student student) {
        return StudentLightDto.builder()
                .id(student.getId())
                .name(student.getName())
                .build();
    }
}
