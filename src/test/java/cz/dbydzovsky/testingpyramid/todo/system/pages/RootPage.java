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

import cz.dbydzovsky.testingpyramid.todo.system.app.AbstractUISystemTest;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Paths;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@RequiredArgsConstructor
public class RootPage {
    private final WebDriverWrapper webDriver;

    public TodoPage goToTodoPage() {
        webDriver.buttonByText("View Todos").click();
        this.webDriver.waitUntilPageFullyLoaded();
        return new TodoPage(this.webDriver);
    }
}
