package com.shaswat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shaswat.config.JwtProvider;
import com.shaswat.models.User;
import com.shaswat.repository.IUserRepository;
import com.shaswat.request.LoginRequest;
import com.shaswat.response.AuthResponse;
import com.shaswat.service.CustomerUserDetailService;
import com.shaswat.service.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	
	@PostMapping("/signup")
	public AuthResponse CreateUser(@RequestBody User user) throws Exception {
		
		User doesExist = userRepository.findByEmail(user.getEmail());
		
		if(doesExist != null) throw new Exception("Email alerady in use with another account");
		
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setGender(user.getGender());
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		String token = JwtProvider.GenerateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token, "Register success");
		
		return authResponse;
	}
	
	@PostMapping("/signin")
	public AuthResponse Signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword()); 
		
		String token = JwtProvider.GenerateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token, "Login success");
		
		return authResponse;
	}

	private Authentication authenticate(String email, String password) {

		UserDetails userDetails = customerUserDetailService.loadUserByUsername(email);
		
		if(userDetails == null) throw new BadCredentialsException("invalid username");
		else if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not matched");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
}
