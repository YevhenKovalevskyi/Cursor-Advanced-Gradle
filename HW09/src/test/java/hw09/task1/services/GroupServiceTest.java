package hw09.task1.services;

import hw09.task1.WatchmanExtension;
import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.exceptions.GroupNotFoundException;
import hw09.task1.repositories.GroupRepository;
import hw09.task1.services.impl.GroupServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
public class GroupServiceTest {
    
    private static final Group GROUP = new Group();
    private static final Student STUDENT = new Student();
    
    @Mock
    private GroupRepository groupRepository;
    
    @InjectMocks
    private GroupServiceImpl groupService;
    
    @Test
    public void createReturnValidResponse() {
        when(groupRepository.save(Group.build(GROUP))).thenReturn(GROUP);
        assertEquals(GROUP, groupService.create(GROUP));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        when(groupRepository.save(Group.build(1, GROUP))).thenReturn(GROUP);
        assertEquals(GROUP, groupService.update(1, GROUP));
    }
    
    @Test
    public void updateReturnException() {
        doThrow(GroupNotFoundException.class).when(groupRepository).findById(1);
        assertThatThrownBy(() -> groupService.update(1, GROUP))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        
        groupService.deleteById(1);
        
        verify(groupRepository).deleteById(1);
    }
    
    @Test
    public void deleteReturnException() {
        doThrow(GroupNotFoundException.class).when(groupRepository).findById(1);
        assertThatThrownBy(() -> groupService.deleteById(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void findAllReturnValidResponse() {
        when(groupRepository.findAll()).thenReturn(List.of(GROUP));
        assertEquals(List.of(GROUP), groupService.findAll());
    }
    
    @Test
    public void findByIdReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        assertEquals(GROUP, groupService.findById(1));
    }
    
    @Test
    public void findByIdReturnException() {
        doThrow(GroupNotFoundException.class).when(groupRepository).findById(1);
        assertThatThrownBy(() -> groupService.findById(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void findStudentsReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        
        GROUP.setStudents(List.of(STUDENT));
        
        assertEquals(List.of(STUDENT), groupService.findStudents(1));
    }
    
    @Test
    public void findStudentsReturnException() {
        doThrow(GroupNotFoundException.class).when(groupRepository).findById(1);
        assertThatThrownBy(() -> groupService.findStudents(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void findStudentsCountReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        
        int count = anyInt();
        GROUP.setStudents(new ArrayList<>(count));
        
        assertEquals(count, groupService.findStudentsCount(1));
    }
    
    @Test
    public void findStudentsCountReturnException() {
        doThrow(GroupNotFoundException.class).when(groupRepository).findById(1);
        assertThatThrownBy(() -> groupService.findStudentsCount(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
}
