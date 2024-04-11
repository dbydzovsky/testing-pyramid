/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.system.app;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/09
 */

import cz.dbydzovsky.testingpyramid.todo.SpringBootTodoAppApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class AbstractSystemTest {
    public static final Integer APP_PORT = 8989;
    private static ConfigurableApplicationContext context;

    public static void setUp() {
        context = SpringApplication.run(SpringBootTodoAppApplication.class, "--server.port=" + APP_PORT);
    }

    public static void tearDown() {
        // Stop application
        context.stop();
    }

    public static String getUrl() {
        return "http://localhost:" + APP_PORT;
    }
}
