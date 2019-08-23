package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.dto.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"Title","description");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(Long.valueOf(1), task.getId());
        assertEquals("Title", task.getTitle());
        assertEquals("description", task.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L,"Title","description");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(Long.valueOf(1), taskDto.getId());
        assertEquals("Title", taskDto.getTitle());
        assertEquals("description", taskDto.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"Title","description"));
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1, taskDtoList.size());
    }
}