package com.desafiobackendmagazord.dto;

import com.desafiobackendmagazord.domain.Comments;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeDTO {
    private String title;
    private String description;
    private List<String> likes;
    private List<String> ingredients;
    private List<Comments> comments;

    // Construtor padrão
    public RecipeDTO() {}

    // Construtor completo
    public RecipeDTO(String title, String description, List<String> likes, List<String> ingredients,List<Comments> comments) {
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.ingredients = ingredients;
        this.comments = comments;
    }

}