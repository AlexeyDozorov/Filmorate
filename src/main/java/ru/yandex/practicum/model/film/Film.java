package ru.yandex.practicum.model.film;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yandex.practicum.exceptions.FilmException;
import ru.yandex.practicum.storage.InMemoryFilmStorage;

import java.util.*;


@Data
public class Film {
    private int id;

    private String name;

    private String description;

    private String releaseDate;

    private Long duration;

    private Genres genres;

    private Ratings ratings;

    private int likes;

    public Film(String name, String description, String releaseDate, Long duration, String ratings, String genres) throws FilmException {
        if (name == null){
            throw new FilmException("Название фильма не может быть null");
        }
        this.name = name;
        if (description.length() > 200){
            throw new FilmException("Описание не может быть больше 200 симоволов");
        }
        this.description = description;
        this.releaseDate = releaseDate;
        if (duration < 0){
            throw new FilmException("Введено отрицательное число на продолжительность фильма");
        }
        this.duration = duration;
        this.likes = 0;
        this.genres = Genres.valueOf(genres);
        this.ratings = Ratings.valueOf(ratings);
    }
    public Film(String name, String description, String releaseDate, Long duration, String ratings, String genres, Integer likes) throws FilmException {
        if (name == null){
            throw new FilmException("Название фильма не может быть null");
        }
        this.name = name;
        if (description.length() > 200){
            throw new FilmException("Описание не может быть больше 200 симоволов");
        }
        this.description = description;
        this.releaseDate = releaseDate;
        if (duration < 0){
            throw new FilmException("Введено отрицательное число на продолжительность фильма");
        }
        this.duration = duration;
        this.likes = likes;
        this.genres = Genres.valueOf(genres);
        this.ratings = Ratings.valueOf(ratings);
    }

    public Film() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && Objects.equals(name, film.name) && Objects.equals(description, film.description) && Objects.equals(releaseDate, film.releaseDate) && Objects.equals(duration, film.duration) && genres == film.genres && ratings == film.ratings && Objects.equals(likes, film.likes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, releaseDate, duration, genres, ratings, likes);
    }
}
