package com.team13.petassist.repo;

import com.team13.petassist.entity.Order;
import com.team13.petassist.entity.PetItems;
import com.team13.petassist.entity.PlaceOrderForm;
import com.team13.petassist.entity.interfaces.IOrder;
import com.team13.petassist.interfaces.IPetItems;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.team13.petassist.interfaces.IOrderRepo;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepoTest {

	@Value("${url_test1}")
	String url;
	@Value("${user_test1}")
	String user;
	@Value("${passwordDb_test1}")
	String passwordDb;

	IOrderRepo orderRepository;

	@Test
	void getAllOrdersTestAdmin() throws SQLException {

		orderRepository = OrderRepo.getInstance(url, user, passwordDb);
		List<IOrder> orders = orderRepository.getAllOrders(null);
		assertNotNull(orders);

	}

	@Test
	void getAllOrdersTestNotmalUser() throws SQLException {

		orderRepository = OrderRepo.getInstance(url, user, passwordDb);
		List<IOrder> orders = orderRepository.getAllOrders("1");
		assertNotNull(orders);

	}

	@Test
	public void updateOrderTest() throws SQLException {
		orderRepository = OrderRepo.getInstance(url, user, passwordDb);
		IOrder o = orderRepository.getOrder("2");
		String old = o.getItemQuantity();
		Integer oldInt = Integer.parseInt(old);
		Integer newInt = oldInt + 1;
		String newIntStr = "" + (newInt);

		o.setItemQuantity(newIntStr);

		orderRepository.updateOrder(o);

		o = orderRepository.getOrder("2");

		assertEquals(newIntStr, o.getItemQuantity());

	}

	@Test
	public void storeOrderTest() throws SQLException {
		IOrderRepo orderRepo = OrderRepo.getInstance(url, user, passwordDb);
		IPetItems petItemRepo = PetItemRepo.getInstance(url, user, passwordDb);
		PetItems petItem = petItemRepo.getPetItemByItemId(1);
		PlaceOrderForm placeOrderForm = new PlaceOrderForm();
		placeOrderForm.setAddress("Address");
		placeOrderForm.setItemDescription("Item Description");
		placeOrderForm.setItemId(1);
		placeOrderForm.setItemQuantity(2);
		placeOrderForm.setItemName("Itemname");
		placeOrderForm.setItemWeight(25);
		placeOrderForm.setTotalCost(30);
		placeOrderForm.setUserContact("9854785442");
		String result = orderRepo.storeOrder(petItem, placeOrderForm);

		assertEquals(result, "pet-items");
	}
}