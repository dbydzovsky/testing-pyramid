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

import cz.dbydzovsky.testingpyramid.todo.system.components.Button;
import cz.dbydzovsky.testingpyramid.todo.system.pages.TodoPage;
import cz.dbydzovsky.testingpyramid.todo.system.pages.WebDriverWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.WebElement;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@AllArgsConstructor
@Builder
public class TodoRow {
    @Getter
    private int id;
    @Getter
    private String title;
    @Getter
    private String status;
    @Getter
    private WebElement update;
    @Getter
    private WebElement delete;
    private WebDriverWrapper wrapper;

    public TodoPage update() {
        this.update.click();
        this.wrapper.waitUntilPageFullyLoaded();
        return new TodoPage(wrapper);
    }
    public TodoPage delete() {
        this.delete.click();
        this.wrapper.waitUntilPageFullyLoaded();
        return new TodoPage(wrapper);
    }
}