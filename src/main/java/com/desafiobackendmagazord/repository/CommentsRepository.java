package com.desafiobackendmagazord.repository;

import com.desafiobackendmagazord.domain.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends MongoRepository<Comments,String> {
}
