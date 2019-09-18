package com.mucheniski.workshopmongo.services;

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
	
}
