package com.desafiobackendmagazord.domain;

import com.desafiobackendmagazord.dto.RecipeDTO;
import com.desafiobackendmagazord.exceptions.CommentsException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "recipe ")
public class Recipe  {
    @Id
    private String id;
    private String title;
    private String description;
    private List<String> likes;
    private List<String> ingredients;
    private List<Comments> comments;

    public Recipe(String title, String description, List<String> likes, List<String> ingredients, List<Comments> comments) {
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.ingredients = ingredients;
        this.comments=comments;
    }

    public Recipe(RecipeDTO recipeDTO) {
        this.title = recipeDTO.getTitle();
        this.description = recipeDTO.getDescription();
        this.likes = recipeDTO.getLikes();
        this.ingredients = recipeDTO.getIngredients();
        this.comments = recipeDTO.getComments();
    }

    public Recipe() {
    }

    public Recipe(String title, String description, List<String> ingredients) {
    }

}
