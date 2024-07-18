package com.desafiobackendmagazord.dto;

import com.desafiobackendmagazord.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.sun.beans.introspect.PropertyInfo.Name.description;

@Getter
@Setter
public class RecipeDTO {
    private String title;
    private String description;
    private List<String> likes;
    private List<String> ingredients;

    // Construtor padrão
    public RecipeDTO() {}

    // Construtor completo
    public RecipeDTO(String title, String description, List<String> likes, List<String> ingredients) {
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.ingredients = ingredients;
    }

}