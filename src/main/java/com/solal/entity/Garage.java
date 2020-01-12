package com.solal.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Garage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "garage_code")
	private String garageCode;

	public Garage() {
	}

	public Garage(long id, String name, String garageCode) {
		this();
		this.id = id;
		this.name = name;
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

	public String getGarageCode() {
		return garageCode;
	}

	public void setGarageCode(String garageCode) {
		this.garageCode = garageCode;
	}

	@Override
	public String toString() {
		return "Garage [id=" + id + ", name=" + name + ", garageCode=" + garageCode + "]";
	}
}
