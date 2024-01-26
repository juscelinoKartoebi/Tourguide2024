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

import com.tourguide.entities.BusEntity;
import com.tourguide.services.BusService;

@Path("bus")
public class BusController {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourguide2");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private BusService service = new BusService(entityManager);

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BusEntity> findAll() {
		return service.getAll();
	}

	@Path("/insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public BusEntity insert(BusEntity bus) {
		service.insert(bus);
		return null;
	}

}
