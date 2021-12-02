package com.testproject.registration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegistrationDto {

    private String login;
    private String password;
    private String email;
    //ФИО
    private String name;

    private Boolean isAccept;
}
