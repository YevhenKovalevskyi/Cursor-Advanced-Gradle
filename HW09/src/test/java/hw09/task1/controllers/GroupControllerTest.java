package hw09.task1.controllers;

import hw09.task1.WatchmanExtension;
import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.entities.Teacher;
import hw09.task1.exceptions.GroupNotFoundException;
import hw09.task1.mappers.GroupMapper;
import hw09.task1.mappers.StudentMapper;
import hw09.task1.services.impl.GroupServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.stream.Stream;

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
    
    private static final Group GROUP = new Group();
    private static final Student STUDENT = new Student();
    
    {
        GROUP.setTeacher(new Teacher());
    }
    
    @Mock
    private GroupServiceImpl groupService;
    
    @InjectMocks
    private GroupController groupController;
    
    @Test
    public void createReturnValidResponse() {
        when(groupService.create(GROUP)).thenReturn(GROUP);
        assertEquals(GroupMapper.getForShow(GROUP), groupController.create(GROUP));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(groupService.update(1, GROUP)).thenReturn(GROUP);
        assertEquals(GroupMapper.getForShow(GROUP), groupController.update(1, GROUP));
    }
    
    @Test
    public void updateReturnException() {
        doThrow(GroupNotFoundException.class).when(groupService).update(1, GROUP);
        assertThatThrownBy(() -> groupController.update(1, GROUP))
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
        when(groupService.findAll()).thenReturn(Collections.singletonList(GROUP));
        assertEquals(Stream.of(GROUP).map(GroupMapper::getForShow).toList(), groupController.getAll());
    }
    
    @Test
    public void getOneReturnValidResponse() {
        when(groupService.findById(1)).thenReturn(GROUP);
        assertEquals(GroupMapper.getForShow(GROUP), groupController.getOne(1));
    }
    
    @Test
    public void getOneReturnException() {
        doThrow(GroupNotFoundException.class).when(groupService).findById(1);
        assertThatThrownBy(() -> groupController.getOne(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void getStudentsReturnValidResponse() {
        when(groupService.findStudents(1)).thenReturn(Collections.singletonList(STUDENT));
        assertEquals(Stream.of(STUDENT).map(StudentMapper::getForShowSingle).toList(), groupController.getStudents(1));
    }
    
    @Test
    public void getStudentsReturnException() {
        doThrow(GroupNotFoundException.class).when(groupService).findStudents(1);
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
        doThrow(GroupNotFoundException.class).when(groupService).findStudentsCount(1);
        assertThatThrownBy(() -> groupController.getStudentsCount(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
}
