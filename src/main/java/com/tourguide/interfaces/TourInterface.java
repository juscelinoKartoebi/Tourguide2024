package com.tourguide.interfaces;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.tourguide.entities.TourEntity;

public interface TourInterface {

	public List<TourEntity> getTourByDate(Date date);

	public List<TourEntity> getTourByDriver(Long driverId);

	public List<TourEntity> getTourByName(String tourName);

	public List<TourEntity> getTourByDriver(String driverName);

	public List<TourEntity> getAll();
}
