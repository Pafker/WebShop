package com.pafker.customer.service;

public class WebShop {

	public static void main(String[] args) {

		CustomerService service = new CustomerService();

		// create customer
		service.createCustomer();

		// query customer
		//service.queryCustomer();
		
		// display all customers
		service.displayCustomerList();

		// update customer
		//service.updateCustomer();
		
		// delete customer
		//service.deleteCustomer();
		
			
		
		
	}

}
