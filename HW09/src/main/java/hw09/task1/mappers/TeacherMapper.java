package hw09.task1.mappers;

import hw09.task1.dto.TeacherDto;
import hw09.task1.entities.Teacher;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TeacherMapper {

    public static Teacher getForUpdate(Integer id, Teacher currentTeacher, Teacher newTeacher) {
        newTeacher.setCreatedAt(currentTeacher.getCreatedAt());
        
        return Teacher.build(id, newTeacher);
    }
    
    public static TeacherDto getForShow(Teacher teacher) {
        return TeacherDto.build(teacher);
    }
}
