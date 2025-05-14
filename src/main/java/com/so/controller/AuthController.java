package com.so.controller;

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

import com.so.config.JwtProvider;
import com.so.models.User;
import com.so.repository.UserRepository;
import com.so.request.LoginRequest;
import com.so.response.AuthResponse;
import com.so.service.CustomUserDetailsService;
import com.so.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if(isExist!=null) {
			throw new Exception("Email already used with another account");
		}
		
		User newUser= new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		User savedUser=userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res= new AuthResponse(token,"Registered Successfully");
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = 
				authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		
String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res= new AuthResponse(token,"Login Successfully");
		
		return res;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(email);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not matched");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, 
				null, 
				userDetails.getAuthorities());
	}
	
}
