package hw07.task1.mappers.impl;

import hw07.task1.dto.GroupDto;
import hw07.task1.dto.GroupEditDto;
import hw07.task1.entities.Group;
import hw07.task1.mappers.GroupMapper;

import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YevhenKovalevskyi
 */
@AllArgsConstructor
@Component
public class GroupMapperImpl implements GroupMapper {
    
    private ModelMapper modelMapper;
    
    public GroupDto toDto(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }
    
    public Group toCreateEntity(GroupEditDto groupToCreate) {
        Group group = modelMapper.map(groupToCreate, Group.class);
        group.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        group.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return group;
    }
    
    public Group toUpdateEntity(Group groupCurrent, GroupEditDto groupToUpdate) {
        Group group = modelMapper.map(groupToUpdate, Group.class);
        group.setId(groupCurrent.getId());
        group.setCreatedAt(groupCurrent.getCreatedAt());
        group.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        return group;
    }
}
