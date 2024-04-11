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
public class LoginPage {
    private final WebDriverWrapper driver;
    private final static String USERNAME = "admin";
    private final static String PASSWORD = "pass";

    public RootPage login() {
        driver.textInputById("username").setText(USERNAME);
        driver.textInputById("password").setText(PASSWORD);
        driver.buttonByText("Sign in").click();
        driver.waitUntilPageFullyLoaded();
        return new RootPage(driver);
    }
}
