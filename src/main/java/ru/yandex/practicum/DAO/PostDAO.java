package ru.yandex.practicum.DAO;

import ru.yandex.practicum.model.Cat;
import ru.yandex.practicum.model.Post;

import java.util.Collection;
import java.util.Collections;

public interface PostDAO {
    Collection<Post> findByCat(Cat cat);
}
