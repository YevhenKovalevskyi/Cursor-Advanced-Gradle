package hw10.task1.mappers;

import hw10.task1.dto.TeacherDto;
import hw10.task1.dto.TeacherEditDto;
import hw10.task1.entities.Teacher;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface TeacherMapper {
    
    TeacherDto toDto(Teacher teacher);
    Teacher toCreateEntity(TeacherEditDto teacherToCreate);
    Teacher toUpdateEntity(Teacher teacherCurrent, TeacherEditDto teacherToUpdate);
}
