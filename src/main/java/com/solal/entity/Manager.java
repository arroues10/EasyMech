package com.solal.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@OneToMany
	private List<Mechanic> mechanics;
	
	public Manager() {
		
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

	public List<Mechanic> getMechanics() {
		return mechanics;
	}

	public void setMechanics(List<Mechanic> mechanics) {
		this.mechanics = mechanics;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", password=" + password + ", mechanics=" + mechanics + "]";
	}

}
