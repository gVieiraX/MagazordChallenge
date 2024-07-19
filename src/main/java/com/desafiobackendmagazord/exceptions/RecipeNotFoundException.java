package com.desafiobackendmagazord.exceptions;

public class RecipeNotFoundException  extends RuntimeException{
    public RecipeNotFoundException(String id) {
        super("Recipe not found with id: " + id);
    }
}
