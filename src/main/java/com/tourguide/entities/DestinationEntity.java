package com.tourguide.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DESTINATION")
public class DestinationEntity {

	private Long id;
	private String destination;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DESTINATION_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DESTINATION")
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		return destination;
	}
	
	

}
