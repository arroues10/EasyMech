package com.solal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "work_card")
public class WorkCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "plate_number")
	private String plateNumber;
	@Column(name = "garage_code")
	private String garageCode;

	@OneToOne(mappedBy = "workCard", cascade = CascadeType.ALL)
	private Mechanic mechanic;

	@OneToMany(mappedBy = "workCard", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part> parts;

	public WorkCard() {
		parts = new ArrayList<>();
	}

	public WorkCard(long id, String plateNumber, String garageCode, Mechanic mechanic, List<Part> parts) {
		this();
		this.id = id;
		this.plateNumber = plateNumber;
		this.garageCode = garageCode;
		this.mechanic = mechanic;
		this.parts = parts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getGarageCode() {
		return garageCode;
	}

	public void setGarageCode(String garageCode) {
		this.garageCode = garageCode;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	@Override
	public String toString() {
		return "WorkCard [id=" + id + ", plateNumber=" + plateNumber + ", garageCode=" + garageCode + ", mechanic="
				+ mechanic + ", parts=" + parts + "]";
	}

}
