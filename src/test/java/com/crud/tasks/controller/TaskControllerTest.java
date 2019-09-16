package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;


    @Test
    public void shodFetchTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title","content");
        Task task = new Task(1L,"Title","content");

        when(service.getTask(task.getId())).thenReturn(Optional.ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then

        mockMvc.perform(get("/v1/tasks/{taskId}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shodFetchTasks() throws Exception {
        //Given
        List<TaskDto> tasksList =new ArrayList<>();
        tasksList.add(new TaskDto(1L,"Title","content"));
        tasksList.add(new TaskDto(2L,"Title2","content2"));

        when(taskMapper.mapToTaskDtoList(any())).thenReturn(tasksList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Trello  board fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title",is("Title")))
                .andExpect(jsonPath("$[0].content",is("content")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title",is("Title2")))
                .andExpect(jsonPath("$[1].content",is("content2")));
    }

    @Test
    public void shodDeleteTask() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/tasks/{taskId}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void shouldCreateFetchTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto((long) 1, "task", "content");

        Task task = new Task((long) 1, "task", "content");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String createTask = gson.toJson(task);
        //When & Then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(createTask))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(
                1L,
                "Test title",
                "Test title");

        TaskDto taskDto = new TaskDto(
                1L,
                "Test title",
                "Test title");

        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Given
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test title")))
                .andExpect(jsonPath("$.content", is("Test title")));
    }

}