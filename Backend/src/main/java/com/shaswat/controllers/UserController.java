package com.shaswat.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shaswat.exceptions.UserException;
import com.shaswat.models.User;
import com.shaswat.repository.IUserRepository;
import com.shaswat.service.IUserService;

@RestController
public class UserController {
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IUserService userService;
	
	
	@GetMapping("/api/users")
	public List<User> GetUsers() {
		
		List<User> users = userRepository.findAll();
		
		return users;
		
	}
	
	@GetMapping("/api/users/{userId}")
	public User GetUserById(@PathVariable("userId") Integer id) throws UserException {
		
		User user = userService.FindUserById(id);
		return user;
		
	}
	
	@PutMapping("/api/users")
	public User UpdateUser(@RequestBody User user, @RequestHeader("Authorization")String jwt) throws UserException {
		
		User reqUser = userService.FindUserByJwt(jwt);
		
		User updatedUser = userService.UpdateUser(user, reqUser.getId());
		return updatedUser;
		
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User FollowUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws UserException {
		
		User reqUser = userService.FindUserByJwt(jwt);
		User user = userService.FollowUser(reqUser.getId(), userId2);
		return user;
		
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userService.SearchUser(query);
		return users;
		
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		
		User user = userService.FindUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
	
}
