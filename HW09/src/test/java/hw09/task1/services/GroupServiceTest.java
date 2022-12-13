package hw09.task1.services;

import hw09.task1.WatchmanExtension;
import hw09.task1.dto.GroupDto;
import hw09.task1.dto.GroupEditDto;
import hw09.task1.dto.StudentDto;
import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.exceptions.GroupNotFoundException;
import hw09.task1.mappers.GroupMapper;
import hw09.task1.mappers.StudentMapper;
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
    private static final GroupDto GROUP_DTO = new GroupDto();
    private static final GroupEditDto GROUP_EDIT_DTO = new GroupEditDto();
    private static final Student STUDENT = new Student();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private GroupMapper groupMapper;
    @Mock
    private StudentMapper studentMapper;
    
    @InjectMocks
    private GroupServiceImpl groupService;
    
    @Test
    public void findByIdIfExistsReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        
        assertEquals(GROUP, groupService.findByIdIfExists(1));
    }
    
    @Test
    public void findByIdIfExistsReturnException() {
        when(groupRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> groupService.findByIdIfExists(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void createReturnValidResponse() {
        when(groupMapper.toCreateEntity(GROUP_EDIT_DTO)).thenReturn(GROUP);
        when(groupMapper.toDto(GROUP)).thenReturn(GROUP_DTO);
        when(groupRepository.save(GROUP)).thenReturn(GROUP);
        
        assertEquals(GROUP_DTO, groupService.create(GROUP_EDIT_DTO));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        when(groupMapper.toUpdateEntity(GROUP, GROUP_EDIT_DTO)).thenReturn(GROUP);
        when(groupMapper.toDto(GROUP)).thenReturn(GROUP_DTO);
        when(groupRepository.save(GROUP)).thenReturn(GROUP);
        
        assertEquals(GROUP_DTO, groupService.update(1, GROUP_EDIT_DTO));
    }
    
    @Test
    public void updateReturnException() {
        when(groupRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> groupService.update(1, GROUP_EDIT_DTO))
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
        when(groupRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> groupService.deleteById(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void findAllReturnValidResponse() {
        when(groupRepository.findAll()).thenReturn(List.of(GROUP));
        when(groupMapper.toDto(GROUP)).thenReturn(GROUP_DTO);
        
        assertEquals(List.of(GROUP_DTO), groupService.findAll());
    }
    
    @Test
    public void findByIdReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        when(groupMapper.toDto(GROUP)).thenReturn(GROUP_DTO);
        
        assertEquals(GROUP_DTO, groupService.findById(1));
    }
    
    @Test
    public void findByIdReturnException() {
        when(groupRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> groupService.findById(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
    
    @Test
    public void findStudentsReturnValidResponse() {
        when(groupRepository.findById(1)).thenReturn(Optional.of(GROUP));
        when(studentMapper.toDto(STUDENT)).thenReturn(STUDENT_DTO);
        
        GROUP.setStudents(List.of(STUDENT));
        
        assertEquals(List.of(STUDENT_DTO), groupService.findStudents(1));
    }
    
    @Test
    public void findStudentsReturnException() {
        when(groupRepository.findById(1)).thenReturn(Optional.empty());
        
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
        when(groupRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> groupService.findStudentsCount(1))
                .isInstanceOf(GroupNotFoundException.class);
    }
}
