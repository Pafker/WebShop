package com.pafker.customer.service;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pafker.entity.Customer;
import com.pafker.entity.CustomerDeliveryAddress;
import com.pafker.entity.Order;

public class test {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerDeliveryAddress.class)
				.addAnnotatedClass(Order.class).buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		// use the session object to save java object
		try {

			// start a transaction
			System.out.println("Begin transaction");
			session.beginTransaction();
			int id = 5;
			Customer tempCustomer = session.get(Customer.class, id);

			BigDecimal prize1 = new BigDecimal("23.45");
			BigDecimal prize2 = new BigDecimal("17.99");
			BigDecimal prize3 = new BigDecimal("45.89");

			Order order1 = new Order(prize1);
			Order order2 = new Order(prize2);
			Order order3 = new Order(prize3);
			tempCustomer.add(order1);
			tempCustomer.add(order2);
			tempCustomer.add(order3);

			session.save(order1);
			session.save(order2);
			session.save(order3);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			session.close();
			factory.close();
		}

	}

}
