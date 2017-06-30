package com.bss.databaseBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="order")
public class CSR_Order implements Serializable{
	

	private static final long serialVersionUID = -4277043126771728704L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_id", nullable = false)
	private long order_id;
	
	@Column(name = "order_type")
	private String order_type;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "customer_id")
	private long customer_id;
	
	@Column(name = "order_status")
	private String order_status;
	
	@Column(name = "order_fulfill_date")
	private String order_fulfill_date;
	
	@Column(name = "order_expiration_date")
	private String order_expiration_date;
	
	@Column(name = "created_date")
	private String created_date;
	
	@Column(name = "modified_date")
	private String modified_date;
	
	@Column(name = "user")
	private String user;

	public CSR_Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CSR_Order(long order_id, String order_type, String description, long customer_id, String order_status,
			String order_fulfill_date, String order_expiration_date, String created_date, String modified_date,
			String user) {
		super();
		this.order_id = order_id;
		this.order_type = order_type;
		this.description = description;
		this.customer_id = customer_id;
		this.order_status = order_status;
		this.order_fulfill_date = order_fulfill_date;
		this.order_expiration_date = order_expiration_date;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.user = user;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getOrder_type() {
		return order_type;
	}

	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getOrder_fulfill_date() {
		return order_fulfill_date;
	}

	public void setOrder_fulfill_date(String order_fulfill_date) {
		this.order_fulfill_date = order_fulfill_date;
	}

	public String getOrder_expiration_date() {
		return order_expiration_date;
	}

	public void setOrder_expiration_date(String order_expiration_date) {
		this.order_expiration_date = order_expiration_date;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
