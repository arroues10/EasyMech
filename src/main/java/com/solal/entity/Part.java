package com.solal.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "part")
public class Part {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "originalPrice")
	private long originalPrice;
	@Column(name = "replacementPrice")
	private long replacementPrice;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "work_card_id")
	private WorkCard workCard;

	public Part() {

	}

	public Part(String name) {
		this();
		this.name = name;
		this.originalPrice = 0;
		this.replacementPrice = 0;
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

	public long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public long getReplacementPrice() {
		return replacementPrice;
	}

	public void setReplacementPrice(long replacementPrice) {
		this.replacementPrice = replacementPrice;
	}

	public WorkCard getWorkCard() {
		return workCard;
	}

	@JsonIgnore
	public void setWorkCard(WorkCard workCard) {
		this.workCard = workCard;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", originalPrice=" + originalPrice + ", replacementPrice="
				+ replacementPrice + ", workCard=" + workCard + "]";
	}

}
