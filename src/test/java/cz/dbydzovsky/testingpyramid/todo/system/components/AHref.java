/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.system.components;

import cz.dbydzovsky.testingpyramid.todo.system.pages.WebDriverWrapper;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@RequiredArgsConstructor
public class AHref {
    private final WebElement webElement;

    public static AHref findByText(WebDriverWrapper driver, String text) {
        WebElement webElement = driver.getWebDriver().findElement(By.xpath("//a[normalize-space()='" + text + "']"));
        return new AHref(webElement);
    }

    public void click() {
        webElement.click();
    }

    public String getHref() {
        return webElement.getAttribute("href");
    }
}
