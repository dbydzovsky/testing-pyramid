/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.system;

import cz.dbydzovsky.testingpyramid.todo.system.app.AbstractUISystemTest;
import cz.dbydzovsky.testingpyramid.todo.system.components.todoTable.TodoTable;
import cz.dbydzovsky.testingpyramid.todo.system.pages.AddNewTodoModal;
import cz.dbydzovsky.testingpyramid.todo.system.pages.Entrypoint;
import cz.dbydzovsky.testingpyramid.todo.system.pages.TodoPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/09
 */
public class AddToDoSystemTest extends AbstractUISystemTest {
    @Test
    public void markAllToDoSDone() {
        TodoPage todoPage = Entrypoint.goToTodoPage();
        String newTodoText = "This is new todo from test";
        checkTodoCount(todoPage, 4);

        todoPage = createNewTodo(todoPage, newTodoText);

        TodoTable todoTable = checkTodoCount(todoPage, 5);
        assertThat(todoTable.getLast().getTitle()).isEqualTo(newTodoText);
    }

    private static TodoPage createNewTodo(TodoPage todoPage, String newTodoText) {
        AddNewTodoModal addTodoModal = todoPage.openAddTodoModal();
        addTodoModal.setTodoText(newTodoText);
        todoPage = addTodoModal.submit();
        return todoPage;
    }

    private TodoTable checkTodoCount(TodoPage todoPage, int expected) {
        TodoTable todoTable = todoPage.getTodoTable();
        assertThat(todoTable.size()).isEqualTo(expected);
        return todoTable;
    }
}
