package com.shaswat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shaswat.config.JwtProvider;
import com.shaswat.exceptions.UserException;
import com.shaswat.models.User;
import com.shaswat.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	IUserRepository userRepository;
	
	@Override
	public User RegisterUser(User user) {
		
		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		
		User savedUser = userRepository.save(newUser);
		
		return savedUser;
	}

	@Override
	public User FindUserById(Integer userId) throws UserException {
		
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) return user.get();
		
		throw new UserException("User does not exist with userid=" + userId);
		
	}

	@Override
	public User FindUserByEmail(String email) {

		User user = userRepository.findByEmail(email);
		return user;
		
	}

	@Override
	public User FollowUser(Integer reqUserId, Integer userId2) throws UserException {

		
		User reqUser = FindUserById(reqUserId);
		User user2 = FindUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
		
	}

	@Override
	public User UpdateUser(User user, Integer userId) throws UserException {

		Optional<User> user1 = userRepository.findById(userId);
		
		if(user1.isEmpty()) throw new UserException("User not exist with id=" + userId);
		
		User existingUser = user1.get();
		
		if(user.getFirstName() != null)
			existingUser.setFirstName(user.getFirstName());
		
		if(user.getLastName() != null)
			existingUser.setLastName(user.getLastName());
		
		if(user.getEmail() != null)
			existingUser.setEmail(user.getEmail());
		
		if(user.getGender() != null)
			existingUser.setGender(user.getGender());
		
		User updatedUser = userRepository.save(existingUser);
		
		return updatedUser;
		
	}

	@Override
	public List<User> SearchUser(String query) {

		return userRepository.searchUser(query);
		
	}

	@Override
	public User FindUserByJwt(String jwt) {

		String email = JwtProvider.GetEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		return user;
	}

}
