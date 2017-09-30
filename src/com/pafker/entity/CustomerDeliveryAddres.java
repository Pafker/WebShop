package com.pafker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_delivery_addres")
public class CustomerDeliveryAddres {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="country")
	private String country;
	
	@Column(name="city")
	private String city;
	
	@Column(name="postal_code")
	private String postalCode;
	
	@Column(name="house_number")
	private String houseNumber;
	
	public CustomerDeliveryAddres() {}

	public CustomerDeliveryAddres(String country, String city,
			String postalCode, String houseNumber) {
		this.country = country;
		this.city = city;
		this.postalCode = postalCode;
		this.houseNumber = houseNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	@Override
	public String toString() {
		return "CustomerDeliveryAddres [id=" + id + ", country=" + country
				+ ", city=" + city + ", postalCode=" + postalCode
				+ ", houseNumber=" + houseNumber + "]";
	}
	
	
	
	
	
}
