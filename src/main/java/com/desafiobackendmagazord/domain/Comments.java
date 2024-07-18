package com.desafiobackendmagazord.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Comments {

    @Id
    private String id;
    private String comments;

    public Comments(String comments) {
        this.comments = comments;
    }

    public Comments() {
    }

}
