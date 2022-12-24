package hw10.task1.unit.services;

import hw10.task1.WatchmanExtension;
import hw10.task1.dto.StudentDto;
import hw10.task1.dto.StudentEditDto;
import hw10.task1.entities.Student;
import hw10.task1.exceptions.StudentNotFoundException;
import hw10.task1.mappers.StudentMapper;
import hw10.task1.repositories.StudentRepository;
import hw10.task1.services.impl.StudentServiceImpl;

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
    
    private static final int ID = 1;
    
    private static final Student STUDENT = new Student();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    private static final StudentEditDto STUDENT_EDIT_DTO = new StudentEditDto();
    
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    
    @InjectMocks
    private StudentServiceImpl studentService;
    
    @Test
    public void findByIdIfExistsReturnValidResponse() {
        when(studentRepository.findById(ID)).thenReturn(Optional.of(STUDENT));
        
        assertEquals(STUDENT, studentService.findByIdIfExists(ID));
    }
    
    @Test
    public void findByIdIfExistsReturnException() {
        when(studentRepository.findById(ID)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> studentService.findByIdIfExists(ID))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void createReturnValidResponse() {
        when(studentMapper.toCreateEntity(STUDENT_EDIT_DTO)).thenReturn(STUDENT);
        when(studentMapper.toDto(STUDENT)).thenReturn(STUDENT_DTO);
        when(studentRepository.save(STUDENT)).thenReturn(STUDENT);
        
        assertEquals(STUDENT_DTO, studentService.create(STUDENT_EDIT_DTO));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(studentRepository.findById(ID)).thenReturn(Optional.of(STUDENT));
        when(studentMapper.toUpdateEntity(STUDENT, STUDENT_EDIT_DTO)).thenReturn(STUDENT);
        when(studentMapper.toDto(STUDENT)).thenReturn(STUDENT_DTO);
        when(studentRepository.save(STUDENT)).thenReturn(STUDENT);
        
        assertEquals(STUDENT_DTO, studentService.update(ID, STUDENT_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(studentRepository.findById(ID)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> studentService.update(ID, STUDENT_EDIT_DTO))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        when(studentRepository.findById(ID)).thenReturn(Optional.of(STUDENT));
    
        studentService.deleteById(ID);
        
        verify(studentRepository).deleteById(ID);
    }
    
    @Test
    public void deleteReturnException() {
        when(studentRepository.findById(ID)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> studentService.deleteById(ID))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void findAllReturnValidResponse() {
        when(studentRepository.findAll()).thenReturn(List.of(STUDENT));
        when(studentMapper.toDto(STUDENT)).thenReturn(STUDENT_DTO);
        
        assertEquals(List.of(STUDENT_DTO), studentService.findAll());
    }
    
    @Test
    public void findByIdReturnValidResponse() {
        when(studentRepository.findById(ID)).thenReturn(Optional.of(STUDENT));
        when(studentMapper.toDto(STUDENT)).thenReturn(STUDENT_DTO);
        
        assertEquals(STUDENT_DTO, studentService.findById(ID));
    }
    
    @Test
    public void findByIdReturnException() {
        when(studentRepository.findById(ID)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> studentService.findById(ID))
                .isInstanceOf(StudentNotFoundException.class);
    }
}
