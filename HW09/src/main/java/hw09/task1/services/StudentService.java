package hw09.task1.services;

import hw09.task1.dto.StudentDto;
import hw09.task1.dto.StudentEditDto;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
public interface StudentService {
    
    StudentDto create(StudentEditDto studentToCreate);
    StudentDto update(Integer id, StudentEditDto studentToUpdate);
    void deleteById(Integer id);
    List<StudentDto> findAll();
    StudentDto findById(Integer id);
}
