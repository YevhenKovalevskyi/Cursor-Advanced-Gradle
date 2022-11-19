package hw07.task1.dto;

import hw07.task1.entities.Group;

import lombok.Builder;
import lombok.Data;

/**
 * @author YevhenKovalevskyi
 */
@Data
@Builder
public class GroupLightDto {
    
    private Integer id;
    private String name;
    
    public static GroupLightDto build(Group group) {
        return GroupLightDto.builder()
                .id(group.getId())
                .name(group.getName())
                .build();
    }
}
