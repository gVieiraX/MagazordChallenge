package com.desafiobackendmagazord.controller;

import com.desafiobackendmagazord.domain.Recipe;
import com.desafiobackendmagazord.dto.RecipeDTO;
import com.desafiobackendmagazord.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<List<Recipe>> saveRecipe(@RequestBody RecipeDTO recipeDTO){
        Recipe recipe =  this.recipeService.saveRecipe(recipeDTO);
             return ResponseEntity.ok().body(Collections.singletonList(recipe));
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> showRecipe(){
        List<Recipe> showRecipe = recipeService.showRecipe();
        return ResponseEntity.ok().body(showRecipe);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") String id, @RequestBody RecipeDTO recipeDTO ){
        Recipe recipe = this.recipeService.updateRecipe(id,recipeDTO);
        return ResponseEntity.ok().body(recipe);
    }



}
