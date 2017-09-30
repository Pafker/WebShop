package com.pafker.customer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pafker.entity.Customer;
import com.pafker.util.HibernateUtil;

public class CustomerService {

	private Scanner skaner = new Scanner(System.in);
	
	private void commitSession(Session session) {
		session.getTransaction().commit();
	}
	
	private Session createSession() throws HibernateException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return session;
	}

	public void createCustomerDatabase(String firstName, String lastName,
			String email, String registryDate) {
		Session session = createSession();
		Customer tempCustomer = new Customer(firstName, lastName, email,
				registryDate);
		System.out.println("--> Begin transcation");
		session.beginTransaction();
		System.out.println("--> Save object");
		session.save(tempCustomer);
		commitSession(session);
		System.out.println("--> Create object done.");
	}

	public void createCustomer(CustomerService service) {
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm:ss");
		String firstName;
		String lastName;
		String email;
		String registryDate;
		System.out.println("Podaj imie.");
		firstName = skaner.nextLine();
		System.out.println("Podaj nazwisko.");
		lastName = skaner.nextLine();
		System.out.println("Podaj email.");
		email = skaner.nextLine();
		registryDate = LocalDateTime.now().format(formatter);
		service.createCustomerDatabase(firstName, lastName, email, registryDate);
	}

	public void displayCustomerList() {
		Session session = createSession();
		System.out.println("--> Begin transaction");
		session.beginTransaction();
		System.out.println("--> Display all customers");
		List<Customer> customerList = session.createQuery("from Customer")
				.getResultList();
		displayCustomers(customerList);
		commitSession(session);
		System.out.println("--> Query done.");
	}

	public void queryCustomerDatabase(String query) {
		Session session = createSession();
		System.out.println("--> Begin transaction");
		session.beginTransaction();
		System.out.println("--> Call query with attribute: " + query);
		List<Customer> customerList = session.createQuery(
				"from Customer where first_name='" + query + "' or last_name='"
						+ query + "'").getResultList();
		displayCustomers(customerList);
		commitSession(session);
		System.out.println("--> Query done.");
	}

	public void queryCustomer(CustomerService service) {
		String query;
		System.out.println("Podaj imie lub nazwisko klienta do wyszukania");
		query = skaner.nextLine();
		service.queryCustomerDatabase(query);

	}

	private static void displayCustomers(List<Customer> customers) {
		for (Customer tempCustomer : customers)
			System.out.println(tempCustomer);
	}

	public void updateCustomer(CustomerService service) {
		Session session = createSession();
		System.out.println("--> Begin transaction");
		session.beginTransaction();
		System.out.println("Podaj id klienta.");
		int id = Integer.valueOf(skaner.nextLine());
		Customer tempCustomer = session.get(Customer.class, id);
		chooseAttributeToChange(tempCustomer);
		System.out.println("--> Updating customer data");
		commitSession(session);
		System.out.println("--> Update done.");

	}

	private void chooseAttributeToChange(Customer tempCustomer) {
		System.out.println("Co chcesz zmieniæ: 1. imie; 2. nazwisko; 3. email");
		int chose = Integer.valueOf(skaner.nextLine());
		String temp;
		switch (chose) {
		case 1: {
			System.out.println("Jakie jest nowe imie?");
			temp = skaner.nextLine();
			tempCustomer.setFirstName(temp);
			break;
		}
		case 2: {
			System.out.println("Jakie jest nowe nazwisko?");
			temp = skaner.nextLine();
			tempCustomer.setLastName(temp);
			break;
		}
		case 3: {
			System.out.println("Jaki jest nowy email?");
			temp = skaner.nextLine();
			tempCustomer.setEmail(temp);
			break;
		}
		default:
			System.out.println("Zly wybor!");
			break;
		}
	}

	public void deleteCustomer(CustomerService service) {
		Session session = createSession();
		System.out.println("--> Begin transaction");
		session.beginTransaction();
		System.out.println("Podaj id klienta.");
		int id = Integer.valueOf(skaner.nextLine());
		Customer tempCustomer = session.get(Customer.class, id);
		System.out.println("--> Deleting customer data");
		session.delete(tempCustomer);
		System.out.println("--> Delete done.");
		commitSession(session);
		
	}

	

}
