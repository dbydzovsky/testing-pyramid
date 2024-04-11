package cz.dbydzovsky.testingpyramid.todo.service;

import cz.dbydzovsky.testingpyramid.todo.model.TodoEntity;
import cz.dbydzovsky.testingpyramid.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/09
 */
@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;

    @Test
    public void testDelete() {
        todoService.deleteById(1L);

        verify(todoRepository).deleteById(1L);
    }

    @Test
    public void testUpdate() {
        TodoEntity todo = mock(TodoEntity.class);

        todoService.save(todo);

        verify(todoRepository).save(todo);
    }

    @Test
    public void testFindAll() {
        TodoEntity expected = mock(TodoEntity.class);
        when(todoRepository.findAll()).thenReturn(List.of(expected));

        List<TodoEntity> result = todoService.getAll();

        assertEquals(1, result.size());
        assertEquals(expected, result.get(0));
    }

    @Test
    public void testFindById() {
        TodoEntity expected = mock(TodoEntity.class);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(expected));

        TodoEntity result = todoService.findById(1L);

        assertSame(expected, result);
    }

    @Test
    public void testFindByIdThrowsIfNotFound() {
        when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception e = assertThrows(NoSuchElementException.class, () -> {
            todoService.findById(1L);
        });

        assertThat(e.getMessage()).isEqualTo("No value present");
    }
}