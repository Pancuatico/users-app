package com.devs4j.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devs4j.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	
	//QueryMethods, ver hoja 8 para más ejemplos y alcance de estos queryMethods
	public Optional<User> findByUsername(String username);		//Spring implementará esta búsqueda basada en nuestra columna que está siendo indicada luego del findBy.. SI!, ES UNA COLUMNA DE NUESTRA ENTIDAD User
	public Optional<User> findByUsernameAndPassword(String username, String password);		//same

	
	//Usamos JPQL para nuestra custom query
	//DUDA: ¿Cómo le paso parámetros?
	@Query("SELECT u.username FROM User u WHERE u.username LIKE 'a%'")
	public List<String> findUsernames();
}
