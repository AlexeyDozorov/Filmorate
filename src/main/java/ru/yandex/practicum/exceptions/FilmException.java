package ru.yandex.practicum.exceptions;

public class FilmException extends Exception{

    public FilmException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
