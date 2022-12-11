package hw07.task1.mappers;

import hw07.task1.dto.TeacherDto;
import hw07.task1.dto.TeacherEditDto;
import hw07.task1.entities.Teacher;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface TeacherMapper {
    
    TeacherDto toDto(Teacher teacher);
    Teacher toCreateEntity(TeacherEditDto teacherDto);
    Teacher toUpdateEntity(Teacher currentTeacher, TeacherEditDto teacherDto);
}
