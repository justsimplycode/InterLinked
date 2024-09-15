package com.shaswat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shaswat.models.Post;
import com.shaswat.models.User;
import com.shaswat.repository.IPostRepository;
import com.shaswat.repository.IUserRepository;

@Service
public class PostService implements IPostService{
	
	@Autowired
	IPostRepository postRepository;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IUserRepository userRepository;
	
	@Override
	public Post CreateNewPost(Post post, Integer userId) throws Exception {
		
		User user = userService.FindUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		return postRepository.save(newPost);
	}

	@Override
	public String DeletePost(Integer postId, Integer userId) throws Exception {
		
		Post post = FindPostById(postId);
		User user = userService.FindUserById(userId);
		
		if(post.getUser().getId() != user.getId()) throw new Exception("Can't delete another users post");
		
		postRepository.delete(post);
		
		return "Post deleted successfully";
		
	}

	@Override
	public List<Post> FindPostByUserId(Integer userId) {
		
		return postRepository.findPostByUserId(userId);
		
	}

	@Override
	public Post FindPostById(Integer postId) throws Exception {

		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isEmpty()) throw new Exception("Post not found with id=" + postId);
		
		return post.get();
		
	}

	@Override
	public List<Post> FindAllPost() {
		
		return postRepository.findAll();
		
	}

	@Override
	public Post SavePost(Integer postId, Integer userId) throws Exception {
		
		Post post = FindPostById(postId);
		User user = userService.FindUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else {
			user.getSavedPost().add(post);
		}
		
		userRepository.save(user);
		
		return post;
		
	}

	@Override
	public Post LikePost(Integer postId, Integer userId) throws Exception {
		
		Post post = FindPostById(postId);
		User user = userService.FindUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		
		return postRepository.save(post);
		
	}

}
