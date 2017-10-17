package com.pafker.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.pafker.entity.Product;
import com.pafker.util.HibernateUtil;

public class ProductService {

	private Scanner skaner = new Scanner(System.in);
	private Session tempSession;
	private Product tempProduct;

	private Session createSession() throws HibernateException {
		tempSession = HibernateUtil.getSessionFactory().getCurrentSession();
		return tempSession;
	}

	private void commitSession(Session tempSession) {
		tempSession.getTransaction().commit();
	}

	public void createProduct() {
		saveProductInDatabase(getProductData());
	}

	public void saveProductInDatabase(Product tempProduct) {
		tempSession = createSession();
		System.out.println("--> Begin transcation");
		tempSession.beginTransaction();
		System.out.println("--> Save object");
		tempSession.save(tempProduct);
		commitSession(tempSession);
		System.out.println("--> Create object done.");
	}

	public Product getProductData() {

		String productName;
		String productDescription;
		String tmpPrize;
		BigDecimal prize;
		System.out.println("Podaj nazwe produktu.");
		productName = skaner.nextLine();
		System.out.println("Podaj opis.");
		productDescription = skaner.nextLine();
		System.out.println("Podaj cene w formacie [___.__]");
		tmpPrize = skaner.nextLine();
		prize = new BigDecimal(tmpPrize);
		tempProduct = new Product(productName, productDescription, prize);
		return tempProduct;
	}

	public void queryProduct() {
		queryProductInDatabase(getQueryData());
	}

	public String getQueryData() {
		String query;
		System.out.println("Podaj nazwe produktu.");
		query = skaner.nextLine();
		return query;

	}

	public void queryProductInDatabase(String query) {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		System.out.println("--> Call query with attribute: " + query);
		List<Product> productList = tempSession.createQuery(
				"from Product where product_name='" + query + "'")
				.getResultList();
		displayProducts(productList);
		commitSession(tempSession);
		System.out.println("--> Query done.");
	}

	public void displayProductList() {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		System.out.println("--> Display all products");
		List<Product> productList = tempSession.createQuery("from Product")
				.getResultList();
		displayProducts(productList);
		commitSession(tempSession);
		System.out.println("--> Query done.");
	}

	private static void displayProducts(List<Product> products) {
		for (Product tempProduct : products)
			System.out.println(tempProduct);
	}

	public void updateProduct() {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		chooseAttributeToChange(getProductById(tempSession));
		System.out.println("--> Updating customer data");
		commitSession(tempSession);
		System.out.println("--> Update done.");

	}

	public Product getProductById(Session tempSession)
			throws NumberFormatException {
		System.out.println("Podaj id produktu.");
		int id = Integer.valueOf(skaner.nextLine());
		Product tempProduct = tempSession.get(Product.class, id);
		return tempProduct;
	}

	public void deleteProduct() {
		tempSession = createSession();
		System.out.println("--> Begin transaction");
		tempSession.beginTransaction();
		System.out.println("--> Deleting product data");
		tempSession.delete(getProductById(tempSession));
		System.out.println("--> Delete done.");
		commitSession(tempSession);

	}

	private void chooseAttributeToChange(Product tempProduct) {
		System.out.println("Co chcesz zmieniæ: 1. nazwe; 2. opis; 3. cene");
		int choice = Integer.valueOf(skaner.nextLine());
		String temp;
		switch (choice) {
		case 1: {
			System.out.println("Jaka jest nowa nazwa?");
			temp = skaner.nextLine();
			tempProduct.setProductName(temp);
			break;
		}
		case 2: {
			System.out.println("Jaki jest nowy opis?");
			temp = skaner.nextLine();
			tempProduct.setProductDescription(temp);
			break;
		}
		case 3: {
			System.out.println("Jaka jest nowa cena? [___.__]");
			temp = skaner.nextLine();
			BigDecimal cena = new BigDecimal(temp);
			tempProduct.setPrize(cena);
			break;
		}
		default:
			System.out.println("Zly wybor!");
			break;
		}
	}

}
