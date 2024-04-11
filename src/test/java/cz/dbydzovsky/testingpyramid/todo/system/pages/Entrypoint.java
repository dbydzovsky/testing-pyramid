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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Paths;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
public class Entrypoint {

    public static LoginPage goToLoginPage() {
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
        WebDriverWrapper wrapper = new WebDriverWrapper(driver);
        driver.get(AbstractUISystemTest.getUrl());
        wrapper.waitUntilPageFullyLoaded();
        return new LoginPage(wrapper);
    }

    public static RootPage goToRootPage() {
        LoginPage loginPage = Entrypoint.goToLoginPage();
        return loginPage.login();
    }
    public static TodoPage goToTodoPage() {
        LoginPage loginPage = Entrypoint.goToLoginPage();
        return loginPage.login().goToTodoPage();
    }
}
