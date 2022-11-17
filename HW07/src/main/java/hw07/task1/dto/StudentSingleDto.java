package hw07.task1.dto;

import hw07.task1.entities.Student;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class StudentSingleDto {
    
    private Integer id;
    private String name;
    
    public static StudentSingleDto build(Student student) {
        return StudentSingleDto.builder()
                .id(student.getId())
                .name(student.getName())
                .build();
    }
}
