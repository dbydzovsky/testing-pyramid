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

import lombok.RequiredArgsConstructor;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@RequiredArgsConstructor
public class AddNewTodoModal {
    private final WebDriverWrapper driver;

    public void setTodoText(String text) {
        driver.textInputById("exampleInputEmail1").setText(text);
    }

    public TodoPage submit() {
        driver.buttonByText("Add Todo").click();
        driver.waitUntilPageFullyLoaded();
        return new TodoPage(driver);
    }
}
