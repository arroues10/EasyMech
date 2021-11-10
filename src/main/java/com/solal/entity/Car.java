package com.solal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "clientName")
	private String clientName;
	@Column(name = "carNumber")
	private String carNumber;

	public Car() {
	}

	public Car(long id, String clientName, String carNumber) {
		this();
		this.id = id;
		this.clientName = clientName;
		this.carNumber = carNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", clientName=" + clientName + ", carNumber=" + carNumber + "]";
	}

}
