package com.dojogroup.pizzatime.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dojogroup.pizzatime.repositories.OrderRepository;
import com.dojogroup.pizzatime.models.Order;
import com.dojogroup.pizzatime.models.User;
import com.dojogroup.pizzatime.repositories.UserRepository;

@Service
public class OrderService{
@Autowired
	private OrderRepository oRepo;
	private UserRepository uRepo;
	public OrderService(OrderRepository repository) {
	this.oRepo = repository;
	}
	
public List<Order> getAllOrders(){
	return this.oRepo.findAll();
}
	
public Order getOneOrder(Long id) {
	return this.oRepo.findById(id).orElse(null);
}

public Order createOrder(Order Order) {
	return this.oRepo.save(Order);
}

public Order editOrder(Order Order) {
	return this.oRepo.save(Order);
}

public String deleteOrder(Long id) {
	this.oRepo.deleteById(id);
	return "DELORTED";
}

public List<Order> getAllOrdersByUser(Long id, String email) {
	User user=uRepo.findByEmail(email);
	oRepo.findById(id);
	List<Order> allOrdersByUser= ((OrderRepository) UserRepository.orders).findAll();
	return allOrdersByUser;
}
}