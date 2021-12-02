package com.testproject.registration.services;

import com.testproject.registration.dto.UserRegistrationDto;
import com.testproject.registration.mapper.UserMapper;
import com.testproject.registration.repo.UserRepository;
import com.testproject.registration.repo.entitys.User;
import com.testproject.registration.utils.DefaultMailBody;
import com.testproject.registration.utils.PasswordUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
@Slf4j
@AllArgsConstructor
public class RegistrationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final SendMailer sendMailer;


    public boolean decisionMarkingOnRegistration(UserRegistrationDto userRegistrationDto) throws TimeoutException {
        if(userRegistrationDto.getIsAccept()){
            saveUser(userRegistrationDto);
            //Тело сообщения можно в целом доставать из базы(из соотвествующего словаря), т.к. проще его менять, а не лезть в константы
            sendMailer.sendMail(userRegistrationDto.getEmail(), DefaultMailBody.SUCCESS_REGISTRATION.getMessage());
        }
        else{
            sendMailer.sendMail(userRegistrationDto.getEmail(),DefaultMailBody.ERROR_ACCEPT_REGISTRATION.getMessage());
        }

        return userRegistrationDto.getIsAccept();
    }

    private void saveUser(UserRegistrationDto userRegistrationDto){
        User user = userMapper.toEntity(userRegistrationDto);
        //Кладем в базу зашифрованный пароль
        user.setPassword(PasswordUtils.getEncryptPassword(userRegistrationDto.getPassword()));
        userRepository.save(user);
    }
}
