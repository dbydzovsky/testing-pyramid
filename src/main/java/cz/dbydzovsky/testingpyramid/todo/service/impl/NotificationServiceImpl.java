/*
 * *************************************************************************
 * * Yaypay CONFIDENTIAL   2024
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 */

package cz.dbydzovsky.testingpyramid.todo.service.impl;

import cz.dbydzovsky.testingpyramid.todo.model.TodoEntity;
import cz.dbydzovsky.testingpyramid.todo.service.NotificationService;
import cz.dbydzovsky.testingpyramid.todo.service.Sender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final Sender sender;

    @Override
    public void onToDoCreated(TodoEntity entity) {
        // todo add dynamic text evaluations
        SecurityContext ctx = SecurityContextHolder.getContext();
        User user = (User) ctx.getAuthentication().getPrincipal();
        String email = user.getUsername();
        if (entity.getCompleted().equals("No")) {
            log.info("Sending notification email to {}", email);
            sender.send(email, "There is new task", entity.getTodoItem());
        }
    }

    @Override
    public void onToDoUpdated(TodoEntity entity) {
        // todo add dynamic text evaluations
        SecurityContext ctx = SecurityContextHolder.getContext();
        User user = (User) ctx.getAuthentication().getPrincipal();
        String email = user.getUsername();
        String subject;
        if (entity.getCompleted().equals("Yes")) {
            log.info("Sending notification email to {}", email);
            subject = "Task completed!";
        } else {
            subject = "Task is back in backlog";
        }
        sender.send(email, subject, entity.getTodoItem());
    }
}
