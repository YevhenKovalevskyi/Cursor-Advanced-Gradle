package hw10.task1.mappers.impl;

import hw10.task1.dto.StudentDto;
import hw10.task1.dto.StudentEditDto;
import hw10.task1.entities.Student;
import hw10.task1.mappers.StudentMapper;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Component
public class StudentMapperImpl implements StudentMapper {
    
    private ModelMapper modelMapper;
    
    public StudentDto toDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }
    
    public Student toCreateEntity(StudentEditDto studentToCreate) {
        Student student = modelMapper.map(studentToCreate, Student.class);
        student.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        student.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return student;
    }
    
    public Student toUpdateEntity(Student studentCurrent, StudentEditDto studentToUpdate) {
        Student student = modelMapper.map(studentToUpdate, Student.class);
        student.setId(studentCurrent.getId());
        student.setCreatedAt(studentCurrent.getCreatedAt());
        student.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return student;
    }
}
