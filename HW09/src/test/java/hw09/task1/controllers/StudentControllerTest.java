package hw09.task1.controllers;

import hw09.task1.WatchmanExtension;
import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.exceptions.StudentNotFoundException;
import hw09.task1.mappers.StudentMapper;
import hw09.task1.services.impl.StudentServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

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
    
    private static final Student STUDENT = new Student();
    
    @Mock
    private StudentServiceImpl studentService;
    
    @InjectMocks
    private StudentController studentController;
    
    {
        STUDENT.setGroup(new Group());
    }
    
    @Test
    public void createReturnValidResponse() {
        when(studentService.create(STUDENT)).thenReturn(STUDENT);
        assertEquals(StudentMapper.getForShow(STUDENT), studentController.create(STUDENT));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(studentService.update(1, STUDENT)).thenReturn(STUDENT);
        assertEquals(StudentMapper.getForShow(STUDENT), studentController.update(1, STUDENT));
    }
    
    @Test
    public void updateReturnException() {
        doThrow(StudentNotFoundException.class).when(studentService).update(1, STUDENT);
        assertThatThrownBy(() -> studentController.update(1, STUDENT))
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
        when(studentService.findAll()).thenReturn(List.of(STUDENT));
        assertEquals(Stream.of(STUDENT).map(StudentMapper::getForShow).toList(), studentController.getAll());
    }
    
    @Test
    public void getOneReturnValidResponse() {
        when(studentService.findById(1)).thenReturn(STUDENT);
        assertEquals(StudentMapper.getForShow(STUDENT), studentController.getOne(1));
    }
    
    @Test
    public void getOneReturnException() {
        doThrow(StudentNotFoundException.class).when(studentService).findById(1);
        assertThatThrownBy(() -> studentController.getOne(1))
                .isInstanceOf(StudentNotFoundException.class);
    }
}
