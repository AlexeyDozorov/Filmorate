package ru.yandex.practicum.storage;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.yandex.practicum.DAO.FilmsDao;
import ru.yandex.practicum.DAO.FilmsDaoImpl;
import ru.yandex.practicum.exceptions.FilmException;
import ru.yandex.practicum.model.film.Film;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import java.util.stream.Collectors;


@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage {

    @Getter
    private LinkedList<Film> films = new LinkedList<>();
    private final FilmsDao filmsDao;
    public static int COUNTER = 0;


    public InMemoryFilmStorage(FilmsDaoImpl filmsDao) {
        this.filmsDao = filmsDao;
    }


    @Override
    public List<Film> showAllFilms(String sort) {
        log.info("Запрос обо всех фильмах");
        return filmsDao.showAllfilms(sort);
    }

    @Override
    public List<Film> showAllFilms() {
        log.info("Запрос обо всех фильмах");
        return filmsDao.showAllfilms();
    }

    @Override
    public Film addFilm(Film film) {
        log.info("Фильм с названием = {} был добавлен", film.getName());
        return filmsDao.addFilm(film);
    }

    @Override
    public Film updateFilm(int id, Film film) throws FilmException {
        if (!filmsDao.get(id)) throw new FilmException("Пользователя с таким id нет");
        log.info("Фильм с id = {} был изменен", id);
        return filmsDao.updateFilm(id, film);
    }

    @Override
    public Film takeFilmById(int id) throws FilmException {
        return filmsDao.takeFilmById(id);
    }


    @Override
    public List<Film> showPopularFilm() {
        log.info("Запрос обо всех фильмах по лайкам");
        return films.stream().sorted((v1, v2) -> v2.getLikes() - v1.getLikes()).collect(Collectors.toList()).
                subList(((films.size() < 10) ? 0 : films.size() - 10), films.size());
    }

    @Override
    public List<Film> showPopularFilm(String count) {
        log.info("Запрос обо всех фильмах по лайкам");
        return films.stream().sorted((v1, v2) -> v2.getLikes() - v1.getLikes()).collect(Collectors.toList()).
                subList(0, Integer.parseInt(count));
    }
}
