package com.devs4j.users.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.User;
import com.devs4j.users.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService service;
	
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
	}
	
}
