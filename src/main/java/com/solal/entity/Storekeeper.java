package com.solal.entity;

import java.util.ArrayList;
import java.util.List;

public class Storekeeper {
	private long id;
	private String name;
	private String password;
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
