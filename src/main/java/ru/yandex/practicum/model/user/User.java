package ru.yandex.practicum.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Value;
import ru.yandex.practicum.storage.InMemoryUserStorage;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {

    private int id;

    @Email(message = "Неправильный формат")
    private String email;
    @NotBlank
    private String login;

    private String name;

    private String birthday;

    private Set<Integer> friends;

    public User(String email, String login, String name, String birthday) {
        this.email = email;
        if (login.isEmpty()){
            this.login = name;
        } else this.login = login;
        this.name = name;
        this.birthday = birthday;
        this.friends = new HashSet<>();
        this.id = InMemoryUserStorage.COUNTER;
        InMemoryUserStorage.COUNTER++;
    }
}
