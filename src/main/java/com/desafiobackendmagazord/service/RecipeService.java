package com.desafiobackendmagazord.service;

import com.desafiobackendmagazord.domain.Recipe;
import com.desafiobackendmagazord.dto.RecipeDTO;
import com.desafiobackendmagazord.exceptions.RecipeNotFoundException;
import com.desafiobackendmagazord.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Recipe updateRecipe(String id, RecipeDTO recipeDTO) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        if(optionalRecipe.isPresent()){
            Recipe recipe = optionalRecipe.get();

            if(recipeDTO.getTitle() !=null){
                recipe.setTitle(recipeDTO.getTitle());
            }

            if(recipeDTO.getDescription() !=null){
                recipe.setDescription(recipeDTO.getDescription());
            }

            if(recipeDTO.getIngredients() !=null){
                recipe.setIngredients(recipeDTO.getIngredients());
            }
            return recipeRepository.save(recipe);

        } else {
            throw new RecipeNotFoundException(id);
        }

    }

    public String deleteRecipe(String id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        if (optionalRecipe.isPresent()) {
            recipeRepository.deleteById(id);
            return "Receita com id:" + id + " foi deletada com sucesso!";
        } else {
            return "Receita com id:" + id + " ,não foi encontrada.";

        }
    }
}
