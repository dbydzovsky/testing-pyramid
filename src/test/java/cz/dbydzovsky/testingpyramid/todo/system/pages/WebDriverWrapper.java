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

import cz.dbydzovsky.testingpyramid.todo.system.components.AHref;
import cz.dbydzovsky.testingpyramid.todo.system.components.Button;
import cz.dbydzovsky.testingpyramid.todo.system.components.TextInput;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@RequiredArgsConstructor
public class WebDriverWrapper {
    @Getter

    private final WebDriver webDriver;

    public void waitUntilPageFullyLoaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public TextInput textInputById(String id) {
        return TextInput.byId(this, id);
    }

    public Button buttonByText(String text) {
        return Button.findByText(this, text);
    }

    public AHref aHrefByText(String text) {
        return AHref.findByText(this, text);
    }
}
