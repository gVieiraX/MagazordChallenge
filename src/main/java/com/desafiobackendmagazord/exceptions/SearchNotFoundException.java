package com.desafiobackendmagazord.exceptions;

public class SearchNotFoundException extends RuntimeException{
    public SearchNotFoundException(String search) {
        super("Não foi encontrada nenhuma receita com a palavavra: " + search);
    }
}
