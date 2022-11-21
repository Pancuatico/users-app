package com.devs4j.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.models.User;
import com.github.javafaker.Faker;

@Service
public class UserService {
	
	@Autowired
	private Faker faker;
	
	private List<User> users = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i = 0; i < 20; i++) {
			users.add(new User(faker.funnyName().name(),faker.name().username(),faker.dragonBall().character()));	
		}
		
	}
	
	public List<User> getUsers(){				//Retorno todos los usuarios
		return users;
	}
	
	public List<User> getUsersStartWith(String startWith) {		//QUEDÓ PENDIENTE EN EL CONTROLLER PORQUE NOS ARROJA AMBIGUOUS POR EL @GetMapping SOLO QUE YA TIENE getUsers
		if(startWith != null) {
			return users.stream().filter(				//HACER MÁS EJERCICIOS
					u->u.getUsername()					//DE ESTO
					.startsWith(startWith))				//Y ESTO
					.collect(Collectors.toList());		//Y ESTO
		}else{
			return null;
		}
	}
	
	@GetMapping
	public User getUserByUsername(String username) {			//Retorno un usuario por username
		return users.stream().filter(u->u.getUsername().equals(username)).findAny()	//filtro mi lista de usuarios y obtengo el resultado
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,		//si no hay resultados
		String.format("User %s not found ", username)));							//arrojo una exception
	}
	
	
	public User createUser(User user) {			//Creo un nuevo usuario
		if(users.stream().anyMatch(u->u.getUsername().equals(user.getUsername()))) {	//Si ya existe este username
			throw new ResponseStatusException(
					HttpStatus.CONFLICT, String.format("User %s already exists", user.getUsername())
			);
		}
		users.add(user);
		return user;							//Retorno esto solo por buena práctica
	}
	
	public User updateUserByUsername(User user, String username) {	//Actualizo user por username
		User userToBeUpdated = getUserByUsername(username);
		userToBeUpdated.setNickName(user.getNickName());
		userToBeUpdated.setPassword(user.getPassword());
		return userToBeUpdated;					//Retorno esto solo por buena práctica
	}
	
	public void deleteUserByUsername(String username) {		//Elimino un usuario por username
		User userToBeDeleted = getUserByUsername(username);
		users.remove(userToBeDeleted);
	}
	
	
	
	
}
