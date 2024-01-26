package com.tourguide.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourguide.entities.TourEntity;
import com.tourguide.factorymethod.Notification;
import com.tourguide.factorymethod.NotificationFactory;
import com.tourguide.services.TourService;

@Path("tour")
public class TourController {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourguide2");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private TourService service = new TourService(entityManager);

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TourEntity> findAll() {
		return service.getAll();
	}

	@Path("/year/{year}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TourEntity> getByYear(@PathParam("year") Integer year) {
		System.out.println(year);
		return service.getByYear(year);
	}

	@Path("/insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TourEntity insert(TourEntity tour) {
		service.insert(tour);
		return null;
	}

	@Path("/delete/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") Long id) {
		TourEntity tour = (TourEntity) service.getById(id);
		System.out.println("here1");
		if (tour != null) {
			System.out.println("here");
			service.delete(tour);

		}
	}

	@Path("/factorymethod/{type}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String factoryMethod(@PathParam("type") String type) {
		NotificationFactory notificationFactory = new NotificationFactory();
		Notification notification = notificationFactory.createNotification(type);
		return notification.notifyUser();
	}

}
