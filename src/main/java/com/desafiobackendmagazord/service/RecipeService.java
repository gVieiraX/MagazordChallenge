package com.desafiobackendmagazord.service;

import com.desafiobackendmagazord.domain.Recipe;
import com.desafiobackendmagazord.dto.RecipeDTO;
import com.desafiobackendmagazord.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe(recipeDTO);

        this.recipeRepository.save(recipe);
        return recipe;
    }


    public List<Recipe> showRecipe() {
        return recipeRepository.findAll();
    }
}
