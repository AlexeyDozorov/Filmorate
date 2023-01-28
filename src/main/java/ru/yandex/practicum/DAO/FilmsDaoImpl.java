package ru.yandex.practicum.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.JDBC.JDBCconnectionService;
import ru.yandex.practicum.exceptions.FilmException;
import ru.yandex.practicum.model.Cat;
import ru.yandex.practicum.model.film.Film;
import ru.yandex.practicum.model.film.Genres;
import ru.yandex.practicum.model.film.Ratings;

import java.util.*;

@Component
public class FilmsDaoImpl implements FilmsDao {

    private final JdbcTemplate jdbcTemplate;

    public FilmsDaoImpl(JDBCconnectionService jdbCconnectionService) {
        this.jdbcTemplate = jdbCconnectionService.getTamplate();
    }

    @Override
    public List<Film> showAllfilms() {
        return jdbcTemplate.query(
                "select * from films ORDER BY likes DESC LIMIT 5",
                (resultSet, rowNum) -> {
                    Film film = new Film();
                    film.setId(resultSet.getInt("id"));
                    film.setName(resultSet.getString("name"));
                    film.setDescription(resultSet.getString("description"));
                    film.setReleaseDate(resultSet.getString("release_date"));
                    film.setDuration(resultSet.getLong("duration"));
                    film.setGenres(Genres.valueOf(resultSet.getString("genres")));
                    film.setRatings(Ratings.valueOf(resultSet.getString("ratings")));
                    film.setLikes(resultSet.getInt("likes"));
                    return film;
                });
    }


    @Override
    public Film addFilm(Film film) {
        jdbcTemplate.update("INSERT INTO films (name,description,release_date,duration, genres, ratings, likes)" +
                "VALUES (?,?,?,?,?,?,?)", film.getName(),film.getDescription(),  film.getReleaseDate(),film.getDuration(),
                String.valueOf(film.getGenres()), String.valueOf(film.getRatings()),film.getLikes());
        return film;
    }

    @Override
    public Film updateFilm(int id, Film film) {
        jdbcTemplate.update("UPDATE  films SET name = ?,description = ?,release_date=?," +
                        "duration=?, genres=?, ratings=?, likes =? WHERE id = ?;", film.getName(),film.getDescription(),  film.getReleaseDate(),film.getDuration(),
                String.valueOf(film.getGenres()), String.valueOf(film.getRatings()),film.getLikes(), id);
        film.setId(id);
        return film;
    }

    @Override
    public boolean get(int id) {
        int rowCount = jdbcTemplate.queryForObject("select count(*) from films where id = ?",Integer.class, id);
        System.out.println(rowCount);
        if (rowCount>0) return true;
        return false;
    }

    @Override
    public Film takeFilmById(int id) throws FilmException {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from films where id =?",id);
        if (rowSet.next()) {
            Film film = new Film(rowSet.getString("name"),
                    rowSet.getString("name"),
                    rowSet.getString("name" ),rowSet.getLong("duration"),rowSet.getString("ratings"),
                    rowSet.getString("genres"));
            return film;
        }
        return null;
    }

    @Override
    public List<Film> showAllfilms(String sort) {
        return jdbcTemplate.query(
                "select * from films ORDER BY likes "+sort+" LIMIT 5",
                (resultSet, rowNum) -> {
                    Film film = new Film();
                    film.setId(resultSet.getInt("id"));
                    film.setName(resultSet.getString("name"));
                    film.setDescription(resultSet.getString("description"));
                    film.setReleaseDate(resultSet.getString("release_date"));
                    film.setDuration(resultSet.getLong("duration"));
                    film.setGenres(Genres.valueOf(resultSet.getString("genres")));
                    film.setRatings(Ratings.valueOf(resultSet.getString("ratings")));
                    film.setLikes(resultSet.getInt("likes"));
                    return film;
                });
    }

}
