package com.desafiobackendmagazord.exceptions;

public class CommentsIdNotFound extends RuntimeException{
    public CommentsIdNotFound(String commentsId) {
        super("Id do comentário não encontrado");
    }
}
