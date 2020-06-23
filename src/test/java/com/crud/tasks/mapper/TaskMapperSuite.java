package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperSuite {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //given
        TaskDto taskDto1 = new TaskDto(1L, "taskdto1", "1");
        //when
        Task task1 = taskMapper.mapToTask(taskDto1);
        //Then
        assertEquals(taskDto1.getId(), task1.getId());
        assertEquals("taskdto1", task1.getTitle());
        assertEquals("1", task1.getContent());
    }

    @Test
    public void testMapToTaskDto() {
        //given
        Task task1 = new Task(1L, "task1", "1");
        //when
        TaskDto taskDto1 = taskMapper.mapToTaskDto(task1);
        //Then
        assertEquals(taskDto1.getId(), task1.getId());
        assertEquals("task1", taskDto1.getTitle());
        assertEquals("1", taskDto1.getContent());
    }

    @Test
    public void testMapToTaskDtoList() {
        //given
        Task task1 = new Task(1L, "task1", "czesc");
        Task task2 = new Task(2L, "task2", "hej");
        List<Task> tasklist = Arrays.asList(task1, task2);
        //when
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasklist);
        Optional<Long> id = Optional.ofNullable(taskDtoList.get(1).getId());
        //Then
        assertEquals(2, taskDtoList.size());
        assertEquals("task1", taskDtoList.get(0).getTitle());
        assertEquals(task1.getId(), taskDtoList.get(0).getId());
        assertEquals("hej", taskDtoList.get(1).getContent());
    }
}
