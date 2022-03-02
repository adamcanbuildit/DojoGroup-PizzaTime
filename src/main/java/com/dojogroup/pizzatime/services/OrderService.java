package main.java.com.dojogroup.pizzatime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dojogroup.pizzatime.repositories.OrderRepository;

@Service
public class OrderService{
@Autowired
	private OrderRepository oRepo;
	public OrderService(OrderRepository repository) {
	this.oRepo = repository;
	}
	
public List<Order> getAllOrders(){
	return this.oRepo.findall();
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
}