package com.microsoft.tutorial.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String email;
	private String password;

	@Column(name = "Created_At", updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	@Column(name = "Updated_At")
	private Timestamp updatedAt;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
	           joinColumns = @JoinColumn(name="user_id"),
	           inverseJoinColumns = @JoinColumn(name="role_id")
			)
	private Set<Role> roles=new HashSet<>();

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
}
