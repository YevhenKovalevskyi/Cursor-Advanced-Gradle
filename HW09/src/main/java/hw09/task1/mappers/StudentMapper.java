package hw09.task1.mappers;

import hw09.task1.dto.StudentDto;
import hw09.task1.dto.StudentEditDto;
import hw09.task1.entities.Student;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface StudentMapper {
    
    StudentDto toDto(Student student);
    Student toCreateEntity(StudentEditDto studentToCreate);
    Student toUpdateEntity(Student studentCurrent, StudentEditDto studentToUpdate);
}
