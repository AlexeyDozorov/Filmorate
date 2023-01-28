package ru.yandex.practicum.storage;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.exceptions.UserException;
import ru.yandex.practicum.model.user.User;

import java.util.LinkedList;
import java.util.List;
@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage{

    @Getter
    private LinkedList<User> users = new LinkedList<>();
    public static int COUNTER = 0;

    @Override
    public List<User> showAllUsers() {
        log.info("Запрос обо всех пользователях");
        return users;
    }

    @Override
    public User addUser(User user) {
        users.add(user);
        log.info("Пользователь с именем = {} был добавлен", user.getName());
        return user;
    }

    @Override
    public User updateUser(int id, User user) throws UserException {
        if (users.get(id) == null) throw new UserException("Пользователя с таким id нет");
        users.get(id).setName(user.getName());
        users.get(id).setBirthday(user.getBirthday());
        users.get(id).setEmail(user.getEmail());
        users.get(id).setLogin(user.getLogin());
        log.info("Пользователь с id = {} был изменен", id);
        return users.get(id);
    }

    @Override
    public User takeUserById(int idUser) throws UserException {
        if (users.get(idUser) == null) throw new UserException("Пользователя с таким id нет");
        return users.stream().filter(val -> val.getId() == idUser).findFirst().orElse(null);
    }


}
