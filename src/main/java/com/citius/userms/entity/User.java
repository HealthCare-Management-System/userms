package com.citius.userms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "user_data")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String name;
	private String lname;
	private String email;
	private String dob;
	private String doj;
	private String phone;

	private String password;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<Role> roles;
	private String status;
	private String empid;

	@OneToMany(mappedBy = "userId")
	private Set<Password> passwordSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Password> getPasswordSet() {
		return passwordSet;
	}

	public void setPasswordSet(Set<Password> passwordSet) {
		this.passwordSet = passwordSet;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", title=" + title + ", name=" + name + ", lname=" + lname + ", email=" + email
				+ ", dob=" + dob + ", doj=" + doj + ", phone=" + phone + ", password=" + password + ", roles=" + roles
				+ ", status=" + status + ", empid=" + empid + ", passwordSet=" + passwordSet + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String title, String name) {
		super();
		this.id = id;
		this.title = title;
		this.name = name;

	}

	public User(int id, String title, String name, String lname, String email, String dob, String doj, String phone,
			String password, Set<Role> roles, String status, String empid, Set<Password> passwordSet) {
		super();
		this.id = id;
		this.title = title;
		this.name = name;
		this.lname = lname;
		this.email = email;
		this.dob = dob;
		this.doj = doj;
		this.phone = phone;
		this.password = password;
		this.roles = roles;
		this.status = status;
		this.empid = empid;
		this.passwordSet = passwordSet;
	}

}
