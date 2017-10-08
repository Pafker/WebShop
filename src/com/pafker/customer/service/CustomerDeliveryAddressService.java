package com.pafker.customer.service;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pafker.entity.CustomerDeliveryAddress;
import com.pafker.util.HibernateUtil;

public class CustomerDeliveryAddressService {

	private Scanner skaner = new Scanner(System.in);
	private CustomerDeliveryAddress tempDeliveryAddress;
	private Session tempSession;

	private String country;
	private String city;
	private String postalCode;
	private String street;
	private String houseNumber;

	private Session createSession() throws HibernateException {
		tempSession = HibernateUtil.getSessionFactory().getCurrentSession();
		return tempSession;
	}

	private void commitSession(Session tempSession) {
		tempSession.getTransaction().commit();
	}

	public CustomerDeliveryAddress getDeliveryAddressData() {
		System.out.println("Podaj kraj.");
		country = skaner.nextLine();
		System.out.println("Podaj miasto.");
		city = skaner.nextLine();
		System.out.println("Podaj kod pocztowy.");
		postalCode = skaner.nextLine();
		System.out.println("Podaj nazwe ulicy");
		street = skaner.nextLine();
		System.out.println("Podaj numer domu i mieszkania");
		houseNumber = skaner.nextLine();
		tempDeliveryAddress = new CustomerDeliveryAddress(country, city,
				postalCode, street, houseNumber);
		return tempDeliveryAddress;
	}

}
