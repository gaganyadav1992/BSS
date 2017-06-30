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
@Table(name="role")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int role_id;

	@NotEmpty
	@Column(name="role_name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@ManyToMany
	@JoinTable(name="role_action_mapping",joinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},
		    inverseJoinColumns={@JoinColumn(name="action_id",referencedColumnName="action_id")})
	List<Action> actionList = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="category_role_mapping",joinColumns={@JoinColumn(name="role_id")},
		    inverseJoinColumns={@JoinColumn(name="category_id")})
	List<Category> categoryList = new ArrayList<>();
	
	@OneToMany(mappedBy="role" , cascade = CascadeType.ALL)
	private List<UserActionMapping> userList = new ArrayList<>();

	public List<Action> getActionList() {
		return actionList;
	}

	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<UserActionMapping> getUserList() {
		return userList;
	}

	public void setUserList(List<UserActionMapping> userList) {
		this.userList = userList;
	}
	
	@Override
    public String toString() {
        return "Role [id=" + role_id + ", name=" + name + "]";
    }

}
