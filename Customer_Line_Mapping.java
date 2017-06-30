package com.bss.databaseBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import javax.persistence.Table;

import com.bss.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="customer_line_mapping")
public class Customer_Line_Mapping implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@JsonView(Views.Public.class)
	@Column(name = "customer_line_mapping_id", nullable = false)
	private long customer_line_mapping_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "customer_id", nullable = false)
	private long customer_id;
	
	@JsonView(Views.Public.class)
	@Column(name = "line_id", nullable = false)
	private long line_id;
	


	 

	public Customer_Line_Mapping() {
		super();
	}

	public Customer_Line_Mapping(long customer_line_mapping_id, long customer_id, long line_id) {
		super();
		this.customer_line_mapping_id = customer_line_mapping_id;
		this.customer_id = customer_id;
		this.line_id = line_id;
	
	}

	public long getCustomer_line_mapping_id() {
		return customer_line_mapping_id;
	}

	public void setCustomer_line_mapping_id(long customer_line_mapping_id) {
		this.customer_line_mapping_id = customer_line_mapping_id;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public long getLine_id() {
		return line_id;
	}

	public void setLine_id(long line_id) {
		this.line_id = line_id;
	}

	


	
	
}
