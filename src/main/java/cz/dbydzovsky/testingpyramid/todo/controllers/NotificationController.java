/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.controllers;

import cz.dbydzovsky.testingpyramid.todo.service.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class NotificationController {
    private final Sender sender;

    @GetMapping("/sentLog")
    public String todos(Model model) {
        model.addAttribute("logs", sender.getSentLog());
        return "sentLog";
    }
}
