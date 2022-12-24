package hw10.task1.unit.controllers;

import hw10.task1.WatchmanExtension;
import hw10.task1.controllers.TeacherController;
import hw10.task1.dto.GroupDto;
import hw10.task1.dto.StudentDto;
import hw10.task1.dto.TeacherDto;
import hw10.task1.dto.TeacherEditDto;
import hw10.task1.exceptions.TeacherNotFoundException;
import hw10.task1.services.TeacherService;

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
    
    private static final int ID = 1;
    
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
        when(teacherService.update(ID, TEACHER_EDIT_DTO)).thenReturn(TEACHER_DTO);
        
        assertEquals(TEACHER_DTO, teacherController.update(ID, TEACHER_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(teacherService.update(ID, TEACHER_EDIT_DTO)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.update(ID, TEACHER_EDIT_DTO))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        teacherController.delete(ID);
        
        verify(teacherService).deleteById(ID);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(TeacherNotFoundException.class).when(teacherService).deleteById(ID);
        
        assertThatThrownBy(() -> teacherController.delete(ID))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getAllReturnValidResponse() {
        when(teacherService.findAll()).thenReturn(List.of(TEACHER_DTO));
        
        assertEquals(List.of(TEACHER_DTO), teacherController.getAll());
    }

    @Test
    public void getOneReturnValidResponse() {
        when(teacherService.findById(ID)).thenReturn(TEACHER_DTO);
        
        assertEquals(TEACHER_DTO, teacherController.getOne(ID));
    }
    
    @Test
    public void getOneReturnException() {
        when(teacherService.findById(ID)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getOne(ID))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getGroupsReturnValidResponse() {
        when(teacherService.findGroups(ID)).thenReturn(List.of(GROUP_DTO));
        
        assertEquals(List.of(GROUP_DTO), teacherController.getGroups(ID));
    }
    
    @Test
    public void getGroupsReturnException() {
        when(teacherService.findGroups(ID)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getGroups(ID))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getGroupsCountReturnValidResponse() {
        int count = anyInt();
        
        when(teacherService.findGroupsCount(ID)).thenReturn(count);
        
        assertEquals(count, teacherController.getGroupsCount(ID));
    }
    
    @Test
    public void getGroupsCountReturnException() {
        when(teacherService.findGroupsCount(ID)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getGroupsCount(ID))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getStudentsReturnValidResponse() {
        when(teacherService.findStudents(ID)).thenReturn(List.of(STUDENT_DTO));
        
        assertEquals(List.of(STUDENT_DTO), teacherController.getStudents(ID));
    }
    
    @Test
    public void getStudentsReturnException() {
        when(teacherService.findStudents(ID)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getStudents(ID))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void getStudentsCountReturnValidResponse() {
        int count = anyInt();
        
        when(teacherService.findStudentsCount(ID)).thenReturn(count);
        
        assertEquals(count, teacherController.getStudentsCount(ID));
    }
    
    @Test
    public void getStudentsCountReturnException() {
        when(teacherService.findStudentsCount(ID)).thenThrow(TeacherNotFoundException.class);
        
        assertThatThrownBy(() -> teacherController.getStudentsCount(ID))
                .isInstanceOf(TeacherNotFoundException.class);
    }
}
