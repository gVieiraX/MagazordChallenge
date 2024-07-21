package com.desafiobackendmagazord.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
public class Comments {

    @Id
    private String commentsId;
    private String comments;

    public Comments(String comments) {
        this.comments = comments;
    }

    public Comments() {
    }
}
