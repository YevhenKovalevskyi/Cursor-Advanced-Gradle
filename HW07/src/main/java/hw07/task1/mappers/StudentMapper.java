package hw07.task1.mappers;

import hw07.task1.dto.StudentDto;
import hw07.task1.dto.StudentEditDto;
import hw07.task1.entities.Student;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {
    
    StudentDto toDto(Student student);
    Student toCreateEntity(StudentEditDto studentDto);
    Student toUpdateEntity(Student currentStudent, StudentEditDto studentDto);
}
