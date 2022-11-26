package com.devs4j.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.User;
import com.devs4j.users.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Page<User> getUsers(int page, int size){
		return userRepository.findAll(PageRequest.of(page, size));		//Con esto ya tenemos soporte para paginación
		//return userRepository.findAll();
	}
	
	public User getUserById(Integer userId) {
		Optional<User> result = userRepository.findById(userId);
		return result.orElseThrow(
						()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d not found", userId))
					  );
		
	}
	
	public User getUserByUsername(String username) {
		Optional<User> result = userRepository.findByUsername(username);	//Uso mi query Method de UserRepository.java
		return result.orElseThrow(
						()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found", username))
					  );
	}
	
	
	public User getUserByUsernameAndPassword(String username, String password) {
		Optional<User> result = userRepository.findByUsernameAndPassword(username, password);	//Uso mi query Method de UserRepository.java
		return result.orElseThrow(
						()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found", username))
					  );
	}

	public List<String> getUsernames() {
		return userRepository.findUsernames();
	}


	
}
