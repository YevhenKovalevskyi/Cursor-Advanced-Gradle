package hw07.task1.mappers;

import hw07.task1.dto.GroupDto;
import hw07.task1.dto.GroupEditDto;
import hw07.task1.entities.Group;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface GroupMapper {
    
    GroupDto toDto(Group group);
    Group toCreateEntity(GroupEditDto groupDto);
    Group toUpdateEntity(Group currentGroup, GroupEditDto groupDto);
}
