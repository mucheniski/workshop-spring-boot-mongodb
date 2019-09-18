package com.mucheniski.workshopmongo.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mucheniski.workshopmongo.domains.Post;
import com.mucheniski.workshopmongo.exception.ObjectNotFoundException;
import com.mucheniski.workshopmongo.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado para o id: " + id));		
	}
	
	public List<Post> findByTitleContainingIgnoreCase(String text) {
		return postRepository.findByTitleContainingIgnoreCase(text);
	}
	

	// Exemplo com consulta simples
	public List<Post> findByTitle(String text) {
		return postRepository.findByTitleContainingIgnoreCase(text);
	}	
	
	// Exemplo com @Query
	public List<Post> findByTitleWhitQuery(String text) {
		return postRepository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {		
		return postRepository.fullSearch(text, minDate, maxDate);
	}
	
}
