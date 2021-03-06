package com.pafker.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import com.pafker.entity.Customer;
import com.pafker.entity.CustomerDeliveryAddress;
import com.pafker.entity.Order;
import com.pafker.entity.OrderDetail;
import com.pafker.entity.Product;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(CustomerDeliveryAddress.class)
				.addAnnotatedClass(Order.class)
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(OrderDetail.class)
				.buildSessionFactory();
		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
