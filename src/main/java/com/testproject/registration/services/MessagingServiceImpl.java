package com.testproject.registration.services;

import com.testproject.registration.config.RabbitConfiguration;
import com.testproject.registration.dto.UserRegistrationDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeoutException;

import static com.testproject.registration.services.SendMailerImpl.*;

@AllArgsConstructor
@Service
public class MessagingServiceImpl implements MessagingService {

    private final RabbitTemplate rabbitTemplate;
    private final RegistrationService registrationService;


    @Override
    public void send(UserRegistrationDto userRegistrationDto) {
        rabbitTemplate.convertAndSend(RabbitConfiguration.ACCEPT_REGISTRATION_QUEUE,userRegistrationDto);
    }


    @RabbitListener(queues = RabbitConfiguration.ACCEPT_REGISTRATION_QUEUE)
    @Override
    public UserRegistrationDto receive(UserRegistrationDto userRegistrationDto) throws TimeoutException {
        if(shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Timeout!");
        }

        if(shouldSleep()) {
            sleep();
        }

        registrationService.decisionMarkingOnRegistration(userRegistrationDto);
        return userRegistrationDto;
    }
}
