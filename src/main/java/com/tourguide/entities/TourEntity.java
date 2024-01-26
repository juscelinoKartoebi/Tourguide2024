package com.tourguide.entities;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "TOUR")
public class TourEntity {

	private Long id;
	private String tourName;
	private Date tourDate;
	private RouteEntity route;
	private BusEntity bus;
	private DriverEntity driver;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TOUR_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TOUR_NAME")
	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	@Column(name = "TOUR_DATE")
	public Date getTourDate() {
		return tourDate;
	}

	public void setTourDate(Date tourDate) {
		this.tourDate = tourDate;
	}

	@ManyToOne
	@JoinColumn(name = "ROUTE_ID")
//	@MapsId
	public RouteEntity getRoute() {
		return route;
	}

	public void setRoute(RouteEntity route) {
		this.route = route;
	}

	@ManyToOne
	@JoinColumn(name = "BUS_ID")
//	@MapsId
	public BusEntity getBus() {
		return bus;
	}

	public void setBus(BusEntity bus) {
		this.bus = bus;
	}

	@ManyToOne
	@JoinColumn(name = "DRIVER_ID")
//	@MapsId
	public DriverEntity getDriver() {
		return driver;
	}

	public void setDriver(DriverEntity driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return tourName;
	}

}
