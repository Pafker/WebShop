package com.pafker.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "registry_date")
	private String date;
	
	// set up a relation between instructor and instructor detail
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_delivery_addres")
	private CustomerDeliveryAddres customerDeliveryAddres;

	public Customer(){};
	
	public Customer(String firstName, String lastName, String email,
			String registryDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = registryDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public CustomerDeliveryAddres getCustomerDeliveryAddres() {
		return customerDeliveryAddres;
	}

	public void setCustomerDeliveryAddres(
			CustomerDeliveryAddres customerDeliveryAddres) {
		this.customerDeliveryAddres = customerDeliveryAddres;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", First Name=" + firstName
				+ ", Last Name=" + lastName + ", Email=" + email + ", Registry Date="
				+ date + ", Delivery Addres=" + customerDeliveryAddres
				+ "]";
	}
	
	
	
	
}
