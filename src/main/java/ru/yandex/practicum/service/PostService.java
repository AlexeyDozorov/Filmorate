package ru.yandex.practicum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.DAO.PostDAO;
import ru.yandex.practicum.DAO.PostDAOImpl;
import ru.yandex.practicum.exceptions.UserException;
import ru.yandex.practicum.model.Cat;
import ru.yandex.practicum.model.Post;

import java.util.Collection;

@Service
public class PostService {
    PostDAO postDAO;
    UserService userService;

    @Autowired
    public PostService(PostDAOImpl postDAO, UserService userService) {
        this.postDAO = postDAO;
        this.userService = userService;
    }

    public Collection<Post> findPostById(String userId) throws UserException {
        Cat cat = userService.findCatsById(userId).orElseThrow(()-> new UserException("User не найден"));
        return postDAO.findByCat(cat);
    }
}
