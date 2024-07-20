package com.desafiobackendmagazord.service;

import com.desafiobackendmagazord.domain.Recipe;
import com.desafiobackendmagazord.dto.RecipeDTO;
import com.desafiobackendmagazord.exceptions.RecipeNotFoundException;
import com.desafiobackendmagazord.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


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

        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            if (recipeDTO.getTitle() != null) {
                recipe.setTitle(recipeDTO.getTitle());
            }

            if (recipeDTO.getDescription() != null) {
                recipe.setDescription(recipeDTO.getDescription());
            }

            if (recipeDTO.getIngredients() != null) {
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

    public Optional<Recipe> showRecipeById(String id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        if (optionalRecipe.isPresent()) {
            recipeRepository.findById(id);
            return optionalRecipe;
        } else {
            throw new RecipeNotFoundException(id);
        }

    }

    public List<Recipe> showRecipeByIngredient(String ingredients) {
        return mongoTemplate.find(Query.query(Criteria.where("ingredients").is(ingredients)).with(Sort.by("title").ascending()), Recipe.class);
    }
}