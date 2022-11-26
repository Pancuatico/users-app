package com.devs4j.users.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.User;
import com.devs4j.users.services.UserService;

@RestController
@RequestMapping("/users2")
public class UserController {

	@Autowired
	private UserService service;
	
	//En este método puedo devolver todos los usuarios si no especifico un page&size
	//Y si los agrego así en la URL: users2?page=2&size=10 obtendré esa paginación
	@GetMapping
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(required=false, value="page", defaultValue="0") int page,
			@RequestParam(required=false, value="size", defaultValue="100") int size){
		return new ResponseEntity<>(service.getUsers(page, size), HttpStatus.OK);
	}
	
	
	//En nuestro UserRepository creamos para este endpoint un custom query con JPQL
	//De esa forma obtenemos solo usernames
	//Esto también podría paginarse
	@GetMapping("/usernames")
	public ResponseEntity<List<String>> getUserNames(){
		return new ResponseEntity<>(service.getUsernames(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId){
		return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);

	}
	
	@GetMapping("/username/{userName}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("userName") String userName){
		return new ResponseEntity<>(service.getUserByUsername(userName), HttpStatus.OK);

	}
	
	@PostMapping		//Esta autenticación no sería una buena práctica, es solo para ejemplo
	public ResponseEntity<User> authenticate(@RequestBody User user){
		return new ResponseEntity<>(
					service.getUserByUsernameAndPassword(
						user.getUsername(),
						user.getPassword()
						),
					HttpStatus.OK
				);

	}
	
	
}
