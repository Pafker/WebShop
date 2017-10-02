package com.pafker.customer.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pafker.entity.Customer;
import com.pafker.entity.CustomerDeliveryAddress;

public class tescik {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerDeliveryAddress.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		// use the session object to save java object
		try {
			// create a instructor object
			Customer tempCustomer = new Customer("Patryk", "Pawlos",
					"ppatrix555@gmail.com", "teraz");
			CustomerDeliveryAddress tempAddress = new CustomerDeliveryAddress(
					"Poland", "Krakow", "23-210", "Os. Tysiaclecia", "55m18");

			// associate objects
			tempCustomer.setCustomerDeliveryAddres(tempAddress);

			// start a transaction
			System.out.println("Begin transaction");
			session.beginTransaction();

			// save the student object
			System.out.println("Save object");
			session.save(tempCustomer);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done");

		} finally {
			factory.close();
		}

	}

}
