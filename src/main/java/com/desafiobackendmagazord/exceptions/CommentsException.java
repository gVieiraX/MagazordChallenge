package com.desafiobackendmagazord.exceptions;

public class CommentsException extends RuntimeException{
    public CommentsException() {
        super("Não há comentários");
    }
}
