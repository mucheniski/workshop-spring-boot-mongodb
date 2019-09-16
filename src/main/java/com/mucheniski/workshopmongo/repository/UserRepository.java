package com.mucheniski.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mucheniski.workshopmongo.domains.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
