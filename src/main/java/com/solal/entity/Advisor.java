package com.solal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "advisor")
public class Advisor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "garage_code")
	private String garageCode;

	public Advisor() {
	}

	public Advisor(long id, String name, String password, String garageCode) {
		this();
		this.id = id;
		this.name = name;
		this.password = password;
		this.garageCode = garageCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGarageCode() {
		return garageCode;
	}

	public void setGarageNumber(String garageCode) {
		this.garageCode = garageCode;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", password=" + password + ", garageNumber=" + garageCode + "]";
	}

}
