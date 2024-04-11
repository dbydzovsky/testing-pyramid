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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
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
public class AddToDoSystemTest extends AbstractSystemTest{
    @Test
    public void markAllToDoSDone() {
        ChromeOptions options = new ChromeOptions();
        if (!System.getProperty("os.name").startsWith("Windows")) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            String driverPath = Paths.get("chromedriver-linux").toString();
            File file = new File(driverPath);
            file.setExecutable(true);
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            String driverPath = Paths.get("chromedriver.exe").toString();
            System.setProperty("webdriver.chrome.driver", driverPath);
        }
        WebDriver driver = new ChromeDriver(options);
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
        viewToDosBtn.click();
        waitUntilPageFullyLoaded(driver);
        List<Map<String, WebElement>> table = parseTodoTable(driver);
        assertTrue(table.size() == 4);

        WebElement addNewToDoOpenModalBtn = driver.findElement(By.xpath("//a[normalize-space()='Add New Todo']"));
        addNewToDoOpenModalBtn.click();
        WebElement todoTextInput = driver.findElement(By.ById.id("exampleInputEmail1"));
        todoTextInput.sendKeys("This is new todo from test");
        WebElement addNewToDoBtn = driver.findElement(By.xpath("//button[normalize-space()='Add Todo']"));
        addNewToDoBtn.click();
        waitUntilPageFullyLoaded(driver);
        table = parseTodoTable(driver);
        assertTrue(table.size() == 5);
        assertTrue(table.get(4).get("Todo").getText().equals("This is new todo from test"));

        AbstractSystemTest.tearDown();
        driver.close();
    }

    private List<Map<String, WebElement>> parseTodoTable(WebDriver driver) {
        WebElement table = driver.findElement(By.className("table"));
        List<Map<String, WebElement>> result = new ArrayList<>();

        // Find all the rows in the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            // Find the cells in the current row
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int index = 1; // skip the left column with index

            Map<String, WebElement> rowMap = new HashMap<>();
            for (WebElement cell : cells) {
                switch (index) {
                    case 1:
                        rowMap.put("Todo", cell);
                        break;
                    case 2:
                        rowMap.put("Status", cell);
                        break;
                    case 3:
                        rowMap.put("Update", cell.findElement(By.tagName("button")));
                        break;
                    case 4:
                        rowMap.put("Delete", cell.findElement(By.tagName("button")));
                        break;
                }
                index++;
            }
            if (!rowMap.isEmpty()) {
                result.add(rowMap);
            }

        }
        return result;
    }

    private void waitUntilPageFullyLoaded(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
}
