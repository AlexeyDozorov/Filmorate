package ru.yandex.practicum.DAO;

import ru.yandex.practicum.exceptions.FilmException;
import ru.yandex.practicum.model.film.Film;

import java.util.List;

public interface FilmsDao {
    List<Film> showAllfilms();

    List<Film> showAllfilms(String sort);

    Film addFilm(Film film);

    Film updateFilm(int id, Film film);

    boolean get(int id);

    Film takeFilmById(int id) throws FilmException;
}
