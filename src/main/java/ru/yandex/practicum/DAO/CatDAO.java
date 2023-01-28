package ru.yandex.practicum.DAO;

import ru.yandex.practicum.model.Cat;

import java.util.Optional;

public interface CatDAO {
    Optional<Cat> findCatsById(String id);
}
