package ru.yandex.practicum.controllers;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.exceptions.UserException;
import ru.yandex.practicum.model.Cat;
import ru.yandex.practicum.model.user.User;
import ru.yandex.practicum.service.UserService;
import ru.yandex.practicum.storage.InMemoryUserStorage;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
public class UserController {

    InMemoryUserStorage userStorage;

    UserService userService;

    @Autowired
    public UserController(InMemoryUserStorage userStorage, UserService userService) {
        this.userStorage = userStorage;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userStorage.showAllUsers();
    }

    @PostMapping("/adduser")
    public User addUser(@Valid @RequestBody User user) {
        return userStorage.addUser(user);
    }

    @PostMapping("/updateuser/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) throws UserException {
        return userStorage.updateUser(id, user);
    }

    @GetMapping("/takeuser/{idUser}")
    public User addFilm(@PathVariable int idUser) throws UserException {
        return userStorage.takeUserById(idUser);
    }

    @PutMapping("/users/{id}/friends/{friendId}")
    public void addInFriends(@PathVariable int id,
                             @PathVariable int friendId) {
        userService.addInFriends(id, friendId);
    }

    @GetMapping("/users/{idUser}/friends")
    public Set<Integer> showAllFriends(@PathVariable int idUser) {
        return userService.showAllFriends(idUser);
    }

    @DeleteMapping (value = "/users/{id}/friends/{friendId}")
    public void deleteFromFriends(@PathVariable int id,
                             @PathVariable int friendId) {
        userService.deleteFromFriends(id, friendId);
    }

    @GetMapping (value = "/users/{id}/friends/common/{otherId}")
    public List<Integer> showCommonFriends(@PathVariable int id,
                                  @PathVariable int otherId) {
         return userService.showCommonFriends(id, otherId);
    }

    @GetMapping("/cats/{id}")
    public Optional<Cat> findCatsById(@PathVariable String id) {
        return userService.findCatsById(id);
    }
}
