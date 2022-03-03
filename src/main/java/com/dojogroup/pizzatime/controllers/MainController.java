package com.dojogroup.pizzatime.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dojogroup.pizzatime.models.*;
import com.dojogroup.pizzatime.services.*;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService oService;
	
	//Landing page redirects to /home or /authentication
	@RequestMapping("/")
	public String landingPage(HttpSession session) {
		// if user already logged in, go to home page "/home"
		if (session.getAttribute("userId")!=null) {
			return "redirect:/home";
		}
		// if no user logged in, load authentication page
		return "redirect:/authentication";
	}
	
	//GET - authentication page
	@RequestMapping("/authentication")
	public String registerForm(@ModelAttribute("user") User user, HttpSession session) {
		// if user already logged in, go to home page "/home"
		if (session.getAttribute("userId")!=null) {
			return "redirect:/home";
		}
		// if no user logged in, load authentication page
		return "RegistrationAndLogin.jsp";
	}
	
	// POST - REGISTER User
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result,
			HttpSession session) {
		// if there are errors with registration, add error message and return the registration page
		if (result.hasErrors()) {
			model.addAttribute("register_error", "Couldn't register user. Please try again.");
			return "RegistrationAndLogin.jsp";
		//check if email in use
		} else if (userService.findByEmail(user.getEmail()) != null) {
			model.addAttribute("register_error", "That email is already in use. Please try again.");
			return "RegistrationAndLogin.jsp";
		//check if passwords match
		} else if (!user.getPassword().equals(user.getPasswordConfirmation())) {
			model.addAttribute("register_error", "Passwords don't match. Please try again.");
			return "RegistrationAndLogin.jsp";
		// else, register the user and save the user.id to session as userId
		} else {
			User u = userService.registerUser(user);
			session.setAttribute("userId", u.getId());
			return "redirect:/home";
		}
	}

	// POST -  Login User
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			HttpSession session) {
		// if the user is authenticated, save their user id in session and redirect home
		if (userService.authenticateUser(email, password)) {
			session.setAttribute("userId", userService.findByEmail(email).getId());
			return "redirect:/home";
		}
		// else, add error messages and return the login page
		else {
			model.addAttribute("login_error", "User authentication error. Please try again.");
			return "RegistrationAndLogin.jsp";
		}
	}
	// GET - Logout User
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// invalidate session and redirect to login page
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/home")
	public String dashboard(Model model, HttpSession session) {
		// get user from session, save them in the model and return requested page
		// if no user, return to login
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("userId", currentUserId);
			model.addAttribute("favoriteOrders", userService.findUserById(currentUserId).getFavoriteOrders());
			return "Home.jsp";
			//needs to give:
			//most common order, new pizza button, a randomiser
			//randomiser goes here
		}
	}

	// GET - View Account details
	@RequestMapping("/account/{id}")
	public String account(Model model, HttpSession session, @PathVariable("id") Long id) {
		// get user from session, save them in the model and return requested page
		// if no user, return to login
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("userId", currentUserId);
			// get past orders and favorite orders
			List<Order> pastOrders = userService.getAllOrdersByUser(id);
			List<Order> favoriteOrders = userService.findUserById(id).getFavoriteOrders();
			// remove the favorites from the order list to make displaying them on .jsp easier
			for (Order order : favoriteOrders) {
				pastOrders.remove(order);
			}
			// add the two order lists to the model to be displayed
			model.addAttribute("user", userService.findUserById(currentUserId));
			model.addAttribute("pastOrders", pastOrders);
			model.addAttribute("favoriteOrders", favoriteOrders);
			return "Account.jsp";
		}
	}
	
	//POST - Favorite an Order
	@PostMapping("/favorite/{id}")
	public String favoriteAnOrder(@PathVariable("id") Long orderId, HttpSession session) {
		// get user from session, save them in the model and return requested page
		// if no user, return to login
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		} else {
			User user = userService.findUserById(currentUserId);
			Order order = oService.getOneOrder(currentUserId);
			userService.favoriteOrderById(user,order);
			return "redirect:/account/" + currentUserId;
		}
	}
	
	// GET - Create Order
	@RequestMapping("/order")
	public String orderForm(@ModelAttribute("order") Order order, Model model, HttpSession session) {
		// get user from session, save them in the model and return requested page
		// if no user, return to login
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("userId", currentUserId);
			model.addAttribute("user", userService.findUserById(currentUserId));
			model.addAttribute("order", new Order());
			return "CreateOrder.jsp";
		}
	}
	// POST - Create Order
	@PostMapping("/createorder")
	public String createOrder(Model model, HttpSession session, @Valid @ModelAttribute("order") Order order, BindingResult result) {
		// get user from session, save them in the model and return requested page
		// if no user, return to login
		Long currentUserId = (Long) session.getAttribute("userId");
		if (currentUserId == null) {
			return "redirect:/";
		} else {
			// add currentOrder to session
			Order currentOrder = oService.createOrder(order); 
			return "redirect:/checkout/"+currentOrder.getId();
		}
	}
	
	// GET - Checkout
//	@RequestMapping("/checkout/{id}")
//	public String checkoutPage(Model model, @PathVariable("id") Long orderId, HttpSession session) {
//		// get user from session, save them in the model and return requested page
//		// if no user, return to login
//		Long currentUserId = (Long) session.getAttribute("userId");
//		if (currentUserId == null) {
//			return "redirect:/";
//		} else {
//			Order currentOrder = oService.getOneOrder(orderId);
//		}
//	}
}
