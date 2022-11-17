package hw07.task1.dto;

import hw07.task1.entities.Group;
import hw07.task1.mappers.TeacherMapper;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class GroupDto {
    
    private Integer id;
    private String name;
    private TeacherDto teacher;
    
    public static GroupDto build(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .teacher(
                        TeacherMapper.getForShow(group.getTeacher())
                )
                .build();
    }
}
