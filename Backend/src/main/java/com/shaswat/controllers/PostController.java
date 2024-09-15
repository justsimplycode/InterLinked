package com.shaswat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shaswat.models.Post;
import com.shaswat.models.User;
import com.shaswat.response.ApiResponse;
import com.shaswat.service.IPostService;
import com.shaswat.service.IUserService;

@RestController
public class PostController {
	
	@Autowired
	IPostService postService;
	
	@Autowired
	IUserService userService;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> CreatePost(@RequestBody Post post, @RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.FindUserByJwt(jwt);
		Post createdPost = postService.CreateNewPost(post, reqUser.getId()); 
		
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> DeletePost(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.FindUserByJwt(jwt);
		String message = postService.DeletePost(postId, reqUser.getId());
		ApiResponse response = new ApiResponse(message, true);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> FindPostByHandler(@PathVariable Integer postId) throws Exception{
		
		Post post = postService.FindPostById(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> FindUserPost(@PathVariable Integer userId){
		
		List<Post> posts = postService.FindPostByUserId(userId);
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> FindAllPost(){
		
		List<Post> posts = postService.FindAllPost();
		
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		
	}
	
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> SavePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.FindUserByJwt(jwt);
		Post post = postService.SavePost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> LikePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization")String jwt) throws Exception{
		
		User reqUser = userService.FindUserByJwt(jwt);
		Post post = postService.LikePost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
		
	}
	
}
