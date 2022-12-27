package hw10.task1.unit.mappers;

import hw10.task1.WatchmanExtension;
import hw10.task1.dto.TeacherDto;
import hw10.task1.dto.TeacherEditDto;
import hw10.task1.entities.Teacher;
import hw10.task1.mappers.impl.TeacherMapperImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author YevhenKovalevskyi
 */
@ExtendWith(WatchmanExtension.class)
@ExtendWith(MockitoExtension.class)
public class TeacherMapperTest {
    
    private static final Teacher TEACHER = new Teacher();
    private static final TeacherDto TEACHER_DTO = new TeacherDto();
    private static final TeacherEditDto TEACHER_EDIT_DTO = new TeacherEditDto();
    private static final Class<Teacher> TEACHER_CLASS = Teacher.class;
    private static final Class<TeacherDto> TEACHER_DTO_CLASS = TeacherDto.class;
    
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private TeacherMapperImpl teacherMapper;
    
    @Test
    public void toDtoReturnValidResponse() {
        when(modelMapper.map(TEACHER, TEACHER_DTO_CLASS)).thenReturn(TEACHER_DTO);
        
        assertEquals(TEACHER_DTO, teacherMapper.toDto(TEACHER));
    }
    
    @Test
    public void toCreateEntityReturnValidResponse() {
        when(modelMapper.map(TEACHER_EDIT_DTO, TEACHER_CLASS)).thenReturn(TEACHER);
        
        assertEquals(TEACHER, teacherMapper.toCreateEntity(TEACHER_EDIT_DTO));
    }
    
    @Test
    public void toUpdateEntityReturnValidResponse() {
        when(modelMapper.map(TEACHER_EDIT_DTO, TEACHER_CLASS)).thenReturn(TEACHER);
        
        assertEquals(TEACHER, teacherMapper.toUpdateEntity(TEACHER, TEACHER_EDIT_DTO));
    }
}
