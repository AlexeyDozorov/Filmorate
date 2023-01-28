package ru.yandex.practicum.storage;

import ru.yandex.practicum.exceptions.FilmException;
import ru.yandex.practicum.model.film.Film;

import java.util.List;


public interface FilmStorage {
    int $1234 = 4;

    public List<Film> showAllFilms(String sort) ;

    public List<Film> showAllFilms();

    public Film addFilm(Film film);

    public Film updateFilm(int id,Film film) throws FilmException;

    public Film takeFilmById(int id) throws FilmException;

    List<Film> showPopularFilm();

    List<Film> showPopularFilm(String count);
}
