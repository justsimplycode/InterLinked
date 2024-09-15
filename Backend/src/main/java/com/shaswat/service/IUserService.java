package com.shaswat.service;

import java.util.*;

import com.shaswat.exceptions.UserException;
import com.shaswat.models.User;

public interface IUserService {

	public User RegisterUser(User user);
	
	public User FindUserById(Integer userId) throws UserException;
	
	public User FindUserByEmail(String email);
	
	public User FollowUser(Integer userId1, Integer userId2) throws UserException;
	
	public User UpdateUser(User user, Integer userId) throws UserException;
	
	public List<User> SearchUser(String query);
	
	public User FindUserByJwt(String jwt);
	
}
