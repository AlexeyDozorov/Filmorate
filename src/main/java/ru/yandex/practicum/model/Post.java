package ru.yandex.practicum.model;

import lombok.Data;

@Data
public class Post {
    private Integer id;

    private Cat cat;

    private Integer creationDate;

    private String description;

    private String photoUrl;

    public Post(Cat cat, Integer creationDate, String description, String photoUrl) {
        this.cat = cat;
        this.creationDate = creationDate;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Post(Integer id, Cat cat, Integer creationDate, String description, String photoUrl) {
        this.id = id;
        this.cat = cat;
        this.creationDate = creationDate;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public Post() {

    }
}
