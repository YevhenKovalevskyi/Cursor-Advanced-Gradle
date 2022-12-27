package hw10.task1.unit.controllers;

import hw10.task1.WatchmanExtension;
import hw10.task1.controllers.GroupController;
import hw10.task1.dto.GroupDto;
import hw10.task1.dto.GroupEditDto;
import hw10.task1.dto.StudentDto;
import hw10.task1.exceptions.GroupNotFoundException;
import hw10.task1.services.GroupService;

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
    
    private static final int ID = 1;
    
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
        when(groupService.update(ID, GROUP_EDIT_DTO)).thenReturn(GROUP_DTO);
        
        assertEquals(GROUP_DTO, groupController.update(ID, GROUP_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(groupService.update(ID, GROUP_EDIT_DTO)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.update(ID, GROUP_EDIT_DTO))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        groupController.delete(ID);
        
        verify(groupService).deleteById(ID);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(GroupNotFoundException.class).when(groupService).deleteById(ID);
        
        assertThatThrownBy(() -> groupController.delete(ID))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void getAllReturnValidResponse() {
        when(groupService.findAll()).thenReturn(List.of(GROUP_DTO));
        
        assertEquals(List.of(GROUP_DTO), groupController.getAll());
    }
    
    @Test
    public void getOneReturnValidResponse() {
        when(groupService.findById(ID)).thenReturn(GROUP_DTO);
        
        assertEquals(GROUP_DTO, groupController.getOne(ID));
    }
    
    @Test
    public void getOneReturnException() {
        when(groupService.findById(ID)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.getOne(ID))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void getStudentsReturnValidResponse() {
        when(groupService.findStudents(ID)).thenReturn(List.of(STUDENT_DTO));
        
        assertEquals(List.of(STUDENT_DTO), groupController.getStudents(ID));
    }
    
    @Test
    public void getStudentsReturnException() {
        when(groupService.findStudents(ID)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.getStudents(ID))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void getStudentsCountReturnValidResponse() {
        int count = anyInt();
        
        when(groupService.findStudentsCount(ID)).thenReturn(count);
        
        assertEquals(count, groupController.getStudentsCount(ID));
    }
    
    @Test
    public void getStudentsCountReturnException() {
        when(groupService.findStudentsCount(ID)).thenThrow(GroupNotFoundException.class);
        
        assertThatThrownBy(() -> groupController.getStudentsCount(ID))
                .isInstanceOf(GroupNotFoundException.class);
    }
}
