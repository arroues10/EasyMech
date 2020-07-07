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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "work_card")
public class WorkCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "plate_number")
	private String plateNumber;
	@Column(name = "start_work")
	private long startWork;
	@Column(name = "end_work")
	private long endWork;
	@Column(name = "garage_code")
	private String garageCode;
	@Column(name = "description")
	private String description;

	@OneToOne(mappedBy = "workCard", cascade = CascadeType.ALL)
	private Mechanic mechanic;

	@OneToMany(mappedBy = "workCard", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Part> parts;

	public WorkCard() {
		parts = new ArrayList<>();
	}

	public WorkCard(long id, String plateNumber, String garageCode, long startWork, long endWork, String description) {
		this();
		this.id = id;
		this.plateNumber = plateNumber;
		this.garageCode = garageCode;
		this.startWork = startWork;
		this.endWork = endWork;
		this.description = description;
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

	public long getStartWork() {
		return startWork;
	}

	public void setStartWork(long startWork) {
		this.startWork = startWork;
	}

	public long getEndWork() {
		return endWork;
	}

	public void setEndWork(long endWork) {
		this.endWork = endWork;
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

	@JsonIgnore
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	@JsonIgnore
	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "WorkCard [id=" + id + ", plateNumber=" + plateNumber + ", startWork=" + startWork + ", endWork="
				+ endWork + ", garageCode=" + garageCode + ", description=" + description + ", mechanic=" + mechanic
				+ ", endWorkMechanic=" + ", parts=" + parts + "]";
	}
}