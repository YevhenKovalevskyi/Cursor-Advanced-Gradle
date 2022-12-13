package hw09.task1.controllers;

import hw09.task1.WatchmanExtension;
import hw09.task1.dto.GroupDto;
import hw09.task1.dto.StudentDto;
import hw09.task1.dto.TeacherDto;
import hw09.task1.dto.TeacherEditDto;
import hw09.task1.exceptions.TeacherNotFoundException;
import hw09.task1.services.TeacherService;

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
public class TeacherControllerTest {
    
    private static final TeacherDto TEACHER_DTO = new TeacherDto();
    private static final TeacherEditDto TEACHER_EDIT_DTO = new TeacherEditDto();
    private static final GroupDto GROUP_DTO = new GroupDto();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    
    @Mock
    private TeacherService teacherService;
    
    @InjectMocks
    private TeacherController teacherController;
    
    @Test
    public void createReturnValidResponse() {
        when(teacherService.create(TEACHER_EDIT_DTO)).thenReturn(TEACHER_DTO);
        
        assertEquals(TEACHER_DTO, teacherController.create(TEACHER_EDIT_DTO));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(teacherService.update(1, TEACHER_EDIT_DTO)).thenReturn(TEACHER_DTO);
        
        assertEquals(TEACHER_DTO, teacherController.update(1, TEACHER_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(teacherService.update(1, TEACHER_EDIT_DTO)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.update(1, TEACHER_EDIT_DTO))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        teacherController.delete(1);
        
        verify(teacherService).deleteById(1);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherService).deleteById(1);
        
        assertThatThrownBy(() -> teacherController.delete(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getAllReturnValidResponse() {
        when(teacherService.findAll()).thenReturn(List.of(TEACHER_DTO));
        
        assertEquals(List.of(TEACHER_DTO), teacherController.getAll());
    }

    @Test
    public void getOneReturnValidResponse() {
        when(teacherService.findById(1)).thenReturn(TEACHER_DTO);
        
        assertEquals(TEACHER_DTO, teacherController.getOne(1));
    }
    
    @Test
    public void getOneReturnException() {
        when(teacherService.findById(1)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getOne(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getGroupsReturnValidResponse() {
        when(teacherService.findGroups(1)).thenReturn(List.of(GROUP_DTO));
        
        assertEquals(List.of(GROUP_DTO), teacherController.getGroups(1));
    }
    
    @Test
    public void getGroupsReturnException() {
        when(teacherService.findGroups(1)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getGroups(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getGroupsCountReturnValidResponse() {
        int count = anyInt();
        
        when(teacherService.findGroupsCount(1)).thenReturn(count);
        
        assertEquals(count, teacherController.getGroupsCount(1));
    }
    
    @Test
    public void getGroupsCountReturnException() {
        when(teacherService.findGroupsCount(1)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getGroupsCount(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getStudentsReturnValidResponse() {
        when(teacherService.findStudents(1)).thenReturn(List.of(STUDENT_DTO));
        
        assertEquals(List.of(STUDENT_DTO), teacherController.getStudents(1));
    }
    
    @Test
    public void getStudentsReturnException() {
        when(teacherService.findStudents(1)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getStudents(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getStudentsCountReturnValidResponse() {
        int count = anyInt();
        
        when(teacherService.findStudentsCount(1)).thenReturn(count);
        
        assertEquals(count, teacherController.getStudentsCount(1));
    }
    
    @Test
    public void getStudentsCountReturnException() {
        when(teacherService.findStudentsCount(1)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getStudentsCount(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
}
