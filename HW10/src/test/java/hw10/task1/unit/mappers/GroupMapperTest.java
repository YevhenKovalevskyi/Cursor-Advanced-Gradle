package hw10.task1.unit.mappers;

import hw10.task1.WatchmanExtension;
import hw10.task1.dto.GroupDto;
import hw10.task1.dto.GroupEditDto;
import hw10.task1.entities.Group;
import hw10.task1.mappers.impl.GroupMapperImpl;

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
public class GroupMapperTest {
    
    private static final Group GROUP = new Group();
    private static final GroupDto GROUP_DTO = new GroupDto();
    private static final GroupEditDto GROUP_EDIT_DTO = new GroupEditDto();
    private static final Class<Group> GROUP_CLASS = Group.class;
    private static final Class<GroupDto> GROUP_DTO_CLASS = GroupDto.class;
    
    @Mock
    private ModelMapper modelMapper;
    
    @InjectMocks
    private GroupMapperImpl groupMapper;
    
    @Test
    public void toDtoReturnValidResponse() {
        when(modelMapper.map(GROUP, GROUP_DTO_CLASS)).thenReturn(GROUP_DTO);
        
        assertEquals(GROUP_DTO, groupMapper.toDto(GROUP));
    }
    
    @Test
    public void toCreateEntityReturnValidResponse() {
        when(modelMapper.map(GROUP_EDIT_DTO, GROUP_CLASS)).thenReturn(GROUP);
        
        assertEquals(GROUP, groupMapper.toCreateEntity(GROUP_EDIT_DTO));
    }
    
    @Test
    public void toUpdateEntityReturnValidResponse() {
        when(modelMapper.map(GROUP_EDIT_DTO, GROUP_CLASS)).thenReturn(GROUP);
        
        assertEquals(GROUP, groupMapper.toUpdateEntity(GROUP, GROUP_EDIT_DTO));
    }
}
