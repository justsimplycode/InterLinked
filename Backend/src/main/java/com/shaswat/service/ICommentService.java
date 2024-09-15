package com.shaswat.service;

import com.shaswat.models.Comment;

public interface ICommentService {
	
	public Comment createComment(Comment comment,Integer postId,Integer userId) throws Exception;

	public Comment findCommentById(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer CommentId,Integer userId) throws Exception;
}
