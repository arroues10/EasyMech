package com.solal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name = "work_card_id")
	private long workCardId;

	public Mechanic() {

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

	public long getWorkCardId() {
		return workCardId;
	}

	public void setWorkCardId(long workCardId) {
		this.workCardId = workCardId;
	}

	@Override
	public String toString() {
		return "Mechanic [id=" + id + ", name=" + name + ", password=" + password + ", workCardId=" + workCardId + "]";
	}
}
