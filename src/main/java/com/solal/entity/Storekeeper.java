package com.solal.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "storekeeper")
public class Storekeeper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@OneToMany
	private List<WorkCard> workCards;

	public Storekeeper() {
		workCards = new ArrayList<>();
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

	public List<WorkCard> getWorkCards() {
		return workCards;
	}

	public void setWorkCards(List<WorkCard> workCards) {
		this.workCards = workCards;
	}

	@Override
	public String toString() {
		return "Storekeeper [id=" + id + ", name=" + name + ", password=" + password + ", workCards=" + workCards + "]";
	}
}
