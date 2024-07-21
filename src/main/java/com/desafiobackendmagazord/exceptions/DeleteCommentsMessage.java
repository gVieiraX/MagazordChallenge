package com.desafiobackendmagazord.exceptions;

public class DeleteCommentsMessage extends RuntimeException {
    public DeleteCommentsMessage() {
        super("Comentário apagado com sucesso");
    }
}
