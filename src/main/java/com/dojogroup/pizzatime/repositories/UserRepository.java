package com.dojogroup.pizzatime.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojogroup.pizzatime.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
