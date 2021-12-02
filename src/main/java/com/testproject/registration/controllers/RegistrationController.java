package com.testproject.registration.controllers;

import com.testproject.registration.dto.UserRegistrationDto;
import com.testproject.registration.services.MessagingService;
import com.testproject.registration.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {


    private final MessagingService messagingService;

    @PostMapping("/registration")
    public void registration(@RequestBody UserRegistrationDto userRegistrationDto){
        messagingService.send(userRegistrationDto);
    }
}
