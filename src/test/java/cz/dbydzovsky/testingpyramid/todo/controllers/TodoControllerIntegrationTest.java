package cz.dbydzovsky.testingpyramid.todo.controllers;

import cz.dbydzovsky.testingpyramid.todo.model.TodoEntity;
import cz.dbydzovsky.testingpyramid.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/05
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WithMockUser(username = "testUser")
class TodoControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoRepository todoRepository;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("LOGO ToDo APP")));
    }

    @Test
    public void testCreateNewToDo() throws Exception {
        mockMvc.perform(post("/todoNew")
                        .param("todoItem", "df")
                        .param("status", "NotExisting")
                        .with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl("/todos"));

        assertThat(todoRepository.findById(5L).get()).extracting(TodoEntity::getTodoItem).isEqualTo("df");
        assertThat(todoRepository.findById(5L).get()).extracting(TodoEntity::getCompleted).isEqualTo("NotExisting");
    }

    @Test
    public void testDeleteTodo() throws Exception {
        mockMvc.perform(post("/todoDelete/1").with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl("/todos"));

        assertThat(todoRepository.count()).isEqualTo(3);
    }

    @Test
    public void testUpdateToDo() throws Exception {
        TodoEntity original = todoRepository.findById(1L).get();

        mockMvc.perform(post("/todoUpdate/1").with(csrf()))
                .andDo(print())
                .andExpect(redirectedUrl("/todos"));

        assertThat(original.getCompleted()).isEqualTo("Yes");
        assertThat(todoRepository.findById(1L).get().getCompleted()).isEqualTo("No");
    }
}