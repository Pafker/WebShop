package com.pafker.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import com.pafker.entity.Customer;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class).buildSessionFactory();
		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}