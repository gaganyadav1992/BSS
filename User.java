package com.bss.databaseBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int user_id;

	@Column(name = "pos_id")
	long pos_id;

	@Column(name = "warehouse_Id")
	long warehouse_Id;

	@Column(name = "username", unique = true, nullable = false)
	String username;

	@Column(name = "password", nullable = false)
	String password;

	@NotEmpty
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@NotEmpty
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@NotEmpty
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "STATE", nullable = false)
	private String state = State.ACTIVE.getState();

	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_DEPT", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "DEPT_ID") })
	private Set<Dept> userDept = new HashSet<Dept>();

	@OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
	private List<UserActionMapping> actionList = new ArrayList<>();

	public long getPos_id() {
		return pos_id;
	}

	public void setPos_id(long pos_id) {
		this.pos_id = pos_id;
	}

	public long getWarehouse_Id() {
		return warehouse_Id;
	}

	public void setWarehouse_Id(long warehouse_Id) {
		this.warehouse_Id = warehouse_Id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Dept> getUserDept() {
		return userDept;
	}

	public void setUserDept(Set<Dept> userDept) {
		this.userDept = userDept;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<UserActionMapping> getActionList() {
		return actionList;
	}

	public void setActionList(List<UserActionMapping> actionList) {
		this.actionList = actionList;
	}
    
}
