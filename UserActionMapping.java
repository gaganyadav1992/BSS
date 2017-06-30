package com.bss.databaseBean;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_action_mapping" )
@AssociationOverrides({@AssociationOverride(name="user",joinColumns=@JoinColumn(name="user_id")),
					   @AssociationOverride(name="category",joinColumns=@JoinColumn(name="category_id")),
					   @AssociationOverride(name="role",joinColumns=@JoinColumn(name="role_id")),
					   @AssociationOverride(name="action",joinColumns=@JoinColumn(name="action_id"))})
public class UserActionMapping{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int mapping_id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Role role;
	
	@ManyToOne
	private Action action;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	public int getMapping_id() {
		return mapping_id;
	}

	public void setMapping_id(int mapping_id) {
		this.mapping_id = mapping_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
