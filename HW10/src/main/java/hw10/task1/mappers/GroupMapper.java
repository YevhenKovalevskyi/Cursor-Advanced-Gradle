package hw10.task1.mappers;

import hw10.task1.dto.GroupDto;
import hw10.task1.dto.GroupEditDto;
import hw10.task1.entities.Group;

import org.mapstruct.Mapper;

/**
 * @author YevhenKovalevskyi
 */
@Mapper(componentModel = "spring")
public interface GroupMapper {
    
    GroupDto toDto(Group group);
    Group toCreateEntity(GroupEditDto groupToCreate);
    Group toUpdateEntity(Group groupCurrent, GroupEditDto groupToUpdate);
}
