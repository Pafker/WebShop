package com.pafker.customer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pafker.entity.Customer;
import com.pafker.entity.CustomerDeliveryAddress;
import com.pafker.util.HibernateUtil;

public class CustomerService {

	private Scanner skaner = new Scanner(System.in);
	private Session tempSession;
	private Customer tempCustomer;
	private CustomerDeliveryAddress customerDeliveryAddress;

	CustomerDeliveryAddressService addressService = new CustomerDeliveryAddressService();

	private Session createSession() throws HibernateException {
		tempSession = HibernateUtil.getSessionFactory().getCurrentSession();
		return tempSession;
	}

	private void commitSession(Session tempSession) {
		tempSession.getTransaction().commit();
	}

	public void createCustomer() {
		saveCustomerInDatabase(getCustomerData(), customerDeliveryAddress);
	}

	public void saveCustomerInDatabase(Customer tempCustomer,
			CustomerDeliveryAddress tempAddress) {
		tempSession = createSession();
		System.out.println("--> Begin transcation");
		tempSession.beginTransaction();
		System.out.println("--> Save object");
		customerDeliveryAddress = addressService.getDeliveryAddressData();
		tempCustomer.setCustomerDeliveryAddres(customerDeliveryAddress);
		tempSession.save(tempCustomer);
		commitSession(tempSession);
		System.out.println("--> Create object done.");
	}

	public Customer getCustomerData() {
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
		tempCustomer = new Customer(firstName, lastName, email, registryDate);
		return tempCustomer;
	}

	public void queryCustomer() {
		queryCustomerInDatabase(getQueryData());
	}

	public String getQueryData() {
		String query;
		System.out.println("Podaj imie lub nazwisko klienta do wyszukania");
		query = skaner.nextLine();
		return query;

	}

	public void queryCustomerInDatabase(String query) {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		System.out.println("--> Call query with attribute: " + query);
		List<Customer> customerList = tempSession.createQuery(
				"from Customer where first_name='" + query + "' or last_name='"
						+ query + "'").getResultList();
		displayCustomers(customerList);
		commitSession(tempSession);
		System.out.println("--> Query done.");
	}

	public void displayCustomerList() {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		System.out.println("--> Display all customers");
		List<Customer> customerList = tempSession.createQuery("from Customer")
				.getResultList();
		displayCustomers(customerList);
		commitSession(tempSession);
		System.out.println("--> Query done.");
	}

	private static void displayCustomers(List<Customer> customers) {
		for (Customer tempCustomer : customers)
			System.out.println(tempCustomer);
	}

	public void updateCustomer() {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		chooseAttributeToChange(getCustomerById(tempSession));
		System.out.println("--> Updating customer data");
		commitSession(tempSession);
		System.out.println("--> Update done.");

	}

	public Customer getCustomerById(Session tempSession)
			throws NumberFormatException {
		System.out.println("Podaj id klienta.");
		int id = Integer.valueOf(skaner.nextLine());
		Customer tempCustomer = tempSession.get(Customer.class, id);
		return tempCustomer;
	}

	public void deleteCustomer() {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		System.out.println("--> Deleting customer data");
		tempSession.delete(getCustomerById(tempSession));
		System.out.println("--> Delete done.");
		commitSession(tempSession);

	}

	private void chooseAttributeToChange(Customer tempCustomer) {
		System.out
				.println("Co chcesz zmieniæ: 1. imie; 2. nazwisko; 3. email; 4. adres");
		int choice = Integer.valueOf(skaner.nextLine());
		String temp;
		switch (choice) {
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
		case 4: {
			System.out.println("Jaki jest nowy adres?");
			customerDeliveryAddress = addressService.getDeliveryAddressData();
			tempCustomer.setCustomerDeliveryAddres(customerDeliveryAddress);
		}
		default:
			System.out.println("Zly wybor!");
			break;
		}
	}

}
