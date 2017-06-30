package com.bss.databaseBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_items")
public class CSR_Order_Item implements Serializable{


	private static final long serialVersionUID = -7039178330027300401L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_item_id", nullable = false)
	private long order_item_id;
	
	@Column(name = "order_id")
	private long order_id;
	
	@Column(name = "line_id")
	private long line_id;
	
	@Column(name = "resource_id")
	private long resource_id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "effective_date")
	private String effective_date;
	
	@Column(name = "expipration_date")
	private String expipration_date;
	
	@Column(name = "fullfill_date")
	private String fullfill_date;
	
	@Column(name = "created_date")
	private String created_date;
	
	@Column(name = "modified_date")
	private String modified_date;
	
	@Column(name = "user")
	private String user;

	public CSR_Order_Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CSR_Order_Item(long order_item_id, long order_id, long line_id, long resource_id, String description,
			String effective_date, String expipration_date, String fullfill_date, String created_date,
			String modified_date, String user) {
		super();
		this.order_item_id = order_item_id;
		this.order_id = order_id;
		this.line_id = line_id;
		this.resource_id = resource_id;
		this.description = description;
		this.effective_date = effective_date;
		this.expipration_date = expipration_date;
		this.fullfill_date = fullfill_date;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.user = user;
	}

	public long getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(long order_item_id) {
		this.order_item_id = order_item_id;
	}

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public long getLine_id() {
		return line_id;
	}

	public void setLine_id(long line_id) {
		this.line_id = line_id;
	}

	public long getResource_id() {
		return resource_id;
	}

	public void setResource_id(long resource_id) {
		this.resource_id = resource_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(String effective_date) {
		this.effective_date = effective_date;
	}

	public String getExpipration_date() {
		return expipration_date;
	}

	public void setExpipration_date(String expipration_date) {
		this.expipration_date = expipration_date;
	}

	public String getFullfill_date() {
		return fullfill_date;
	}

	public void setFullfill_date(String fullfill_date) {
		this.fullfill_date = fullfill_date;
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
	
	
	
}
