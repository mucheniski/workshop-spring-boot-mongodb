package com.mucheniski.workshopmongo.configs;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mucheniski.workshopmongo.domains.Post;
import com.mucheniski.workshopmongo.domains.User;
import com.mucheniski.workshopmongo.dto.AuthorDTO;
import com.mucheniski.workshopmongo.dto.CommentDTO;
import com.mucheniski.workshopmongo.repository.PostRepository;
import com.mucheniski.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepositoy;

	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepositoy.deleteAll();
		
		User morty = new User(null, "Morty Sanches", "morty@gmail.com");
		User rick = new User(null, "Rick Sanches", "rick@gmail.com");
		User summer = new User(null, "Summer Sanches", "summer@gmail.com");
		
		userRepository.saveAll(Arrays.asList(morty, rick, summer));
		
		Post post1 = new Post(null, Instant.parse("2019-06-20T19:53:07Z"), "Hooow", "I can't fill my legs", new AuthorDTO(morty));
		Post post2 = new Post(null, Instant.parse("2019-06-20T19:53:07Z"), "Gee Rick", "What a life", new AuthorDTO(morty));
		
		CommentDTO comment1 = new CommentDTO("COmment1", Instant.parse("2019-06-20T19:53:07Z"), new AuthorDTO(summer));
		CommentDTO comment2 = new CommentDTO("COmment2", Instant.parse("2019-06-20T19:53:07Z"), new AuthorDTO(rick));
		CommentDTO comment3 = new CommentDTO("COmment3", Instant.parse("2019-06-20T19:53:07Z"), new AuthorDTO(morty));
		
		post1.getComments().addAll(Arrays.asList(comment1, comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		postRepositoy.saveAll(Arrays.asList(post1, post2));
		
		morty.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.saveAll(Arrays.asList(morty));
		
	}

}
