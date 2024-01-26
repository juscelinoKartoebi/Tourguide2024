package com.tourguide.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tourguide.chain.Chain;
import com.tourguide.chain.LongRoute;
import com.tourguide.chain.MidRoute;
import com.tourguide.chain.MinimumRoute;
import com.tourguide.entities.RouteEntity;
import com.tourguide.services.RouteService;

@Path("route")
public class RouteController {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourguide2");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();
	private RouteService service = new RouteService(entityManager);

	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<RouteEntity> findAll() {
		return service.getAllRoutes();
	}

	@Path("/insert")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public RouteEntity insert(RouteEntity route) {
		service.insert(route);
		return null;
	}

	@Path("/update")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void update(RouteEntity route) {
		service.update(route);
	}

	@Path("/chain/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String runChain(@PathParam("id") Long id) {
		return service.runChain(id);
	}

	@Path("/getById/{routeId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RouteEntity getById(@PathParam("routeId") Long routeId) {
		RouteEntity route = (RouteEntity) service.getById(routeId);
		if (route != null) {
			System.out.println("route not null in contr");
		}
		System.out.println("Looking for id: " + routeId);
		return route;
	}
}
