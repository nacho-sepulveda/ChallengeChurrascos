package com.ProductAPI.app.EntityObjects;

import java.util.*;

import javax.persistence.*;



@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private Integer id;
	@Column(name = "created", nullable = false)
	private Date created;
	@Column(name = "email")
	private String email;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "password", nullable = false, length = 20)
	private String password;
	@Column(name = "updated")
	private Date updated;
	@Column(name = "username", unique = true, nullable = false)
	private String username;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = new Date();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public User() {
		super();
	}
	
	
	public User(Integer id, Date created, String email, String first_name, String last_name, String password,
			Date updated, String username, Set<Role> roles) {
		super();
		this.id = id;
		this.created = created;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.updated = updated;
		this.username = username;
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", created=" + created + ", email=" + email + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", password=" + password + ", updated=" + updated + ", username="
				+ username + ", roles=" + roles + "]";
	}
	
	
	
}
