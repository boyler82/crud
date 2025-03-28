package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void shouldMapToTask() {
//        GIVEN
        TaskDto taskDto = new TaskDto(
                1L,
                "Test title",
                "Test content"
        );
//        WHEN
    Task task = taskMapper.mapToTask(taskDto);
//        THEN
        assertEquals(1L, task.getId());
        assertEquals("Test title", task.getTitle());
        assertEquals("Test content", task.getContent());

    }

    @Test
    void shouldMapToTaskDto() {
        Task task = new Task(2L, "Title", "Content");

        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    void shouldMapToTaskDtoList() {
        List<Task> tasks = List.of(
                new Task(1L, "Title1", "Content1"),
                new Task(2L, "Title2", "Content2")
        );

        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        assertEquals(2, taskDtos.size());
        assertEquals("Title1", taskDtos.get(0).getTitle());
        assertEquals("Content2", taskDtos.get(1).getContent());
    }
}
