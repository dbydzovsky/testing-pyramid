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
        model.addAttribute("todos", process(todoService.getAll()));
        return "todos";
    }

    @PostMapping("/todoNew")
    public String add(@RequestParam String todoItem, @RequestParam String status, Model model) {
        TodoEntity todoEntity = new TodoEntity(todoItem, status);
        todoEntity.setTodoItem(todoItem);
        todoEntity.setCompleted(status);
        todoService.save(todoEntity);
        return "redirect:/todos";
    }

    @PostMapping("/todoDelete/{id}")
    public String delete(@PathVariable long id, Model model) {
        todoService.deleteById(id);
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
        return "redirect:/todos";
    }

    public List<TodoEntity> process(List<TodoEntity> entities) {
        for (TodoEntity entity : entities) {
            entity.setTodoItem(checkTag(entity.getTodoItem()));
        }
        return entities;
    }

    private String checkTag(String todoItem) {
        String result = todoItem;
        if (todoItem.contains("{{now}}")) {
            Date now = new Date(System.currentTimeMillis());
            result = todoItem.replace("{{now}}", new SimpleDateFormat("yyyy-MM-dd hh:mm").format(now));
        }
        if (todoItem.contains("{{companyName}}")) {
            result = todoItem.replace("{{companyName}}", "MyCompany");
        }
        if (todoItem.contains("{{kalkulacka")) {
            // {{kalkulacka:1+1}}
            // {{kalkulacka:1-1}}
            // {{kalkulacka:2*6}}
            while (result.contains("{{kalkulacka")) {
                int start = result.indexOf("{{kalkulacka");
                int end = result.indexOf("}}", start);
                if (start == -1) break;
                String expression = result.substring(start + 13, end);
                String resultValue = evaluate(expression);
                result = result.replace(result.substring(start, end + 2), resultValue);
            }
        }
        return result;
    }

    private String evaluate(String expression) {
        // 1+1
        // 1-1
        // 2*6
        Integer firstNumber = Integer.parseInt(expression.substring(0, 1));
        String operator = expression.substring(1, 2);
        Integer secondNumber = Integer.parseInt(expression.substring(2, 3));
        return switch (operator) {
            case "+" -> String.valueOf(firstNumber + secondNumber);
            case "-" -> String.valueOf(firstNumber - secondNumber);
            case "/" -> String.valueOf(firstNumber / secondNumber);
            case "*" -> String.valueOf(firstNumber * secondNumber);
            default -> "Unrecognized operator '" + operator + "'";
        };
    }
}