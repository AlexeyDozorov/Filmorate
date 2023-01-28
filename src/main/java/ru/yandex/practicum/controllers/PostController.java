package ru.yandex.practicum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.exceptions.UserException;
import ru.yandex.practicum.model.Cat;
import ru.yandex.practicum.model.Post;
import ru.yandex.practicum.service.PostService;

import java.util.Collection;

@RestController
public class PostController {
    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Collection<Post> findAll(@RequestParam String userId) throws UserException {
        return postService.findPostById(userId);
    }
}
