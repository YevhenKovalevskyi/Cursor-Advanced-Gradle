package hw10.task1.unit.controllers;

import hw10.task1.WatchmanExtension;
import hw10.task1.controllers.StudentController;
import hw10.task1.dto.StudentDto;
import hw10.task1.dto.StudentEditDto;
import hw10.task1.exceptions.StudentNotFoundException;
import hw10.task1.services.StudentService;

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
    
    private static final int ID = 1;
    
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
        when(studentService.update(ID, STUDENT_EDIT_DTO)).thenReturn(STUDENT_DTO);
        
        assertEquals(STUDENT_DTO, studentController.update(ID, STUDENT_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(studentService.update(ID, STUDENT_EDIT_DTO)).thenThrow(StudentNotFoundException.class);
        
        assertThatThrownBy(() -> studentController.update(ID, STUDENT_EDIT_DTO))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        studentController.delete(ID);
        
        verify(studentService).deleteById(ID);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(StudentNotFoundException.class).when(studentService).deleteById(ID);
        
        assertThatThrownBy(() -> studentController.delete(ID))
                .isInstanceOf(StudentNotFoundException.class);
    }
    
    @Test
    public void getAllReturnValidResponse() {
        when(studentService.findAll()).thenReturn(List.of(STUDENT_DTO));
        
        assertEquals(List.of(STUDENT_DTO), studentController.getAll());
    }
    
    @Test
    public void getOneReturnValidResponse() {
        when(studentService.findById(ID)).thenReturn(STUDENT_DTO);
        
        assertEquals(STUDENT_DTO, studentController.getOne(ID));
    }
    
    @Test
    public void getOneReturnException() {
        when(studentService.findById(ID)).thenThrow(StudentNotFoundException.class);
        
        assertThatThrownBy(() -> studentController.getOne(ID))
                .isInstanceOf(StudentNotFoundException.class);
    }
}
