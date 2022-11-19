package hw07.task1.mappers;

import hw07.task1.dto.GroupDto;
import hw07.task1.dto.GroupLightDto;
import hw07.task1.entities.Group;

/**
 * @author YevhenKovalevskyi
 */
public class GroupMapper {
    
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
