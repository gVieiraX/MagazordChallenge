package com.desafiobackendmagazord.controller;

import com.desafiobackendmagazord.domain.Comments;
import com.desafiobackendmagazord.domain.Recipe;
import com.desafiobackendmagazord.dto.RecipeDTO;
import com.desafiobackendmagazord.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<List<Recipe>> saveRecipe(@RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = this.recipeService.saveRecipe(recipeDTO);
        return ResponseEntity.ok().body(Collections.singletonList(recipe));
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> showRecipe() {
        List<Recipe> showRecipe = recipeService.showRecipe();
        return ResponseEntity.ok().body(showRecipe);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") String id, @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = this.recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable("id") String id) {
        return recipeService.deleteRecipe(id);
    }

    @GetMapping("/{id}")
    public Optional<Recipe> showRecipeById(@PathVariable("id") String id) {
        return recipeService.showRecipeById(id);
    }

    @GetMapping("/ingredient")
    public List<Recipe> showRecipeByIngredient(@RequestParam String ingredients){
        return recipeService.showRecipeByIngredient(ingredients);
    }

    @GetMapping("/search")
    public List<Recipe> showRecipeBySearch(@RequestParam String search ){
        return recipeService.showRecipeBySearch(search);
    }

    @PostMapping("/{id}/comment")
    public Comments saveRecipeComments(@PathVariable("id") String id, @RequestBody Comments comments){
       return  recipeService.saveRecipeComments(id,comments);
    }

    @PutMapping("/{id}/comment/{commentId}")
    public ResponseEntity<String> updateRecipeComments(@PathVariable String id, @PathVariable String commentId, @RequestBody Comments comments) {
        try {
            boolean updated = recipeService.updateRecipeComments(id, commentId, comments);
            if (updated) {
                return ResponseEntity.ok("Comentário atualizado com sucesso");
            } else {
                System.out.println("Comentário não encontrado para a receita com Id : " + id + " e comentário com ID: " + commentId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error ao atualizar o comentário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}/comment/{commentsId}")
        public ResponseEntity<String> deleteRecipeComments(@PathVariable String id, @PathVariable String commentsId){
        return  recipeService.deleteCommentsById(id,commentsId);
    }

}