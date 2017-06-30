package com.bss.databaseBean;

import java.io.Serializable;
import java.util.List;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

import com.bss.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="base_plan")
public class BasePlan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@JsonView(Views.Public.class)
	@Column(name = "plan_id", nullable = false)
	private long plan_id;
	
	@Column(name = "plan_oid")
	private String plan_oid;
	
	@Column(name = "plan_name")
	private String plan_name;
	
	@Column(name = "plan_type")
	private String plan_type;
	
	@Column(name = "plan_description")
	private String plan_description;

	@JsonView(Views.Public.class)
	@Column(name = "activation_charge")
	private String activation_charge;
	
	@JsonView(Views.Public.class)
	@Column(name = "change_number_charge")
	private String change_number_change;
	
	@JsonView(Views.Public.class)
	@Column(name = "switch_phone_number_charge")
	private String switch_phone_number_charge;
	
	@JsonView(Views.Public.class)
	@Column(name = "on_net_minute_charge")
	private String on_net_minute_charge;
	
	@JsonView(Views.Public.class)
	@Column(name = "off_net_minute_charge")
	private String off_net_minute_charge;
	
	@JsonView(Views.Public.class)
	@Column(name = "call_details_request_charge")
	private String call_details_request_charge;
	
	@JsonView(Views.Public.class)
	@Column(name = "reactivation_charge")
	private String reactivation_charge;
	
	@JsonView(Views.Public.class)
	@Column(name = "deposit")
	private String deposit;
	
	@JsonView(Views.Public.class)
	@Column(name = "monthly_charge")
	private String monthly_charge;
	
	@JsonView(Views.Public.class)
	@Column(name = "local_call_rate")
	private String local_call_rate;
	
	@JsonView(Views.Public.class)
	@Column(name = "local_call_rate_frequency")
	private String local_call_rate_frequency;
	
	@JsonView(Views.Public.class)
	@Column(name = "sms_rate")
	private String sms_rate;
	
	@JsonView(Views.Public.class)
	@Column(name = "data_rate")
	private String data_rate;
	
	@JsonView(Views.Public.class)
	@Column(name = "data_rate_frequency")
	private String data_rate_frequency;
	
	@JsonView(Views.Public.class)
	@Column(name = "int_call_rate")
	private String int_call_rate;
	
	@JsonView(Views.Public.class)
	@Column(name = "int_call_frequency")
	private String int_call_frequency;
	
	@JsonView(Views.Public.class)
	@Column(name = "created_date")
	private String created_date;
	
	@JsonView(Views.Public.class)
	@Column(name = "modified_date")
	private String modified_date;
	
	@JsonView(Views.Public.class)
	@Column(name = "user")
	private String user;
	
	/*@ManyToOne
    @JoinColumn(name = "plan_type_id")
	private Plan_Types plan_Types;*/
	
	/* @OneToMany(fetch = FetchType.EAGER,mappedBy = "plan_id", cascade = CascadeType.ALL)
	 @Fetch(value = FetchMode.SELECT)
	 private List<Customer_Group_Mapping> customer_Group_Mapping;*/

	
	 @ManyToMany(fetch = FetchType.EAGER,mappedBy = "basePlan")
	 private List<Line> line;
	
	public BasePlan() {
		super();
	}
	
	
	
	



	public String getPlan_oid() {
		return plan_oid;
	}







	public String getPlan_name() {
		return plan_name;
	}







	public String getPlan_type() {
		return plan_type;
	}







	public String getPlan_description() {
		return plan_description;
	}







	public void setPlan_oid(String plan_oid) {
		this.plan_oid = plan_oid;
	}







	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}







	public void setPlan_type(String plan_type) {
		this.plan_type = plan_type;
	}







	public void setPlan_description(String plan_description) {
		this.plan_description = plan_description;
	}







	public long getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(long plan_id) {
		this.plan_id = plan_id;
	}

	public String getActivation_charge() {
		return activation_charge;
	}
	public void setActivation_charge(String activation_charge) {
		this.activation_charge = activation_charge;
	}
	public String getChange_number_change() {
		return change_number_change;
	}
	public void setChange_number_change(String change_number_change) {
		this.change_number_change = change_number_change;
	}
	public String getSwitch_phone_number_charge() {
		return switch_phone_number_charge;
	}
	public void setSwitch_phone_number_charge(String switch_phone_number_charge) {
		this.switch_phone_number_charge = switch_phone_number_charge;
	}
	public String getOn_net_minute_charge() {
		return on_net_minute_charge;
	}
	public void setOn_net_minute_charge(String on_net_minute_charge) {
		this.on_net_minute_charge = on_net_minute_charge;
	}
	public String getOff_net_minute_charge() {
		return off_net_minute_charge;
	}
	public void setOff_net_minute_charge(String off_net_minute_charge) {
		this.off_net_minute_charge = off_net_minute_charge;
	}
	public String getCall_details_request_charge() {
		return call_details_request_charge;
	}
	public void setCall_details_request_charge(String call_details_request_charge) {
		this.call_details_request_charge = call_details_request_charge;
	}
	public String getReactivation_charge() {
		return reactivation_charge;
	}
	public void setReactivation_charge(String reactivation_charge) {
		this.reactivation_charge = reactivation_charge;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getMonthly_charge() {
		return monthly_charge;
	}
	public void setMonthly_charge(String monthly_charge) {
		this.monthly_charge = monthly_charge;
	}
	public String getLocal_call_rate() {
		return local_call_rate;
	}
	public void setLocal_call_rate(String local_call_rate) {
		this.local_call_rate = local_call_rate;
	}
	public String getLocal_call_rate_frequency() {
		return local_call_rate_frequency;
	}
	public void setLocal_call_rate_frequency(String local_call_rate_frequency) {
		this.local_call_rate_frequency = local_call_rate_frequency;
	}
	public String getSms_rate() {
		return sms_rate;
	}
	public void setSms_rate(String sms_rate) {
		this.sms_rate = sms_rate;
	}
	public String getData_rate() {
		return data_rate;
	}
	public void setData_rate(String data_rate) {
		this.data_rate = data_rate;
	}
	public String getData_rate_frequency() {
		return data_rate_frequency;
	}
	public void setData_rate_frequency(String data_rate_frequency) {
		this.data_rate_frequency = data_rate_frequency;
	}
	public String getInt_call_rate() {
		return int_call_rate;
	}
	public void setInt_call_rate(String int_call_rate) {
		this.int_call_rate = int_call_rate;
	}
	public String getInt_call_frequency() {
		return int_call_frequency;
	}
	public void setInt_call_frequency(String int_call_frequency) {
		this.int_call_frequency = int_call_frequency;
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



/*	public Plan_Types getPlan_Types() {
		return plan_Types;
	}



	public void setPlan_Types(Plan_Types plan_Types) {
		this.plan_Types = plan_Types;
	}


*/


	public List<Line> getLine() {
		return line;
	}



	public void setLine(List<Line> line) {
		this.line = line;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
