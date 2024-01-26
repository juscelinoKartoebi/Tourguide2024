package com.tourguide.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "V_BUS_DISTANCE")
public class BusDistanceEntity implements Serializable {

	private BusEntity bus;
	private Long totalDistance;

	@Id
	@OneToOne
	@JoinColumn(name = "BUS_ID")
	public BusEntity getBus() {
		return bus;
	}

	public void setBus(BusEntity bus) {
		this.bus = bus;
	}

	@Column(name = "TOTAL_DISTANCE")
	public Long getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Long totalDistance) {
		this.totalDistance = totalDistance;
	}

}
