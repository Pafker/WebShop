package com.pafker.service;

import java.math.BigDecimal;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pafker.entity.Customer;
import com.pafker.entity.Order;
import com.pafker.entity.OrderDetail;
import com.pafker.entity.Product;
import com.pafker.util.HibernateUtil;

public class OrderService {

	private Session tempSession;
	private BigDecimal totalPrize;
	private Scanner skaner = new Scanner(System.in);
	private BigDecimal productValue;

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
		System.out.println("--> Retrieve object");
		Customer tempCustomer = customerService.getCustomerById(tempSession);
		Order tempOrder = new Order();
		tempCustomer.addOrder(tempOrder);
		tempSession.save(tempOrder);
		addProductsToOrder(tempOrder);

		tempSession.save(tempOrder);
		commitSession(tempSession);
		System.out.println("--> Create object done.");
	}

	private void addProductsToOrder(Order tempOrder)
			throws NumberFormatException {
		System.out.println("Ile produktow chcesz zamowic?");
		int n = Integer.valueOf(skaner.nextLine());
		BigDecimal initialVal = new BigDecimal("0.00");
		tempOrder.setTotalPrize(initialVal);
		System.out.println("Obecna cena za zamowienie: "
				+ tempOrder.getTotalPrize());
		for (int i = 0; i < n; i++) {
			addProduct(tempOrder, initialVal);
		}

	}

	private void addProduct(Order tempOrder, BigDecimal initialVal) {
		System.out.println("Podaj id produktu");
		int prodId = Integer.valueOf(skaner.nextLine());
		Product tempProduct = tempSession.get(Product.class, prodId);
		System.out.println("Podaj ilosc");
		int quantity = Integer.valueOf(skaner.nextLine());
		OrderDetail tempDetail = new OrderDetail(quantity);
		tempDetail.setOrder(tempOrder);
		tempDetail.setProduct(tempProduct);
		productValue = tempProduct.getPrize()
				.multiply(new BigDecimal(quantity));
		System.out.println("Ten produkt bedzie kosztowal: " + productValue);
		totalPrize = tempOrder.getTotalPrize().add(productValue);
		System.out
				.println("Ten produkt plus poprzednia wartosc: " + totalPrize);
		tempOrder.setTotalPrize(totalPrize);
		tempSession.save(tempDetail);
	}

	public void getCustomerOrders() {
		tempSession = createSession();
		System.out.println("--> Begin transcation");
		tempSession.beginTransaction();
		System.out.println("--> Get customer orders");
		Customer tempCustomer = customerService.getCustomerById(tempSession);
		System.out
				.println(tempCustomer + " has: \n" + tempCustomer.getOrders());
		commitSession(tempSession);
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
		System.out.println("Podaj id zamowienia.");
		int id = Integer.valueOf(skaner.nextLine());
		return id;
	}

}
