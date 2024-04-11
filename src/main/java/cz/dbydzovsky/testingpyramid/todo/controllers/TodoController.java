package cz.dbydzovsky.testingpyramid.todo.controllers;

import cz.dbydzovsky.testingpyramid.todo.model.TodoEntity;
import cz.dbydzovsky.testingpyramid.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public String index() {
        return "index.html";
    }

    @GetMapping("/todos")
    public String todos(Model model) {
        model.addAttribute("todos", todoService.getAll());
        return "todos";
    }

    @PostMapping("/todoNew")
    public String add(@RequestParam String todoItem, @RequestParam String status, Model model) {
        TodoEntity todoEntity = new TodoEntity(todoItem, status);
        todoEntity.setTodoItem(todoItem);
        todoEntity.setCompleted(status);
        todoService.save(todoEntity);
        // todo maybe not show done ?
        model.addAttribute("todos", todoService.getAll());
        return "redirect:/todos";
    }

    @DeleteMapping("/todoDelete/{id}")
    public String delete(@PathVariable long id, Model model) {
        todoService.deleteById(id);
        model.addAttribute("todos", todoService.getAll());
        return "redirect:/todos";
    }

    @PostMapping("/todoUpdate/{id}")
    public String update(@PathVariable long id, Model model) {
        TodoEntity todoEntity = todoService.findById(id);
        // 2021-01-19 todo change to Yes after release
        if ("Yes".equals(todoEntity.getCompleted())) {
            todoEntity.setCompleted("No");
        } else {
            todoEntity.setCompleted("yes");
        }
        todoService.save(todoEntity);
        model.addAttribute("todos", todoService.getAll());
        return "redirect:/todos";
    }
}