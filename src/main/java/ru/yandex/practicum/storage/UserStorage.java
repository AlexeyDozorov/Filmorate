package ru.yandex.practicum.storage;

import ru.yandex.practicum.exceptions.UserException;
import ru.yandex.practicum.model.user.User;

import java.util.List;

public interface UserStorage {

    List<User> showAllUsers();

    public User addUser(User user);

    public User updateUser(int id,User user) throws UserException;

    User takeUserById(int idUser) throws UserException;
}
