package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    void shouldGetAllTasks() {
        //Given
        List<Task> tasks = List.of(new Task(1L, "Title1", "Content1"));
        when(repository.findAll()).thenReturn(tasks);
        //When
        List<Task> result = dbService.getAllTasks();
        //Then
        assertEquals(1, result.size());
        assertEquals("Title1", result.getFirst().getTitle());
        verify(repository, times(1)).findAll();

    }

    @Test
    void shouldGetTask() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L,"Title1","Content1");
        List<Task> tasks = List.of(task);
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        //When
        Task result = dbService.getTask(1l);

        //Then
        assertEquals("Title1", result.getTitle());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTask(1L));
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void shouldSaveTask() {
        // Given
        Task taskToSave = new Task(null, "Title", "Content");
        Task savedTask = new Task(1L, "Title", "Content");
        when(repository.save(taskToSave)).thenReturn(savedTask);

        // When
        Task result = dbService.saveTask(taskToSave);

        // Then
        assertNotNull(result.getId());
        assertEquals("Title", result.getTitle());
        verify(repository, times(1)).save(taskToSave);
    }

    @Test
    void shouldDeleteTask() {
        // Given
        Long taskId = 1L;

        // When
        dbService.deleteTask(taskId);

        // Then
        verify(repository, times(1)).deleteById(taskId);
    }
}