package com.desafiobackendmagazord.config.mongodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(){
        return new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/recipe-catalog?authSource=admin");
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDatabaseFactory());
    }
}
