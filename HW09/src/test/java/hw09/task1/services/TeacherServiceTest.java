package hw09.task1.services;

import hw09.task1.WatchmanExtension;
import hw09.task1.dto.GroupDto;
import hw09.task1.dto.StudentDto;
import hw09.task1.dto.TeacherDto;
import hw09.task1.dto.TeacherEditDto;
import hw09.task1.entities.Group;
import hw09.task1.entities.Student;
import hw09.task1.entities.Teacher;
import hw09.task1.exceptions.TeacherNotFoundException;
import hw09.task1.mappers.GroupMapper;
import hw09.task1.mappers.StudentMapper;
import hw09.task1.mappers.TeacherMapper;
import hw09.task1.repositories.TeacherRepository;
import hw09.task1.services.impl.TeacherServiceImpl;

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
    private static final TeacherDto TEACHER_DTO = new TeacherDto();
    private static final TeacherEditDto TEACHER_EDIT_DTO = new TeacherEditDto();
    private static final Group GROUP = new Group();
    private static final GroupDto GROUP_DTO = new GroupDto();
    private static final Student STUDENT = new Student();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private TeacherMapper teacherMapper;
    @Mock
    private GroupMapper groupMapper;
    @Mock
    private StudentMapper studentMapper;
    
    @InjectMocks
    private TeacherServiceImpl teacherService;
    
    @Test
    public void findByIdIfExistsReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        
        assertEquals(TEACHER, teacherService.findByIdIfExists(1));
    }
    
    @Test
    public void findByIdIfExistsReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.findByIdIfExists(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void createReturnValidResponse() {
        when(teacherMapper.toCreateEntity(TEACHER_EDIT_DTO)).thenReturn(TEACHER);
        when(teacherMapper.toDto(TEACHER)).thenReturn(TEACHER_DTO);
        when(teacherRepository.save(TEACHER)).thenReturn(TEACHER);
        
        assertEquals(TEACHER_DTO, teacherService.create(TEACHER_EDIT_DTO));
    }
    
    @Test
    public void updateReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        when(teacherMapper.toUpdateEntity(TEACHER, TEACHER_EDIT_DTO)).thenReturn(TEACHER);
        when(teacherMapper.toDto(TEACHER)).thenReturn(TEACHER_DTO);
        when(teacherRepository.save(TEACHER)).thenReturn(TEACHER);
        
        assertEquals(TEACHER_DTO, teacherService.update(1, TEACHER_EDIT_DTO));
    }

    @Test
    public void updateReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.update(1, TEACHER_EDIT_DTO))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void deleteReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        
        teacherService.deleteById(1);
        
        verify(teacherRepository).deleteById(1);
    }
    
    @Test
    public void deleteReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.deleteById(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findAllReturnValidResponse() {
        when(teacherRepository.findAll()).thenReturn(List.of(TEACHER));
        when(teacherMapper.toDto(TEACHER)).thenReturn(TEACHER_DTO);
        
        assertEquals(List.of(TEACHER_DTO), teacherService.findAll());
    }
    
    @Test
    public void findByIdReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        when(teacherMapper.toDto(TEACHER)).thenReturn(TEACHER_DTO);
        
        assertEquals(TEACHER_DTO, teacherService.findById(1));
    }
    
    @Test
    public void findByIdReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.findById(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findGroupsReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        when(groupMapper.toDto(GROUP)).thenReturn(GROUP_DTO);
        
        TEACHER.setGroups(List.of(GROUP));
        
        assertEquals(List.of(GROUP_DTO), teacherService.findGroups(1));
    }
    
    @Test
    public void findGroupsReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.findGroups(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findGroupsCountReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        
        int count = anyInt();
        TEACHER.setGroups(new ArrayList<>(count));
        
        assertEquals(count, teacherService.findGroupsCount(1));
    }
    
    @Test
    public void findGroupsCountReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.findGroupsCount(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findStudentsReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        when(studentMapper.toDto(STUDENT)).thenReturn(STUDENT_DTO);
        
        List<Group> groups = List.of(GROUP);
        groups.forEach(group -> group.setStudents(List.of(STUDENT)));
        TEACHER.setGroups(groups);
        
        assertEquals(List.of(STUDENT_DTO), teacherService.findStudents(1));
    }
    
    @Test
    public void findStudentsReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.findStudents(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
    
    @Test
    public void findStudentsCountReturnValidResponse() {
        when(teacherRepository.findById(1)).thenReturn(Optional.of(TEACHER));
        
        int count = anyInt();
        TEACHER.setGroups(new ArrayList<>(count));

        assertEquals(count, teacherService.findStudentsCount(1));
    }
    
    @Test
    public void findStudentsCountReturnException() {
        when(teacherRepository.findById(1)).thenReturn(Optional.empty());
        
        assertThatThrownBy(() -> teacherService.findStudentsCount(1))
                .isInstanceOf(TeacherNotFoundException.class);
    }
}
