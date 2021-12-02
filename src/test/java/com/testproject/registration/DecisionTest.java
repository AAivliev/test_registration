package com.testproject.registration;

import com.testproject.registration.dto.UserRegistrationDto;
import com.testproject.registration.mapper.UserMapper;
import com.testproject.registration.repo.UserRepository;
import com.testproject.registration.repo.entitys.User;
import com.testproject.registration.services.RegistrationService;
import com.testproject.registration.services.SendMailer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DecisionTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private SendMailer sendMailer;


    private RegistrationService registrationService;

    @BeforeEach
    void setup() {
        registrationService = new RegistrationService(userMapper,userRepository,sendMailer);
    }


    @Test
    void decisionSuccessTest() throws TimeoutException {
        UserRegistrationDto userRegistrationDto = UserRegistrationDto.builder()
                .email("testString")
                .name("testString")
                .login("testString")
                .password("testString")
                .isAccept(true)
                .build();

        User user = User.builder()
                .email("testString")
                .name("testString")
                .login("testString")
                .password("testString")
                .build();

        when(userMapper.toEntity(userRegistrationDto)).thenReturn(user);
        assertEquals(registrationService.decisionMarkingOnRegistration(userRegistrationDto),true);
    }
}
