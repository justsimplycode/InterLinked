package com.shaswat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shaswat.models.Comment;
import com.shaswat.models.User;
import com.shaswat.service.ICommentService;
import com.shaswat.service.IUserService;

@RestController
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment CreateComment(@RequestBody Comment comment,@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.FindUserByJwt(jwt);
		
		Comment createdComment = commentService.createComment(comment, postId, user.getId());
		
		return createdComment;
		
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment LikeComment(@PathVariable Integer commentId,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.FindUserByJwt(jwt);
		
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		
		return likedComment;
		
	}
	
}
