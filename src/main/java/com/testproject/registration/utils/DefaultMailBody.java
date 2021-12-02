package com.testproject.registration.utils;

public enum DefaultMailBody {

    SUCCESS_REGISTRATION("Регистрация завершена"),
    ERROR_ACCEPT_REGISTRATION("Запрос в регистрации отклонён");

    private String message;


    DefaultMailBody(String message){

    }

    public String getMessage(){
        return message;
    }
}
