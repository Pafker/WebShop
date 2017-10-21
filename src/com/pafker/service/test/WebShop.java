package com.pafker.service.test;

import com.pafker.service.CustomerService;
import com.pafker.service.OrderDetailService;
import com.pafker.service.OrderService;
import com.pafker.service.ProductService;

public class WebShop {

	public static void main(String[] args) {

		CustomerService customerService = new CustomerService();
		OrderService orderService = new OrderService();
		ProductService productService = new ProductService();
		OrderDetailService orderDetailService = new OrderDetailService();

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

		// create product
		productService.createProduct();

		// query product
		productService.queryProduct();

		// display all products
		productService.displayProductList();

		// update product
		productService.updateProduct();

		// delete product
		productService.deleteProduct();

		// change amount of product in order
		orderDetailService.changeQuantity();

	}

}
