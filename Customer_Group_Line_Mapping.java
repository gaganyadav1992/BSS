package com.bss.databaseBean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer_group_line_mapping")
public class Customer_Group_Line_Mapping implements Serializable{

	private static final long serialVersionUID = 1204079441804863990L;
	
	@Id @GeneratedValue
	@Column(name = "cust_group_line_auto_id", nullable = false)
	private long  cust_group_line_auto_id;
	
	@ManyToOne
    @JoinColumn(name = "group_id")
	private Customer_Group_Master  group_id ;
	
	@ManyToOne
    @JoinColumn(name = "line_id")
	private Line  line_id ;
	
	@Column(name = "creation_date")
	private String  creation_date;
	
	@Column(name = "modified_date")
	private String  modified_date ;
	
	@Column(name = "user", nullable = false)
	private String  user ;

	public long getCust_group_line_auto_id() {
		return cust_group_line_auto_id;
	}

	public Customer_Group_Master getGroup_id() {
		return group_id;
	}

	public Line getLine_id() {
		return line_id;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public String getModified_date() {
		return modified_date;
	}

	public String getUser() {
		return user;
	}

	public void setCust_group_line_auto_id(long cust_group_line_auto_id) {
		this.cust_group_line_auto_id = cust_group_line_auto_id;
	}

	public void setGroup_id(Customer_Group_Master group_id) {
		this.group_id = group_id;
	}

	public void setLine_id(Line line_id) {
		this.line_id = line_id;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
	

}
