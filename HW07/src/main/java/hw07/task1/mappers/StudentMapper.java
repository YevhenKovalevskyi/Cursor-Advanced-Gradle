package hw07.task1.mappers;

import hw07.task1.dto.StudentDto;
import hw07.task1.dto.StudentLightDto;
import hw07.task1.entities.Student;

/**
 * @author YevhenKovalevskyi
 */
public class StudentMapper {
    
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
