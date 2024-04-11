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

import cz.dbydzovsky.testingpyramid.todo.system.app.AbstractSystemTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/09
 */
public class CanLoginSystemTest {
    @Test
    public void markAllToDoSDone() {
        String driverPath = Paths.get("chromedriver.exe").toString();
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();
        AbstractSystemTest.setUp();
        driver.get(AbstractSystemTest.getUrl());
        waitUntilPageFullyLoaded(driver);

        WebElement usernameElement = driver.findElement(By.ById.id("username"));
        usernameElement.sendKeys("admin");
        WebElement passwordElement = driver.findElement(By.ById.id("password"));
        passwordElement.sendKeys("pass");
        WebElement loginBtn = driver.findElement(By.xpath("//button[normalize-space()='Sign in']"));
        loginBtn.click();

        waitUntilPageFullyLoaded(driver);
        WebElement viewToDosBtn = driver.findElement(By.xpath("//button[normalize-space()='View Todos']"));
        assertTrue(viewToDosBtn.isDisplayed());
    }

    private void waitUntilPageFullyLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
