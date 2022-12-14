package com.devs4j.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.models.User;
import com.devs4j.users.services.UserServiceUsingLists;

@RestController
@RequestMapping("/users")
public class UserControllerUsingLists{

	@Autowired
	private UserServiceUsingLists userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
	}
	
	//QUEDA PENDIENTE ACLARAR CÓMO HACER ESTE ENDPOINT 
	//@GetMapping							//Como tenemos un @RequestParam no es necesario poner nada en el @GetMapping
	//public ResponseEntity<List<User>> getUsersStartWith(@RequestParam(value="startWith",required=false) String startWith){
	//	return new ResponseEntity<List<User>>(userService.getUsersStartWith(startWith),HttpStatus.OK);
	//}
	
	@GetMapping(value="/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
		return new ResponseEntity<User>(
				userService.getUserByUsername(username),HttpStatus.OK
				);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="/{username}")
	public ResponseEntity<User> updateUserByUserName(@RequestBody User user, @PathVariable("username") String username){
		return new ResponseEntity<User>(
				userService.updateUserByUsername(user, username),
				HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(value="/{username}")
	public ResponseEntity<Void> deleteUserByUsername(@PathVariable("username") String username){
		userService.deleteUserByUsername(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
