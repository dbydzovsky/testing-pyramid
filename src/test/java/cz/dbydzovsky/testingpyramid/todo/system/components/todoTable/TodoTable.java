/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.system.components.todoTable;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */


import cz.dbydzovsky.testingpyramid.todo.system.pages.TodoPage;
import cz.dbydzovsky.testingpyramid.todo.system.pages.WebDriverWrapper;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@RequiredArgsConstructor
public class TodoTable {
    private final WebDriverWrapper wrapper;
    private final List<TodoRow> rows;

    public static TodoTable findById(WebDriverWrapper driver, String id) {
        WebElement table = driver.getWebDriver().findElement(By.ById.id(id));
        WebElement headerRow = table.findElement(By.ByClassName.className("headerRow"));
        List<WebElement> headerCells = headerRow.findElements(By.ByClassName.className("cell"));
        Map<Integer, BiConsumer<TodoRow.TodoRowBuilder, WebElement>> mappingTable = new HashMap<>();
        for (int i = 0; i < headerCells.size(); i++) {
            WebElement headerCell = headerCells.get(i);
            switch (headerCell.getText()) {
                case "Id":
                    mappingTable.put(i, (builder, el) -> builder.id(Integer.parseInt(el.getText())));
                    break;
                case "Todo":
                    mappingTable.put(i, (builder, el) -> builder.title(el.getText()));
                    break;
                case "Status":
                    mappingTable.put(i, (builder, el) -> builder.status(el.getText()));
                    break;
                case "Update":
                    mappingTable.put(i, TodoRow.TodoRowBuilder::update);
                    break;
                case "Delete":
                    mappingTable.put(i, TodoRow.TodoRowBuilder::delete);
                    break;
                default:
                    throw new IllegalStateException("Unsupported column " + headerCell.getText() + " in a table with id " + id);
            }
        }

        List<TodoRow> result  = new ArrayList<>();
        List<WebElement> valueRows = table.findElements(By.className("cellRow"));
        for (WebElement valueRow : valueRows) {
            List<WebElement> cells = valueRow.findElements(By.ByClassName.className("cell"));
            TodoRow.TodoRowBuilder rowBuilder = new TodoRow.TodoRowBuilder();
            rowBuilder.wrapper(driver);
            for (int i = 0; i < cells.size(); i++) {
                WebElement cell = cells.get(i);
                mappingTable.get(i).accept(rowBuilder, cell);
            }
            result.add(rowBuilder.build());
        }
        return new TodoTable(driver, result);
    }


    public int size() {
        return rows.size();
    }
    public TodoPage deleteAll() {
        List<TodoRow> actualRows = rows;
        TodoPage todoPage = new TodoPage(wrapper);
        while(!actualRows.isEmpty()) {
            todoPage = actualRows.get(0).delete();
            actualRows = todoPage.getTodoTable().rows;
        }
        return todoPage;
    }

    public TodoRow getLast() {
        return rows.get(rows.size() - 1);
    }
}