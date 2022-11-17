package hw07.task1.mappers;

import hw07.task1.dto.TeacherDto;
import hw07.task1.entities.Teacher;

/**
 * @author YevhenKovalevskyi
 */
public class TeacherMapper {
    
    public static Teacher getForUpdate(Integer id, Teacher currentTeacher, Teacher newTeacher) {
        newTeacher.setCreatedAt(currentTeacher.getCreatedAt());
        
        return Teacher.build(id, newTeacher);
    }
    
    public static TeacherDto getForShow(Teacher teacher) {
        return TeacherDto.build(teacher);
    }
}
