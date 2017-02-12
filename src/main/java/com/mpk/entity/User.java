package com.mpk.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userAccount")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String mail;
	private String phone;
	private Boolean active;
	private String role;
	@OneToOne(optional = true)
	private Driver driver;
}
