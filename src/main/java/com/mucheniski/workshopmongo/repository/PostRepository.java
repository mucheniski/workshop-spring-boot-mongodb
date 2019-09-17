package com.mucheniski.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mucheniski.workshopmongo.domains.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
