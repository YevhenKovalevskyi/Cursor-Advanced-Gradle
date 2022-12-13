package hw09.task1.controllers;

import hw09.task1.WatchmanExtension;
import hw09.task1.dto.StudentDto;
import hw09.task1.dto.StudentEditDto;
import hw09.task1.exceptions.StudentNotFoundException;
import hw09.task1.services.StudentService;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@ExtendWith(WatchmanExtension.class)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    
    private static final StudentDto STUDENT_DTO = new StudentDto();
    private static final StudentEditDto STUDENT_EDIT_DTO = new StudentEditDto();
    
    @Mock
    private StudentService studentService;
    
    @InjectMocks
    private StudentController studentController;
    
    @Test
    public void createReturnValidResponse() {
        when(studentService.create(STUDENT_EDIT_DTO)).thenReturn(STUDENT_DTO);
        
        assertEquals(STUDENT_DTO, studentController.create(STUDENT_EDIT_DTO));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(studentService.update(1, STUDENT_EDIT_DTO)).thenReturn(STUDENT_DTO);
        
        assertEquals(STUDENT_DTO, studentController.update(1, STUDENT_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(studentService.update(1, STUDENT_EDIT_DTO)).thenThrow(StudentNotFoundException.class);
        
        assertThatThrownBy(() -> studentController.update(1, STUDENT_EDIT_DTO))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        studentController.delete(1);
        
        verify(studentService).deleteById(1);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(StudentNotFoundException.class).when(studentService).deleteById(1);
        
        assertThatThrownBy(() -> studentController.delete(1))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void getAllReturnValidResponse() {
        when(studentService.findAll()).thenReturn(List.of(STUDENT_DTO));
        
        assertEquals(List.of(STUDENT_DTO), studentController.getAll());
    }
    
    @Test
    public void getOneReturnValidResponse() {
        when(studentService.findById(1)).thenReturn(STUDENT_DTO);
        
        assertEquals(STUDENT_DTO, studentController.getOne(1));
    }
    
    @Test
    public void getOneReturnException() {
        when(studentService.findById(1)).thenThrow(StudentNotFoundException.class);
        
        assertThatThrownBy(() -> studentController.getOne(1))
                .isInstanceOf(StudentNotFoundException.class);
    }
}
