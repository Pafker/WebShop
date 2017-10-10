package com.pafker.customer.service;

public class WebShop {

	public static void main(String[] args) {

		CustomerService customerService = new CustomerService();
		OrderService orderService = new OrderService();

		// create customer
		customerService.createCustomer();

		// query customer
		customerService.queryCustomer();

		// display all customers
		customerService.displayCustomerList();

		// update customer
		customerService.updateCustomer();

		// delete customer
		customerService.deleteCustomer();

		// create order for customer
		orderService.createOrder();

		// display customer's orders
		orderService.getCustomerOrders();

		// change order status
		orderService.changeOrderStatus();

	}

}
