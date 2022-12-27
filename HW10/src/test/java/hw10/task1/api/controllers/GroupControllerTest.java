package hw10.task1.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw10.task1.WatchmanExtension;

import hw10.task1.controllers.GroupController;
import hw10.task1.dto.GroupDto;
import hw10.task1.dto.GroupEditDto;
import hw10.task1.dto.StudentDto;
import hw10.task1.exceptions.GroupNotFoundException;
import hw10.task1.messages.Messages;
import hw10.task1.services.GroupService;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * @author YevhenKovalevskyi
 */
@Slf4j
@ExtendWith(WatchmanExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(GroupController.class)
public class GroupControllerTest {
    
    private static final int ID = 1;
    private static final int GROUP_DTO_SIZE = 3;
    private static final String GROUP_NOT_FOUND = String.format(Messages.GROUP_NOT_FOUND.getOutMessage(), ID);
    
    private static final GroupDto GROUP_DTO = new GroupDto();
    private static final GroupEditDto GROUP_EDIT_DTO = new GroupEditDto();
    private static final StudentDto STUDENT_DTO = new StudentDto();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private GroupService groupService;
    
    @InjectMocks
    ObjectMapper objectMapper;
    
    @Test
    @DisplayName("API. Group. POST '/groups'. Valid. Created group")
    void createReturnValidResponse() throws Exception {
        when(groupService.create(GROUP_EDIT_DTO)).thenReturn(GROUP_DTO);
        
        mockMvc.perform(
                post("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(GROUP_EDIT_DTO))
                )
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(GROUP_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Group. PUT '/groups/{id}'. Valid. Updated group")
    void updateReturnValidResponse() throws Exception {
        when(groupService.update(ID, GROUP_EDIT_DTO)).thenReturn(GROUP_DTO);
        
        mockMvc.perform(
                put("/groups/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(GROUP_EDIT_DTO))
                )
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(GROUP_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Group. PUT '/groups/{id}'. Exception. Unknown group by ID")
    void updateReturnException() throws Exception {
        when(groupService.update(ID, GROUP_EDIT_DTO)).thenThrow(new GroupNotFoundException(GROUP_NOT_FOUND));
        
        mockMvc.perform(
                put("/groups/{id}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(GROUP_EDIT_DTO))
                )
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(GROUP_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Group. DELETE '/groups/{id}'. Valid. Deleted group")
    void deleteReturnValidResponse() throws Exception {
        mockMvc.perform(delete("/groups/{id}", ID))
                .andExpectAll(
                        status().isNoContent()
                );
    }
    
    @Test
    @DisplayName("API. Group. DELETE '/groups/{id}'. Exception. Unknown group by ID")
    void deleteReturnException() throws Exception {
        doThrow(new GroupNotFoundException(GROUP_NOT_FOUND)).when(groupService).deleteById(ID);
        
        mockMvc.perform(delete("/groups/{id}", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(GROUP_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups'. Valid. List of all groups")
    void getAllReturnValidResponse() throws Exception {
        when(groupService.findAll()).thenReturn(List.of(GROUP_DTO));
        
        mockMvc.perform(get("/groups"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups'. Valid. Empty list")
    void getAllEmptyDataReturnValidResponse() throws Exception {
        when(groupService.findAll()).thenReturn(new ArrayList<>());
        
        mockMvc.perform(get("/groups"))
                .andExpect(
                        jsonPath("$", hasSize(0))
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups/{id}'. Valid. Certain group by ID")
    void getOneReturnValidResponse() throws Exception {
        when(groupService.findById(ID)).thenReturn(GROUP_DTO);
        
        mockMvc.perform(get("/groups/{id}", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.size()").value(GROUP_DTO_SIZE)
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups/{id}'. Exception. Unknown group by ID")
    void getOneReturnException() throws Exception {
        when(groupService.findById(ID)).thenThrow(new GroupNotFoundException(GROUP_NOT_FOUND));
        
        mockMvc.perform(get("/groups/{id}", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(GROUP_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups/{id}/students'. Valid. List of all students by group ID")
    public void getStudentsReturnValidResponse() throws Exception {
        when(groupService.findStudents(ID)).thenReturn(List.of(STUDENT_DTO));
    
        mockMvc.perform(get("/groups/{id}/students", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups/{id}/students'. Exception. Unknown group by ID")
    public void getStudentsReturnException() throws Exception {
        when(groupService.findStudents(ID)).thenThrow(new GroupNotFoundException(GROUP_NOT_FOUND));
        
        mockMvc.perform(get("/groups/{id}/students", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(GROUP_NOT_FOUND)
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups/{id}/students/count'. Valid. Count of all students by group ID")
    public void getStudentsCountReturnValidResponse() throws Exception {
        int count = anyInt();
        
        when(groupService.findStudentsCount(ID)).thenReturn(count);
    
        mockMvc.perform(get("/groups/{id}/students/count", ID))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string(String.valueOf(count))
                );
    }
    
    @Test
    @DisplayName("API. Group. GET '/groups/{id}/students/count'. Exception. Unknown group by ID")
    public void getStudentsCountReturnException() throws Exception {
        when(groupService.findStudentsCount(ID)).thenThrow(new GroupNotFoundException(GROUP_NOT_FOUND));
    
        mockMvc.perform(get("/groups/{id}/students/count", ID))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.message").value(GROUP_NOT_FOUND)
                );
    }
}
