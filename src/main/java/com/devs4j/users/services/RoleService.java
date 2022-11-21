package com.devs4j.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devs4j.users.entities.Role;
import com.devs4j.users.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	public List<Role> getRoles(){
		return repository.findAll();
	}

	
	public Role createRole(Role role) {
		return repository.save(role);
	}
	
}
