package com.it332.wildcatonetap.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbluser")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@Column(name = "fullname")
	private String fullName;

	@Column(name = "idnumber")
	private String idNumber;

	public UserEntity() {
		super();
	}
	
	public UserEntity(int userId, String username, String email, String password, String fullName, String idNumber) {
    super();
    this.userId = userId;
    this.username = username;
    this.email = email;
    this.password = password;
    this.fullName = fullName;
    this.idNumber = idNumber;
}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}