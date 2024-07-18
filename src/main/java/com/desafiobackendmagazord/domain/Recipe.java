package com.desafiobackendmagazord.domain;

import com.desafiobackendmagazord.dto.RecipeDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public Recipe(String title, String description, List<String> likes, List<String> ingredients) {
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.ingredients = ingredients;
    }


    public Recipe(RecipeDTO recipeDTO) {
        this.title = recipeDTO.getTitle();
        this.description = recipeDTO.getDescription();
        this.likes = recipeDTO.getLikes();
        this.ingredients = recipeDTO.getIngredients();
    }

    public Recipe() {
    }
}
