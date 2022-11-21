package com.devs4j.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.users.entities.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>{

}
