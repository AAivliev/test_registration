package com.testproject.registration.services;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@AllArgsConstructor
public class SendMailerImpl implements SendMailer {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String toAddress, String messageBody) throws TimeoutException {
        if(shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setText(messageBody);
        mailSender.send(simpleMailMessage);

        log.info("Message sent to {}, body {}.", toAddress, messageBody);
    }

    @SneakyThrows
    protected static void sleep() {
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
    }

    protected static boolean shouldSleep() {
        return new Random().nextInt(10) == 1;
    }

    protected static boolean shouldThrowTimeout() {
        return new Random().nextInt(10) == 1;
    }
}
