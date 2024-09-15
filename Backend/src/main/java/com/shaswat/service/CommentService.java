package com.shaswat.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaswat.models.Comment;
import com.shaswat.models.Post;
import com.shaswat.models.User;
import com.shaswat.repository.ICommentRepository;
import com.shaswat.repository.IPostRepository;

@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private IPostService postService;
	
	@Autowired
	private IUserService userService; 
	
	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private IPostRepository postRepository;
	
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user = userService.FindUserById(userId);
		Post post = postService.FindPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(comment);
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isEmpty()) throw new Exception("comment does not exist");
		
		return opt.get();
	}

	@Override
	public Comment likeComment(Integer CommentId, Integer userId) throws Exception {

		Comment comment = findCommentById(CommentId);
		User user = userService.FindUserById(userId);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else {
			comment.getLiked().remove(user);
		}
		
		return commentRepository.save(comment);
	}

}
