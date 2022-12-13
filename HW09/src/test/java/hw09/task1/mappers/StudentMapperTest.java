package hw09.task1.mappers;

import hw09.task1.WatchmanExtension;
import hw09.task1.dto.StudentDto;
import hw09.task1.dto.StudentEditDto;
import hw09.task1.entities.Student;
import hw09.task1.mappers.impl.StudentMapperImpl;

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
public class StudentMapperTest {
    
    private static final Student STUDENT = new Student();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    private static final StudentEditDto STUDENT_EDIT_DTO = new StudentEditDto();
    private static final Class<Student> GROUP_CLASS = Student.class;
    private static final Class<StudentDto> GROUP_DTO_CLASS = StudentDto.class;
    
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private StudentMapperImpl studentMapper;
    
    @Test
    public void toDtoReturnValidResponse() {
        when(modelMapper.map(STUDENT, GROUP_DTO_CLASS)).thenReturn(STUDENT_DTO);
        
        assertEquals(STUDENT_DTO, studentMapper.toDto(STUDENT));
    }
    
    @Test
    public void toCreateEntityReturnValidResponse() {
        when(modelMapper.map(STUDENT_EDIT_DTO, GROUP_CLASS)).thenReturn(STUDENT);
        
        assertEquals(STUDENT, studentMapper.toCreateEntity(STUDENT_EDIT_DTO));
    }
    
    @Test
    public void toUpdateEntityReturnValidResponse() {
        when(modelMapper.map(STUDENT_EDIT_DTO, GROUP_CLASS)).thenReturn(STUDENT);
        
        assertEquals(STUDENT, studentMapper.toUpdateEntity(STUDENT, STUDENT_EDIT_DTO));
    }
}
