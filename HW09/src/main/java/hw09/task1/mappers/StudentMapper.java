package hw09.task1.mappers;

import hw09.task1.dto.StudentDto;
import hw09.task1.dto.StudentLightDto;
import hw09.task1.entities.Student;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StudentMapper {
    
    public static Student getForUpdate(Integer id, Student currentStudent, Student newStudent) {
        newStudent.setCreatedAt(currentStudent.getCreatedAt());
        
        return Student.build(id, newStudent);
    }
    
    public static StudentDto getForShow(Student student) {
        return StudentDto.build(student);
    }
    
    public static StudentLightDto getForShowSingle(Student student) {
        return StudentLightDto.build(student);
    }
}
