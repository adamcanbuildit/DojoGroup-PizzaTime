package com.dojogroup.pizzatime.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dojogroup.pizzatime.models.*;
import com.dojogroup.pizzatime.repositories.*;

@Service
public class UserService {
	private final UserRepository userRepo;
	private final OrderRepository orderRepo;
	public UserService(UserRepository userRepo, OrderRepository orderRepo) {
        this.userRepo = userRepo;
        this.orderRepo = orderRepo;
    }
	
	// Register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepo.save(user);
    }
    // Find user by email
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
    // Find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    // Authenticate user
    // (returns False is user doesn't exist OR if password doesn't match)
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public List<Order> getAllOrdersByUser(Long id) {
        User user = userRepo.findById(id).get();
        return user.getOrders();
    }
    
    public void favoriteOrderById(Long userId, Long orderId) {
    	User user = userRepo.findById(userId).get();
		Order order = orderRepo.findById(orderId).get();
    	if(user.getFavoriteOrders().contains(order)) {
    		user.getFavoriteOrders().remove(order);
    		userRepo.save(user);
    	} else {
    		System.out.println("2");
    		System.out.println(user.getFirstName());
    		System.out.println(user.getFavoriteOrders());
    		System.out.println(order);
    		user.getFavoriteOrders().add(order);
    		System.out.println(user.getFavoriteOrders());
    		userRepo.save(user);
    		System.out.println(user.getFavoriteOrders());
    	}
    }

}
