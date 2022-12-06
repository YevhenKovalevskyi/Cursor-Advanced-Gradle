package hw09.task1.services.impl;

import hw09.task1.entities.Student;
import hw09.task1.exceptions.StudentNotFoundException;
import hw09.task1.mappers.StudentMapper;
import hw09.task1.messages.Messages;
import hw09.task1.services.StudentService;
import hw09.task1.repositories.StudentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    
    private StudentRepository studentRepository;
    
    public Student findByIdIfExists(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.STUDENT_NOT_FOUND.getLogMessage(), id);
            throw new StudentNotFoundException(
                    String.format(Messages.STUDENT_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public Student create(Student newStudent) {
        return studentRepository.save(Student.build(newStudent));
    }
    
    public Student update(Integer id, Student newStudent) {
        Student currStudent = findByIdIfExists(id);
        newStudent = StudentMapper.getForUpdate(id, currStudent, newStudent);
        
        return studentRepository.save(newStudent);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        studentRepository.deleteById(id);
    }
    
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }
    
    public Student findById(Integer id) {
        return findByIdIfExists(id);
    }
}
