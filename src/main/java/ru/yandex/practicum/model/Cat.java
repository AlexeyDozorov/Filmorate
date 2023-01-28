package ru.yandex.practicum.model;

import lombok.Data;

@Data
public class Cat {
    private int id;
    private String username;
    private String nickname;

    public Cat() {

    }

    public Cat(int id, String username, String nickname) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
    }
}
