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

import cz.dbydzovsky.testingpyramid.todo.service.Sender;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * Author : Bydzovsky Dominik
 * Date Created: 2024/04/11
 */
@Slf4j
@Getter
@Service
public class SenderImpl implements Sender {
    private final List<String> sentLog = new ArrayList<>();

    @Override
    public void send(String recipient, String subject, String message) {
        sentLog.add(String.format("Email to %s with subject %s, message: %s", recipient, subject, message));
        log.info("Calling external service");
    }
}
