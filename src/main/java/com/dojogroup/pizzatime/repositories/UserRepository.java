package com.dojogroup.pizzatime.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojogroup.pizzatime.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	Object orders = null;
	List<User> findAll();
	
	User findByEmail(String email);
	User findUserById(Long id);
}
