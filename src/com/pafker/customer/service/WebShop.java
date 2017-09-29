package com.pafker.customer.service;

public class WebShop {

	public static void main(String[] args) {

		CustomerService service = new CustomerService();

		// create customer
		//service.createCustomer(service);

		// query customer
		//service.queryCustomer(service);
		
		// display all customers
		service.displayCustomerList();

		// update customer
		//service.updateCustomer(service);
		
		// delete customer
		service.deleteCustomer(service);
		
		
		
	}

}
