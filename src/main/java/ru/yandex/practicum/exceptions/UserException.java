package ru.yandex.practicum.exceptions;

public class UserException extends Exception{

    public UserException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
