package com.pafker.customer.service;

import java.math.BigDecimal;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pafker.entity.Customer;
import com.pafker.entity.Order;
import com.pafker.util.HibernateUtil;

public class OrderService {

	private Session tempSession;
	private BigDecimal totalPrize;

	CustomerService customerService = new CustomerService();

	private Session createSession() throws HibernateException {
		tempSession = HibernateUtil.getSessionFactory().getCurrentSession();
		return tempSession;
	}

	private void commitSession(Session tempSession) {
		tempSession.getTransaction().commit();
	}

	public void createOrder() {
		tempSession = createSession();
		System.out.println("--> Begin transcation");
		tempSession.beginTransaction();
		System.out.println("--> Save object");
		Customer tempCustomer = customerService.getCustomerById(tempSession);
		Order tempOrder = new Order(setTotalPrizeForOrder());
		tempCustomer.add(tempOrder);
		tempSession.save(tempOrder);
		commitSession(tempSession);
		System.out.println("--> Create object done.");
	}

	public BigDecimal setTotalPrizeForOrder() {
		// temporary method
		// TODO: implement auto-set prize
		totalPrize = new BigDecimal("43.99");
		return totalPrize;
	}

	public void getCustomerOrders() {
		tempSession = createSession();
		System.out.println("--> Begin transcation");
		tempSession.beginTransaction();
		System.out.println("--> Get customer orders");
		Customer tempCustomer = customerService.getCustomerById(tempSession);
		System.out.println(tempCustomer + " has " + tempCustomer.getOrders());
	}

	public void changeOrderStatus() {
		tempSession = createSession();
		System.out.println("--> Begin transcation");
		tempSession.beginTransaction();
		System.out.println("--> Change order status");
		Order tempOrder = tempSession.get(Order.class, getId());
		tempOrder.setOrderStatus("true");
		commitSession(tempSession);
		System.out.println("--> Update done.");
	}

	private int getId() {
		Scanner skaner = new Scanner(System.in);
		System.out.println("Podaj id zamowienia.");
		int id = Integer.valueOf(skaner.nextLine());
		skaner.close();
		return id;
	}

}
