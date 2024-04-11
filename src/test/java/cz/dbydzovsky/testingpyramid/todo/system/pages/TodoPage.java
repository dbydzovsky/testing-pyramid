/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.system.pages;

import cz.dbydzovsky.testingpyramid.todo.system.components.Button;
import cz.dbydzovsky.testingpyramid.todo.system.components.todoTable.TodoTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@RequiredArgsConstructor
public class TodoPage {
    private final WebDriverWrapper webDriver;

    public AddNewTodoModal openAddTodoModal() {
        webDriver.aHrefByText("Add New Todo").click();
        return new AddNewTodoModal(webDriver);
    }

    public TodoTable getTodoTable() {
        return TodoTable.findById(webDriver, "todoTable");
    }
}
