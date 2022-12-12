package hw09.task1.services.impl;

import hw09.task1.dto.StudentDto;
import hw09.task1.dto.StudentEditDto;
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
    private StudentMapper studentMapper;
    
    public Student findByIdIfExists(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            log.error(Messages.STUDENT_NOT_FOUND.getLogMessage(), id);
            throw new StudentNotFoundException(
                    String.format(Messages.STUDENT_NOT_FOUND.getOutMessage(), id)
            );
        });
    }
    
    public StudentDto create(StudentEditDto studentToCreate) {
        Student studentCreated = studentRepository.save(
                studentMapper.toCreateEntity(studentToCreate)
        );
    
        return studentMapper.toDto(studentCreated);
    }
    
    public StudentDto update(Integer id, StudentEditDto studentToUpdate) {
        Student studentCurrent = findByIdIfExists(id);
        Student studentUpdated = studentRepository.save(
                studentMapper.toUpdateEntity(studentCurrent, studentToUpdate)
        );
    
        return studentMapper.toDto(studentUpdated);
    }
    
    public void deleteById(Integer id) {
        findByIdIfExists(id);
        studentRepository.deleteById(id);
    }
    
    public List<StudentDto> findAll() {
        return ((List<Student>) studentRepository.findAll())
                .stream()
                .map(student -> studentMapper.toDto(student))
                .toList();
    }
    
    public StudentDto findById(Integer id) {
        return studentMapper.toDto(findByIdIfExists(id));
    }
}
