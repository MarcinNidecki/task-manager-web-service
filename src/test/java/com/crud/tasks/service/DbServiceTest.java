package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTest {
    @Autowired
    private DbService dbService;

    @Test
    public void SaveTaskAndGetAllTask() {
        //Given
        long prevNumOfRecords = dbService.getAllTasks().size();
        Task task = new Task("Test","Test A");
        Task task2 = new Task("Test 2","Test B");

        //When
        List<Task> taskList = dbService.getAllTasks();
        dbService.saveTask(task);
        dbService.saveTask(task2);
        long taskId = task.getId();
        long task2Id = task2.getId();
        long afterNumOfRecords =  dbService.getAllTasks().size();
        //Then
        assertNotNull(taskList);
        assertEquals(2, afterNumOfRecords - prevNumOfRecords);

        //Clean up
        dbService.deleteTask(taskId);
        dbService.deleteTask(task2Id);
    }

    @Test
    public void deleteTask() {
        //Given
        long prevNumOfRecords = dbService.getAllTasks().size();
        Task task = new Task("Test 2","Test B");

        //When
        dbService.saveTask(task);
        int numberOfRecordsAfterSave = dbService.getAllTasks().size();
        long taskId = task.getId();

        dbService.deleteTask(taskId);
        int numberOfRecordsAfterDelete = dbService.getAllTasks().size();
        //Then
        assertEquals(1, numberOfRecordsAfterSave - prevNumOfRecords);
        assertEquals(0, numberOfRecordsAfterDelete - prevNumOfRecords);

    }

    @Test
    public void getTask() {
        //Given
        Task task = new Task("Title", "Content");
        dbService.saveTask(task);
        long taskId = task.getId();
        //When
        Task expectingTask = dbService.getTask(taskId).orElse(null);
        //Then
        assertNotNull(expectingTask);
        assertEquals("Title", expectingTask.getTitle());
        assertEquals("Content", expectingTask.getContent());
        //Clean up
        dbService.deleteTask(taskId);
    }
}