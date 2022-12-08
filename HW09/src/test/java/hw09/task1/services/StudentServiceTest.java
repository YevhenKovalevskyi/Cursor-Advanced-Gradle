package hw09.task1.services;

import hw09.task1.WatchmanExtension;
import hw09.task1.entities.Student;
import hw09.task1.exceptions.StudentNotFoundException;
import hw09.task1.repositories.StudentRepository;
import hw09.task1.services.impl.StudentServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@ExtendWith(WatchmanExtension.class)
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    
    private static final Student STUDENT = new Student();
    
    @Mock
    private StudentRepository studentRepository;
    
    @InjectMocks
    private StudentServiceImpl studentService;
    
    @Test
    public void findByIdIfExistsReturnValidResponse() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(STUDENT));
        assertEquals(STUDENT, studentService.findByIdIfExists(1));
    }
    
    @Test
    public void findByIdIfExistsReturnException() {
        doThrow(StudentNotFoundException.class).when(studentRepository).findById(1);
        assertThatThrownBy(() -> studentService.findByIdIfExists(1))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void createReturnValidResponse() {
        when(studentRepository.save(Student.build(STUDENT))).thenReturn(STUDENT);
        assertEquals(STUDENT, studentService.create(STUDENT));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(STUDENT));
        when(studentRepository.save(Student.build(1, STUDENT))).thenReturn(STUDENT);
        assertEquals(STUDENT, studentService.update(1, STUDENT));
    }
    
    @Test
    public void updateReturnException() {
        doThrow(StudentNotFoundException.class).when(studentRepository).findById(1);
        assertThatThrownBy(() -> studentService.update(1, STUDENT))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(STUDENT));
    
        studentService.deleteById(1);
        
        verify(studentRepository).deleteById(1);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(StudentNotFoundException.class).when(studentRepository).findById(1);
        assertThatThrownBy(() -> studentService.deleteById(1))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void findAllReturnValidResponse() {
        when(studentRepository.findAll()).thenReturn(List.of(STUDENT));
        assertEquals(List.of(STUDENT), studentService.findAll());
    }
    
    @Test
    public void findByIdReturnValidResponse() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(STUDENT));
        assertEquals(STUDENT, studentService.findById(1));
    }
    
    @Test
    public void findByIdReturnException() {
        doThrow(StudentNotFoundException.class).when(studentRepository).findById(1);
        assertThatThrownBy(() -> studentService.findById(1))
                .isInstanceOf(StudentNotFoundException.class);
    }
}
