package ru.yandex.practicum.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import ru.yandex.practicum.exceptions.FilmException;
import ru.yandex.practicum.service.FilmService;
import ru.yandex.practicum.storage.InMemoryFilmStorage;
import ru.yandex.practicum.model.film.Film;


import java.util.List;

@Slf4j
@RestController
public class FilmController {
    private final InMemoryFilmStorage filmStorage;
    private final FilmService filmService;
    @Autowired
    public FilmController(InMemoryFilmStorage filmStorage, FilmService filmService) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }

    @GetMapping("/films")
    public List<Film> showAllFilms(@RequestParam(required = false) String sort) {
        if (sort != null) return filmStorage.showAllFilms(sort);
        return filmStorage.showAllFilms();
    }


    @PostMapping("/addfilm")
    public Film addFilm(@RequestBody Film film) {
        return filmStorage.addFilm(film);
    }

    @PostMapping("/updatefilm/{id}")
    public Film updateFilm(@PathVariable int id, @RequestBody Film film) throws FilmException {
        return filmStorage.updateFilm(id,film);
    }

    @GetMapping("/takefilm/{idFilm}")
    public Film addFilm(@PathVariable int idFilm) throws FilmException {
        Film film = filmStorage.takeFilmById(idFilm);
        if (film == null) throw new FilmException("Пользователя с таким id нет");
        return film;
    }

    @PutMapping("/films/{id}/like/{userId}")
    public Film addLikeFilm(@PathVariable int id,
                        @PathVariable int userId) throws FilmException {
        return filmService.addLikeFilm(id, userId);
    }

    @DeleteMapping (value = "/films/{id}/like/{friendId}")
    public void deleteLikeFromFilm(@PathVariable int id,
                                  @PathVariable int friendId) throws FilmException {
        filmService.deleteLikeFromFilm(id, friendId);
    }
    @GetMapping("/films/popular")
    public List<Film> showPopularFilm(@RequestParam(required = false) String count) {
        if (count != null) return filmStorage.showPopularFilm(count);
        return filmStorage.showPopularFilm();
    }
}
/*
{
        "email": "alex.01@mail.ru",
        "login": "login2",
        "name": "name2",
        "birthday": "123sSGSEG5"
        }*/


/*
{
        "name": "film",
        "description": "login2",
        "releaseDate": "name2",
        "duration": 12344
        }*/
