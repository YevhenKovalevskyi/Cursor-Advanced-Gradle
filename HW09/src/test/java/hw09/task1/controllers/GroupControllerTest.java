package hw09.task1.controllers;

import hw09.task1.WatchmanExtension;
import hw09.task1.dto.GroupDto;
import hw09.task1.dto.GroupEditDto;
import hw09.task1.dto.StudentDto;
import hw09.task1.exceptions.GroupNotFoundException;
import hw09.task1.services.GroupService;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
public class GroupControllerTest {
    
    private static final GroupDto GROUP_DTO = new GroupDto();
    private static final GroupEditDto GROUP_EDIT_DTO = new GroupEditDto();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    
    @Mock
    private GroupService groupService;
    
    @InjectMocks
    private GroupController groupController;
    
    @Test
    public void createReturnValidResponse() {
        when(groupService.create(GROUP_EDIT_DTO)).thenReturn(GROUP_DTO);
        
        assertEquals(GROUP_DTO, groupController.create(GROUP_EDIT_DTO));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(groupService.update(1, GROUP_EDIT_DTO)).thenReturn(GROUP_DTO);
        
        assertEquals(GROUP_DTO, groupController.update(1, GROUP_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(groupService.update(1, GROUP_EDIT_DTO)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.update(1, GROUP_EDIT_DTO))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        groupController.delete(1);
        
        verify(groupService).deleteById(1);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(GroupNotFoundException.class).when(groupService).deleteById(1);
        
        assertThatThrownBy(() -> groupController.delete(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void getAllReturnValidResponse() {
        when(groupService.findAll()).thenReturn(List.of(GROUP_DTO));
        
        assertEquals(List.of(GROUP_DTO), groupController.getAll());
    }
    
    @Test
    public void getOneReturnValidResponse() {
        when(groupService.findById(1)).thenReturn(GROUP_DTO);
        
        assertEquals(GROUP_DTO, groupController.getOne(1));
    }
    
    @Test
    public void getOneReturnException() {
        when(groupService.findById(1)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.getOne(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void getStudentsReturnValidResponse() {
        when(groupService.findStudents(1)).thenReturn(List.of(STUDENT_DTO));
        
        assertEquals(List.of(STUDENT_DTO), groupController.getStudents(1));
    }
    
    @Test
    public void getStudentsReturnException() {
        when(groupService.findStudents(1)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.getStudents(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void getStudentsCountReturnValidResponse() {
        int count = anyInt();
        
        when(groupService.findStudentsCount(1)).thenReturn(count);
        
        assertEquals(count, groupController.getStudentsCount(1));
    }
    
    @Test
    public void getStudentsCountReturnException() {
        when(groupService.findStudentsCount(1)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.getStudentsCount(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
}
