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
	private String registryDate;

	// set up a relation between instructor and instructor detail
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_delivery_address_id")
	private CustomerDeliveryAddress customerDeliveryAddress;

	public Customer() {
	};

	public Customer(String firstName, String lastName, String email,
			String registryDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.registryDate = registryDate;
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

	public String getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(String registryDate) {
		this.registryDate = registryDate;
	}

	public CustomerDeliveryAddress getCustomerDeliveryAddress() {
		return customerDeliveryAddress;
	}

	public void setCustomerDeliveryAddres(
			CustomerDeliveryAddress customerDeliveryAddress) {
		this.customerDeliveryAddress = customerDeliveryAddress;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", First Name=" + firstName
				+ ", Last Name=" + lastName + ", Email=" + email
				+ ", Registry Date=" + registryDate + ", Delivery Address="
				+ customerDeliveryAddress + "]";
	}

}
