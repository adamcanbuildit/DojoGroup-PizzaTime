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
    private OrderService oService;
    private UserValidator uValid;
	public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
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
            }}}
    

    public List<Order> getAllOrdersByUser(Long id, String email) {
        List<Order> allOrders = userRepo.findByEmail(email).orders;
        return allOrders;
        
    }

    public Order favoriteOrderById(User user, Order order, Long id) {
        if(order.favoritedBy == user) {
            oService.getOneOrder(id);
            return order;
        } else {
            return null;
        }
    }

    public void validate(User user) {
        this.uValid.validate(user, null);
    }
    
    public List<Order> getAllOrdersByUser(Long id) {
        User user = userRepo.findById(id).get();
        return user.getOrders();
    }
    
    public void favoriteOrderById(User user, Order order) {
    	List<Order> favorites = (List<Order>) user.getFavoriteOrders();
    	if(favorites.contains(order)) {
    		return;
    	} else {
    		favorites.add(order);
    		user.setFavoriteOrders(favorites);
    		return;
    	}
    }



}
