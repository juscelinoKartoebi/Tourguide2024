package com.tourguide.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "ROUTE")
public class RouteEntity {

	private Long id;
	private DestinationEntity from;
	private DestinationEntity to;
	private Long distance;

//	private List<TourEntity> tours = new ArrayList<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROUTE_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "DESTINATION_FROM")
//	@MapsId
	public DestinationEntity getFrom() {
		return from;
	}

	public void setFrom(DestinationEntity from) {
		this.from = from;
	}

	@ManyToOne
	@JoinColumn(name = "DESTINATION_TO")
//	@MapsId
	public DestinationEntity getTo() {
		return to;
	}

	public void setTo(DestinationEntity to) {
		this.to = to;
	}

	@Column(name = "DISTANCE")
	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "TOUR_ID")
//	public List<TourEntity> getTours() {
//		return tours;
//	}
//
//	public void setTours(List<TourEntity> tours) {
//		this.tours = tours;
//	}

	@Override
	public String toString() {
		return from.getDestination() + " - " + to.getDestination();
	}

}
