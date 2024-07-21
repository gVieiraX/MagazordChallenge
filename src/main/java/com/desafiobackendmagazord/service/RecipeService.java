package com.desafiobackendmagazord.service;

import com.desafiobackendmagazord.domain.Comments;
import com.desafiobackendmagazord.domain.Recipe;
import com.desafiobackendmagazord.dto.RecipeDTO;
import com.desafiobackendmagazord.exceptions.CommentsIdNotFound;
import com.desafiobackendmagazord.exceptions.DeleteCommentsMessage;
import com.desafiobackendmagazord.exceptions.RecipeNotFoundException;
import com.desafiobackendmagazord.exceptions.SearchNotFoundException;
import com.desafiobackendmagazord.repository.CommentsRepository;
import com.desafiobackendmagazord.repository.RecipeRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommentsRepository commentsRepository;


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

            if(recipeDTO.getComments() != null){
                recipe.setComments(recipeDTO.getComments());
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
            throw new RecipeNotFoundException(id);

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

    public List<Recipe> showRecipeBySearch(String search) {
                Query query = new Query();

                query.addCriteria(new Criteria().orOperator(
                        Criteria.where("title").regex(search,"i"),
                        Criteria.where("description").regex(search,"i")
                ));

                List<Recipe> recipes =  mongoTemplate.find(query,Recipe.class);

                if (recipes.isEmpty()){
                    throw new SearchNotFoundException(search);
                } else {
                    return recipes;

                }

    }

    public Comments saveRecipeComments(String id, Comments comments) {
        comments.setCommentsId(new ObjectId().toString());

        commentsRepository.save(comments);

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update().addToSet("comments", comments);

        UpdateResult result = mongoTemplate.updateFirst(query, update, Recipe.class);

        if (result.getMatchedCount() == 0) {
            throw new RecipeNotFoundException(id);
        }
        return comments;
    }

    public boolean updateRecipeComments(String recipeId, String commentId, Comments newComment) {
        System.out.println("Recipe ID: " + recipeId);
        System.out.println("Comment ID: " + commentId);

        Query query = new Query(Criteria.where("_id").is(recipeId).and("comments._id").is(commentId));
        System.out.println("Query: " + query.toString());

        Update update = new Update()

                .set("comments.$.comments", newComment.getComments());
        System.out.println("Update: " + update.toString());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Recipe.class);
        System.out.println("Update result: " + result.toString());

        return result.getMatchedCount() > 0;
    }

    public ResponseEntity<String> deleteCommentsById(String id, String commentsId) {
        if (commentsId == null || commentsId.isEmpty()) {
            throw new CommentsIdNotFound(commentsId);
        }

        Recipe recipe = mongoTemplate.findById(id, Recipe.class);
        if (recipe == null) {
            throw new RecipeNotFoundException(id);
        }

        boolean commentFound = false;
        for (Comments comment : recipe.getComments()) {
            if (comment.getCommentsId().equals(commentsId)) {
                commentFound = true;
                break;
            }
        }

        if (!commentFound) {
            throw new CommentsIdNotFound(commentsId);
        }

        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update().pull("comments", new BasicDBObject("_id", commentsId));
        UpdateResult result = mongoTemplate.updateFirst(query, update, Recipe.class);

        if (result.getMatchedCount() == 0) {
            throw new RecipeNotFoundException(id);
        }

        if (result.getModifiedCount() == 0) {
            throw new CommentsIdNotFound(commentsId);
        } else {
            throw new DeleteCommentsMessage();
        }
    }


}