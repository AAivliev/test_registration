package com.testproject.registration.services;


import com.testproject.registration.dto.UserRegistrationDto;
import org.springframework.messaging.Message;

import java.util.concurrent.TimeoutException;

public interface MessagingService {

    /**
     * Отправка сообщения в шину.
     *
     * @param userRegistrationDto сообщение для отправки.
     */
    void send(UserRegistrationDto userRegistrationDto);

    /**
     * Встает на ожидание ответа по сообщению с messageId.
     *
     * Редко, но может кинуть исключение по таймауту.
     *
     * @param userRegistrationDto дтошка возвращаемая с маркером ответа
     * @return Тело ответа.
     */
    UserRegistrationDto receive(UserRegistrationDto userRegistrationDto) throws TimeoutException;
}
