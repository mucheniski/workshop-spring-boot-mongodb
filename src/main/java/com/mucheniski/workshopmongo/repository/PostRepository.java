package com.mucheniski.workshopmongo.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mucheniski.workshopmongo.domains.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	// ?0 é a posição do parametro passado no método
	// i indica que é case insensitive
	// Maiores informações na documentação sobre query mathods https://docs.mongodb.com/manual/reference/operator/query/regex/
	// Para usar os métodos basta incluí-los no service e resource normalmente

	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findByTitleWhitQuery(String text);
	
	@Query("{ $and: [ {moment: {$gte: ?1} }, { moment: { $lte: ?2} }, "
			+ "{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] }   "
		+ "] }")
	List<Post> fullSearch(String text, Instant minDate, Instant maxDate);
	
}
