package com.tourguide.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourguide.entities.DestinationEntity;
import com.tourguide.services.DestinationService;

@Path("destination")
public class DestinationController {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourguide2");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private DestinationService service = new DestinationService(entityManager);

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DestinationEntity> findAll() {
		return service.getAll();
	}

	@Path("/insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public DestinationEntity insert(DestinationEntity destination) {
		service.insert(destination);
		return null;
	}
}
