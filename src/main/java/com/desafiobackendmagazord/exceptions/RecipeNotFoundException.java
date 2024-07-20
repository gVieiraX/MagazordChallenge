package com.desafiobackendmagazord.exceptions;

public class RecipeNotFoundException  extends RuntimeException{
    public RecipeNotFoundException(String id) {
        super("Receita não encontrada: " + id);
    }
}
