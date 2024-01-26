package com.tourguide.controller;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourguide.driveradapter.DriverAdapter;
import com.tourguide.entities.DriverEntity;
import com.tourguide.services.DriverService;

@Path("driver")
public class DriverController {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourguide2");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private DriverService driverService = new DriverService(entityManager);
	private DriverAdapter driverAdapter = new DriverAdapter();

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DriverEntity> findAll() {
		return driverService.getAll();
	}

	@Path("/insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public DriverEntity insert(DriverEntity driver) {
		driverService.insert(driver);
		return null;
	}

	@Path("/file")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void handleFile(File file) {
		System.out.println(file.getPath());
		List<DriverEntity> list = driverAdapter.readExcel(file.getPath());
		for (DriverEntity driver : list) {
			driverService.insert(driver);
		}

	}

}
