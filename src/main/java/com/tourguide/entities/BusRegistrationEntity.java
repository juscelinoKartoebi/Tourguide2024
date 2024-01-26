package com.tourguide.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BUS_REGISTRATION")
public class BusRegistrationEntity {

	private Long id;
	private BusEntity bus;
	private String model;
	private String type;
	private Long build;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BUS_REGISTRATION_ID", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name = "BUS_ID")
	public BusEntity getBus() {
		return bus;
	}

	public void setBus(BusEntity bus) {
		this.bus = bus;
	}

	@Column(name = "MODEL")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "BUILD")
	public Long getBuild() {
		return build;
	}

	public void setBuild(Long build) {
		this.build = build;
	}

}
