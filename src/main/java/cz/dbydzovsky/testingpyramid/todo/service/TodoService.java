/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.service;

import cz.dbydzovsky.testingpyramid.todo.model.TodoEntity;
import cz.dbydzovsky.testingpyramid.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/05
 */
@Component
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoEntity> getAll() {
        return StreamSupport.stream(todoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void save(TodoEntity todoEntity) {
        todoRepository.save(todoEntity);
    }

    public void deleteById(long id) {
        todoRepository.deleteById(id);
    }

    public TodoEntity findById(long id) {
        // todo this sometimes throws I don't know why
        return todoRepository.findById(id).get();
    }
}
