package com.devs4j.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
