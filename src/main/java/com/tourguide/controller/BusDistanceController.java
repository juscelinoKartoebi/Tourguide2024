package com.tourguide.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourguide.entities.BusDistanceEntity;
import com.tourguide.services.BusDistanceService;

@Path("busdistance")
public class BusDistanceController {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourguide2");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private BusDistanceService service = new BusDistanceService(entityManager);

	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BusDistanceEntity> findAll() {
		return service.getAll();
	}
}
