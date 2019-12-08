package com.solal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "workCard")
public class WorkCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "plateNumber")
	private String plateNumber;
	@OneToMany
	@JoinTable(name = "work_card_part", joinColumns = @JoinColumn(name = "work_card_id"), inverseJoinColumns = @JoinColumn(name = "part_id"))
	private List<Part> parts;

	public WorkCard() {
		parts = new ArrayList<>();
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Override
	public String toString() {
		return "WorkCard [plateNumber=" + plateNumber + "]";
	}

}
