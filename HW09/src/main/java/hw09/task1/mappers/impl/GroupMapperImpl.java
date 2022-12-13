package hw09.task1.mappers.impl;

import hw09.task1.dto.GroupDto;
import hw09.task1.dto.GroupEditDto;
import hw09.task1.entities.Group;
import hw09.task1.mappers.GroupMapper;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YevhenKovalevskyi
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
