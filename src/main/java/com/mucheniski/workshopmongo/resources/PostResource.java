package com.mucheniski.workshopmongo.resources;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mucheniski.workshopmongo.domains.Post;
import com.mucheniski.workshopmongo.services.PostService;
import com.mucheniski.workshopmongo.util.URL;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	
	@Autowired
	private PostService postService;	
	
	@GetMapping("/{id}") 
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = postService.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping("/findByTitleContainingIgnoreCase") 
	public ResponseEntity<List<Post>> findByTitleContainingIgnoreCase(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decoreParam(text);
		List<Post> posts = postService.findByTitleContainingIgnoreCase(text);		
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value="/findbytitleQuery")
	public ResponseEntity <List<Post>> findByTitleWhitQuery(@RequestParam(value="text", defaultValue="") String text) {			
		text = URL.decoreParam(text);		
		List<Post> list = postService.findByTitle(text);		 
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/fullSearch")
	public ResponseEntity <List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="1986-12-27T00:00:00Z") String minDate,
			@RequestParam(value="maxDate", defaultValue="1986-12-27T00:00:00Z") String maxDate
			) {			
		text = URL.decoreParam(text);		
		Instant min = Instant.parse(minDate);
		Instant max = Instant.parse(minDate);
		List<Post> list = postService.fullSearch(text, min, max);	
		return ResponseEntity.ok().body(list);
	}
	
}
