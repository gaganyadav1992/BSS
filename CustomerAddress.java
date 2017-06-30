package com.bss.databaseBean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.bss.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="address")
public class CustomerAddress implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@JsonView(Views.Public.class)
	@Column(name = "address_id", nullable = false)
	private long address_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "area")
	private String area;
	
	@JsonView(Views.Public.class)
	@Column(name = "email")
	private String email;
	
	@JsonView(Views.Public.class)
	@Column(name = "pobox")
	private String pobox;
	
	@JsonView(Views.Public.class)
	@Column(name = "building")
	private String building;

	@JsonView(Views.Public.class)
	@Column(name = "house_number")
	private String house_number;
	
	@JsonView(Views.Public.class)
	@Column(name = "floor_number")
	private String floor_number;
	
	@JsonView(Views.Public.class)
	@Column(name = "apartment")
	private String apartment;
	
	@JsonView(Views.Public.class)
	@Column(name = "street_id")
	private String street_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "city_id")
	private String city_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "district_id")
	private String district_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "zipcode")
	private String zipcode;
	
	@JsonView(Views.Public.class)
	@Column(name = "state")
	private String state;
	
	@JsonView(Views.Public.class)
	@Column(name = "country")
	private String country;
	
	
	@JsonView(Views.Public.class)
	@Column(name = "created_date")
	private String created_date;
	
	@JsonView(Views.Public.class)
	@Column(name = "modified_date")
	private String modified_date;
	
	@JsonView(Views.Public.class)
	@Column(name = "user")
	private String user;
		
	@OneToOne
    @JoinColumn(name = "customer_id")
	private CustomerDetails customer;
	
	
	
	public CustomerAddress(long address_id, /*long customer_id,*/ String area, String email, String pobox, String building,
			String house_number, String floor_number, String apartment, String street_id, String city_id,
			String district_id, String zipcode, String state, String country,  String created_date,
			String modified_date, String user, CustomerDetails customer) {
		super();
		this.address_id = address_id;
		this.area = area;
		this.email = email;
		this.pobox = pobox;
		this.building = building;
		this.house_number = house_number;
		this.floor_number = floor_number;
		this.apartment = apartment;
		this.street_id = street_id;
		this.city_id = city_id;
		this.district_id = district_id;
		this.zipcode = zipcode;
		this.state = state;
		this.country = country;
	
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.user = user;
		this.customer = customer;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPobox() {
		return pobox;
	}


	public void setPobox(String pobox) {
		this.pobox = pobox;
	}


	public CustomerAddress() {
		super();
		
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	/*public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}*/

	public long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}
	public String getHouse_number() {
		return house_number;
	}
	public void setHouse_number(String house_number) {
		this.house_number = house_number;
	}
	public String getFloor_number() {
		return floor_number;
	}
	public void setFloor_number(String floor_number) {
		this.floor_number = floor_number;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getStreet_id() {
		return street_id;
	}
	public void setStreet_id(String sreet_id) {
		this.street_id = sreet_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getDistrict_id() {
		return district_id;
	}
	public void setDistrict_id(String district_id) {
		this.district_id = district_id;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	
	public CustomerDetails getCustomer() {
		return customer;
	}


	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}


	

}
