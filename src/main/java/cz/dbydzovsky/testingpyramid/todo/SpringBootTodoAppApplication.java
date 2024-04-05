package cz.dbydzovsky.testingpyramid.todo;

import java.util.Arrays;
import java.util.List;

import cz.dbydzovsky.testingpyramid.todo.model.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cz.dbydzovsky.testingpyramid.todo.repository.TodoRepository;

@SpringBootApplication
public class SpringBootTodoAppApplication implements CommandLineRunner {
    @Autowired
    public TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTodoAppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        TodoEntity test = TodoEntity.builder().id(10).completed("its completed").todoItem("python ML").build();
        System.out.println(test.toString());
        List<TodoEntity> todoEntities = Arrays.asList(new TodoEntity("Learn Spring", "Yes"), new TodoEntity("Learn Driving", "No"), new TodoEntity("Go for a Walk", "No"), new TodoEntity("Cook Dinner", "Yes"));
        todoRepository.saveAll(todoEntities);
    }
}
