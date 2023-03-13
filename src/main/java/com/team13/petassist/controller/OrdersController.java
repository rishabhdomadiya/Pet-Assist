package com.team13.petassist.controller;

import java.sql.SQLException;
import java.util.List;

import com.team13.petassist.entity.PetItems;
import com.team13.petassist.entity.PlaceOrderForm;
import com.team13.petassist.interfaces.IOrderRepo;
import com.team13.petassist.interfaces.IPetItems;
import com.team13.petassist.repo.LoginRepo;
import com.team13.petassist.repo.OrderRepo;
import com.team13.petassist.repo.PetItemRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.team13.petassist.entity.Order;
import com.team13.petassist.entity.interfaces.IOrder;

@Controller
public class OrdersController {
	@Value("${url}")
	String url;
	@Value("${user1}")
	String user;
	@Value("${passwordDb}")
	String passwordDb;
	LoginRepo loginRepo;
	IOrderRepo orderRepo;

	@RequestMapping(value = "/ManageOrders/{userId}", method = RequestMethod.GET)
	public String showOrderStatus(@PathVariable String userId, Model model) throws SQLException {

		loginRepo = LoginRepo.getInstance(url, user, passwordDb);
		String userRole = loginRepo.getUserRoleById(userId);
		orderRepo = OrderRepo.getInstance(url, user, passwordDb);
		if (userRole.equals("Admin")) {

			List<IOrder> orders = orderRepo.getAllOrders(null);
			model.addAttribute("orders", orders);
			model.addAttribute("userRole", "Admin User");
			return "ManageOrders";
		}

		else {

			List<IOrder> orders = orderRepo.getAllOrders(userId);

			model.addAttribute("orders", orders);

			model.addAttribute("userRole", "Normal User");
			return "ManageOrders";

		}

	}
	
	

	@RequestMapping(value = "/EditOrder/{userId}")
	public String updateOrderDetails(@PathVariable("userId") String userId, @ModelAttribute Order order, Model model)
			throws SQLException {

		orderRepo = OrderRepo.getInstance(url, user, passwordDb);
		orderRepo.updateOrder(order);

		return "redirect:/ManageOrders/" + userId;
	}

	@RequestMapping(value = "/ManageOrders/{userId}/EditOrderDetails/{id}")
	public String editOrderDetails(@PathVariable("id") String id, @PathVariable("userId") String userId, Model model)
			throws SQLException {

		loginRepo = LoginRepo.getInstance(url, user, passwordDb);
		String userRole = loginRepo.getUserRoleById(userId);
		orderRepo = OrderRepo.getInstance(url, user, passwordDb);
		if (userRole.equals("Admin")) {

			IOrder order = orderRepo.getOrder(id);

			model.addAttribute("order", order);
			model.addAttribute("userId", userId);
			return "EditOrder";
		}

		else {
			model.addAttribute("userId", userId);
			return "Forbidden";

		}

	}

	@RequestMapping(value = "/place-order/{userId}/pet-item/{itemId}")
	public String placeOrderForm(Model model, @PathVariable("userId") String userId, @PathVariable("itemId") int itemId)
			throws SQLException {

		IPetItems petItemRepo = PetItemRepo.getInstance(url, user, passwordDb);
		PetItems petItem = petItemRepo.getPetItemByItemId(itemId);
		model.addAttribute("petItem", petItem);
		model.addAttribute("userId", userId);
		return "place-order-form";
	}

	@PostMapping("/place-order")
	public String placeOrder(@ModelAttribute(name = "placeOrderForm") PlaceOrderForm placeOrderForm) throws SQLException {

		int itemId = placeOrderForm.getItemId();
		int userId = placeOrderForm.getUserId();
		IPetItems petItemRepo = PetItemRepo.getInstance(url, user, passwordDb);
		PetItems petItem = petItemRepo.getPetItemByItemId(itemId);
		orderRepo = OrderRepo.getInstance(url, user, passwordDb);
		orderRepo.storeOrder(petItem, placeOrderForm);

		return "redirect:/pet-items/" + userId;
	}

}

