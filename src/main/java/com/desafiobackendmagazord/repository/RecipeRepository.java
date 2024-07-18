package com.desafiobackendmagazord.repository;

import com.desafiobackendmagazord.domain.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe,String> {
}
