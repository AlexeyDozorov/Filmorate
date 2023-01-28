package ru.yandex.practicum.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.exceptions.FilmException;
import ru.yandex.practicum.model.film.Film;
import ru.yandex.practicum.model.user.User;
import ru.yandex.practicum.storage.InMemoryFilmStorage;
import ru.yandex.practicum.storage.InMemoryUserStorage;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FilmService {
    private final InMemoryFilmStorage filmStorage;
    private final InMemoryUserStorage userStorage;
    @Autowired
    public FilmService(InMemoryFilmStorage filmStorage, InMemoryUserStorage userStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }


    public Film addLikeFilm(int id, int userId) throws FilmException {
        log.info("User c id = {} пытается поставить лайк фильму c id = {}", userId, id);
        int set = filmStorage.getFilms().get(id).getLikes();
        User idUser = userStorage.getUsers().get(userId);
        if (idUser == null) throw new FilmException("Пользователя с таким id нет");
        set++;
        return null;
    }

    public void deleteLikeFromFilm(int id, int userId) throws FilmException {
        log.info("User c id = {} пытается удалить лайк фильму  c id = {}", id, userId);
        Integer set = filmStorage.getFilms().get(id).getLikes();
        User idUser = userStorage.getUsers().get(userId);
        if (idUser == null) throw new FilmException("Пользователя с таким id нет");
        set++;
    }
}
