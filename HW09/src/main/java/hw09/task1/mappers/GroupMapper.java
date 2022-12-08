package hw09.task1.mappers;

import hw09.task1.dto.GroupDto;
import hw09.task1.dto.GroupLightDto;
import hw09.task1.entities.Group;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GroupMapper {
    
    public static Group getForUpdate(Integer id, Group currentGroup, Group newGroup) {
        newGroup.setCreatedAt(currentGroup.getCreatedAt());
        
        return Group.build(id, newGroup);
    }
    
    public static GroupDto getForShow(Group group) {
        return GroupDto.build(group);
    }
    
    public static GroupLightDto getForShowSingle(Group group) {
        return GroupLightDto.build(group);
    }
}
