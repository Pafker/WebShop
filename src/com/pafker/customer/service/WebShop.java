package com.pafker.customer.service;

public class WebShop {

	public static void main(String[] args) {

		CustomerService customerService = new CustomerService();

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

	}

}
