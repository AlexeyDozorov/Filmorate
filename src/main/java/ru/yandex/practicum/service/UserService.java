package ru.yandex.practicum.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.DAO.CatDAOimpl;
import ru.yandex.practicum.model.Cat;
import ru.yandex.practicum.model.Post;
import ru.yandex.practicum.storage.InMemoryUserStorage;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    private final InMemoryUserStorage memoryUserStorage;
    private final CatDAOimpl catDAOimpl;

    @Autowired
    public UserService(InMemoryUserStorage memoryUserStorage, CatDAOimpl catDAOimpl) {
        this.memoryUserStorage = memoryUserStorage;
        this.catDAOimpl = catDAOimpl;
    }

    public void addInFriends(int id, int friendId) {
        log.info("User c id = {} пытается добавить в друзья Usera c id = {}", id, friendId);
        memoryUserStorage.getUsers().get(id).getFriends().add(memoryUserStorage.getUsers().get(friendId).getId());
        memoryUserStorage.getUsers().get(friendId).getFriends().add(memoryUserStorage.getUsers().get(id).getId());
    }

    public Set<Integer> showAllFriends(int idUser) {
        return memoryUserStorage.getUsers().get(idUser).getFriends();
    }

    public void deleteFromFriends(int id, int friendId) {
        log.info("User c id = {} пытается удалить из друзей Usera c id = {}", id, friendId);
        memoryUserStorage.getUsers().get(id).getFriends().remove(memoryUserStorage.getUsers().get(friendId).getId());
        memoryUserStorage.getUsers().get(friendId).getFriends().remove(memoryUserStorage.getUsers().get(id).getId());
    }


    public List<Integer> showCommonFriends(int id, int otherId) {
        log.info("User c id = {} пытается удалить из друзей Usera c id = {}", id, otherId);
        List<Integer> list = memoryUserStorage.getUsers().get(id).getFriends().stream().filter(val -> memoryUserStorage.
                getUsers().get(otherId).getFriends().contains(val)).toList();
        return list;
    }

    public Optional<Cat> findCatsById(String id){
        return catDAOimpl.findCatsById(id);
    }

}
