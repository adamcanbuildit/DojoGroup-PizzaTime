package com.dojogroup.pizzatime.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.dojogroup.pizzatime.models.*;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findAll();
	
	Optional<Order> findById(Long id);
}
