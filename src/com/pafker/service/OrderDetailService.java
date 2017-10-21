package com.pafker.service;

import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pafker.entity.OrderDetail;
import com.pafker.util.HibernateUtil;

public class OrderDetailService {

	private Session tempSession;
	private Scanner skaner = new Scanner(System.in);

	private Session createSession() throws HibernateException {
		tempSession = HibernateUtil.getSessionFactory().getCurrentSession();
		return tempSession;
	}

	private void commitSession(Session tempSession) {
		tempSession.getTransaction().commit();
	}

	public void changeQuantity() {
		tempSession = createSession();
		System.out.println("--> Begin transcation");
		tempSession.beginTransaction();
		System.out.println("--> Change amount of product.");
		OrderDetail tempOrderDetail = tempSession.get(OrderDetail.class,
				getId());
		System.out.println("Podaj nowa ilosc");
		int quantity = Integer.valueOf(skaner.nextLine());
		tempOrderDetail.setQuantity(quantity);
		commitSession(tempSession);
		System.out.println("--> Update done.");

	}

	private int getId() {
		Scanner skaner = new Scanner(System.in);
		System.out.println("Podaj id zamowienia.");
		int id = Integer.valueOf(skaner.nextLine());
		return id;
	}

}
