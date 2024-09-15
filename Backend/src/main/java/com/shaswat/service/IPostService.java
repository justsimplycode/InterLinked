package com.shaswat.service;

import java.util.List;
import com.shaswat.models.Post;

public interface IPostService {
	
	Post CreateNewPost(Post post, Integer userId) throws Exception;
	
	String DeletePost(Integer postId, Integer userId) throws Exception;
	
	List<Post> FindPostByUserId(Integer userId);
	
	Post FindPostById(Integer postId) throws Exception;
	
	List<Post> FindAllPost();
	
	Post SavePost(Integer postId, Integer userId) throws Exception;
	
	Post LikePost(Integer postId, Integer userId) throws Exception;
	
}
