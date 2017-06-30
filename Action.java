package com.bss.databaseBean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "action")
public class Action {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int action_id;

	@NotEmpty
	@Column(name = "action_name")
	private String name;
	
	@Column(name = "alias")
	private String alias;

	@Column(name = "description")
	private String description;

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Role.class)
	@JoinTable(name = "role_action_mapping", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	List<Role> roleList = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "category_action_mapping", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	List<Category> categoryList = new ArrayList<>();

	@OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
	private List<UserActionMapping> userList = new ArrayList<>();

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAction_id() {
		return action_id;
	}

	public void setAction_id(int action_id) {
		this.action_id = action_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserActionMapping> getUserList() {
		return userList;
	}

	public void setUserList(List<UserActionMapping> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "Action [id=" + action_id + ", name=" + name + "]";
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
