package hw09.task1.services;

import hw09.task1.WatchmanExtension;
import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.entities.Teacher;
import hw09.task1.exceptions.TeacherNotFoundException;
import hw09.task1.repositories.TeacherRepository;
import hw09.task1.services.impl.TeacherServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@ExtendWith(WatchmanExtension.class)
@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    
    private static final Teacher TEACHER = new Teacher();
    private static final Group GROUP = new Group();
    private static final Student STUDENT = new Student();
    
    @Mock
    private TeacherRepository teacherRepository;
    
    @InjectMocks
    private TeacherServiceImpl teacherService;
    
    @Test
    public void createReturnValidResponse() {
        when(teacherRepository.save(Teacher.build(TEACHER))).thenReturn(TEACHER);
        assertEquals(TEACHER, teacherService.create(TEACHER));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        when(teacherRepository.save(Teacher.build(1, TEACHER))).thenReturn(TEACHER);
        assertEquals(TEACHER, teacherService.update(1, TEACHER));
    }

    @Test
    public void updateReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherRepository).findById(1);
        assertThatThrownBy(() -> teacherService.update(1, TEACHER))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        teacherService.deleteById(1);
        verify(teacherRepository).deleteById(1);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherRepository).findById(1);
        assertThatThrownBy(() -> teacherService.deleteById(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findAllReturnValidResponse() {
        when(teacherRepository.findAll()).thenReturn(Collections.singletonList(TEACHER));
        assertEquals(Collections.singletonList(TEACHER), teacherService.findAll());
    }
    
    @Test
    public void findByIdReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        assertEquals(TEACHER, teacherService.findById(1));
    }
    
    @Test
    public void findByIdReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherRepository).findById(1);
        assertThatThrownBy(() -> teacherService.findById(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findGroupsReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        TEACHER.setGroups(Collections.singletonList(GROUP));
        assertEquals(Collections.singletonList(GROUP), teacherService.findGroups(1));
    }
    
    @Test
    public void findGroupsReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherRepository).findById(1);
        assertThatThrownBy(() -> teacherService.findGroups(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findGroupsCountReturnValidResponse() {
        int count = anyInt();
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        TEACHER.setGroups(new ArrayList<>(count));
        assertEquals(count, teacherService.findGroupsCount(1));
    }
    
    @Test
    public void findGroupsCountReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherRepository).findById(1);
        assertThatThrownBy(() -> teacherService.findGroupsCount(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findStudentsReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        TEACHER.setGroups(Collections.singletonList(GROUP));
        
        assertEquals(Collections.singletonList(STUDENT), teacherService.findStudents(1));
    }
    
    @Test
    public void findStudentsReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherRepository).findById(1);
        assertThatThrownBy(() -> teacherService.findStudents(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findStudentsCountReturnValidResponse() {
        int count = anyInt();
        when(teacherService.findStudents(1).size()).thenReturn(count);
        assertEquals(count, teacherService.findStudentsCount(1));
    }
    
    @Test
    public void findStudentsCountReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherRepository).findById(1);
        assertThatThrownBy(() -> teacherService.findStudentsCount(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
}
