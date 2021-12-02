package com.testproject.registration.services;

import java.util.concurrent.TimeoutException;

public interface SendMailer {
    void sendMail (String toAddress, String messageBody) throws TimeoutException;
}
