package com.dojogroup.pizzatime.services;

import org.springframework.stereotype.Service;

import com.dojogroup.pizzatime.models.*;
import com.dojogroup.pizzatime.repositories.OrderRepository;

@Service
public class OrderService {
	private final OrderRepository orderRepo;
	public OrderService(OrderRepository orderRepo) {
		this.orderRepo=orderRepo;
	}
	
	// Create a new order
	public Order createOrder(Order order) {
		return orderRepo.save(order);
	}
	
}
