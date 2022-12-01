package hw09.task1.controllers;

import hw09.task1.WatchmanExtension;
import hw09.task1.entities.Group;
import hw09.task1.mappers.GroupMapper;
import hw09.task1.services.GroupService;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@ExtendWith(WatchmanExtension.class)
@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {
    
    private static final Group ENTITY = new Group();
    
    @Mock
    private GroupService groupService;
    
    @InjectMocks
    private GroupController groupController;
    
    @Test
    public void getOneReturnValidResponse() {
        when(groupService.findById(1)).thenReturn(ENTITY);
        assertEquals(GroupMapper.getForShow(ENTITY), groupController.getOne(1));
    }
    
    @Test
    public void getAllReturnValidResponse() {
        when(groupService.findAll()).thenReturn(Collections.singletonList(ENTITY));
        assertEquals(Stream.of(ENTITY).map(GroupMapper::getForShow).toList(), groupController.getAll());
    }
}
