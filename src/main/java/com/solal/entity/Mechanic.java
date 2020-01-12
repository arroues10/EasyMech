package com.solal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "mechanic")
public class Mechanic {

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

	@JsonProperty("work_card_plate_number")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "plateNumber")
	@JsonIdentityReference(alwaysAsId = true)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "work_card_id")
	private WorkCard workCard;

	public Mechanic() {
	}

	public Mechanic(long id, String name, String password, String garageCode) {
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

	public void setGarageCode(String garageCode) {
		this.garageCode = garageCode;
	}

	@JsonIgnore
	public WorkCard getWorkCard() {
		return workCard;
	}

	public void setWorkCard(WorkCard workCard) {
		this.workCard = workCard;
	}

	@Override
	public String toString() {
		return "Mechanic [id=" + id + ", name=" + name + ", password=" + password + ", garageCode=" + garageCode
				+ ", workCard=" + workCard + "]";
	}

}
