package hw09.task1.services.impl;

import hw09.task1.entities.Student;
import hw09.task1.exceptions.DataNotFoundException;
import hw09.task1.exceptions.StudentNotFoundException;
import hw09.task1.mappers.StudentMapper;
import hw09.task1.messages.Messages;
import hw09.task1.services.StudentService;
import hw09.task1.repositories.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    
    private StudentRepository studentRepository;
    
    public Student checkFound(Integer id, Optional<Student> student) {
        return student.orElseThrow(() -> {
            log.error(Messages.STUDENT_NOT_FOUND.getLogMessage(), id);
            throw new StudentNotFoundException(
                    String.format(Messages.STUDENT_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Student save(Student newStudent) {
        return studentRepository.save(Student.build(newStudent));
    }
    
    public Student save(Integer id, Student newStudent) {
        Student currStudent = checkFound(id, studentRepository.findById(id));
        newStudent = StudentMapper.getForUpdate(id, currStudent, newStudent);
        
        return studentRepository.save(newStudent);
    }
    
    public void deleteById(Integer id) {
        checkFound(id, studentRepository.findById(id));
        studentRepository.deleteById(id);
    }
    
    public List<Student> findAll() {
        List<Student> students = (List<Student>) studentRepository.findAll();
        
        if (students.isEmpty()) {
            log.error(Messages.DATA_NOT_FOUND.getLogMessage());
            throw new DataNotFoundException(Messages.DATA_NOT_FOUND.getOutMessage());
        }
        
        return students;
    }
    
    public Student findById(Integer id) {
        return checkFound(id, studentRepository.findById(id));
    }
}
