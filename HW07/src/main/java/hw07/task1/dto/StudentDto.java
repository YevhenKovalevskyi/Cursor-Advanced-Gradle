package hw07.task1.dto;

import hw07.task1.entities.Student;
import hw07.task1.mappers.GroupMapper;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class StudentDto {
    
    private Integer id;
    private String name;
    private GroupSingleDto group;
    
    public static StudentDto build(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .name(student.getName())
                .group(
                        GroupMapper.getForShowSingle(student.getGroup())
                )
                .build();
    }
}
