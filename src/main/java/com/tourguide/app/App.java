package com.tourguide.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tourguide.chain.Chain;
import com.tourguide.chain.LongRoute;
import com.tourguide.chain.MidRoute;
import com.tourguide.chain.MinimumRoute;
import com.tourguide.controller.TourController;
import com.tourguide.driveradapter.DriverAdapter;
import com.tourguide.entities.BusDistanceEntity;
import com.tourguide.entities.BusEntity;
import com.tourguide.entities.DestinationEntity;
import com.tourguide.entities.DriverEntity;
import com.tourguide.entities.RouteEntity;
import com.tourguide.entities.TourEntity;
import com.tourguide.factorymethod.Notification;
import com.tourguide.factorymethod.NotificationFactory;
import com.tourguide.services.BusDistanceService;
import com.tourguide.services.BusService;
import com.tourguide.services.DestinationService;
import com.tourguide.services.DriverService;
import com.tourguide.services.RouteService;
import com.tourguide.services.TourService;

public class App {

	public static void main(String[] args) {
		try {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tourguide2");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
//			getTotalDistance(entityManager);
//			importExcel(entityManager);
//			persistDestination(entityManager);
//			deleteDriver(entityManager);
//			chain();
//			insertDestination(entityManager);
//			factorymethod();
//			getToursBy(entityManager);
//			getDrivers(entityManager);
			insertBus(entityManager);
//			getRoutesOverzicht(entityManager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getRoutesOverzicht(EntityManager entityManager) {
		RouteService service = new RouteService(entityManager);

		String a = service.runChain(1L);
		System.out.println(a);
//		RouteEntity r = (RouteEntity) service.getById(1L);
//		if(r != null) {
//			System.out.println(1);
//		}else {
//			System.out.println(2);
//		}
//		
//		List<RouteEntity> routes = service.getAllRoutes();
//		if (!routes.isEmpty()) {
//			for (RouteEntity route : routes) {
//				System.out.println(route.getFrom().getDestination() + " -> " + route.getTo().getDestination() + " "
//						+ route.getDistance() + "km");
//			}
//		}
	}

	private static void insertBus(EntityManager entityManager) {

		BusService service = new BusService(entityManager);

		BusEntity b1 = new BusEntity();
		b1.setLicensePlate("22-83 AB");
		service.insert(b1);

		BusEntity b2 = new BusEntity();
		b2.setLicensePlate("04-51 AB");
		service.insert(b2);

		BusEntity b3 = new BusEntity();
		b3.setLicensePlate("91-62 AB");
		service.insert(b3);

	}

	public static void insertDestination(EntityManager entityManager) {
		DestinationService destinationService = new DestinationService(entityManager);

		List<String> destinations = Arrays.asList("Paramaribo", "Cayenne", "Nickerie", "Brokopondo", "Sipaliwini",
				"Georgetown");
		for (String destination : destinations) {
			DestinationEntity destinationEntity = new DestinationEntity();
			destinationEntity.setDestination(destination);
			destinationService.insert(destinationEntity);
		}

		System.out.println();
		List<DestinationEntity> destinationList = destinationService.getAll();
		for (DestinationEntity destination : destinationList) {
			System.out.println(destination.getDestination());
		}
	}

	public static void chain() {

//		Chain chain1 = new MinimumRoute();
//		Chain chain2 = new MidRoute();
//		Chain chain3 = new LongRoute();
//
//		RouteEntity route = new RouteEntity();
//		route.setDistance(20L);
//
//		chain1.setNextChain(chain2);
//		chain2.setNextChain(chain3);
//
//		chain1.execute(route);

	}

	public static void factorymethod() {
		NotificationFactory notificationFactory = new NotificationFactory();
		Notification notification = notificationFactory.createNotification("EMAIL");
		notification.notifyUser();
	}

	public static void getTotalDistance(EntityManager entityManager) {

		BusDistanceService busDistanceService = new BusDistanceService(entityManager);
		List<BusDistanceEntity> list = busDistanceService.getAll();
		if (!list.isEmpty()) {
			for (BusDistanceEntity b : list) {
				System.out.println(b.getBus().getLicensePlate() + " - " + b.getTotalDistance() + "km");
			}
		}
	}

	public static void importExcel(EntityManager entityManager) {
		DriverAdapter driverAdapter = new DriverAdapter();
		DriverService driverService = new DriverService(entityManager);
		List<DriverEntity> driversList = driverAdapter.readExcel("C:\\Users\\znijm\\Documents\\Docs\\Book2.xlsx");
		for (DriverEntity driver : driversList) {
			driverService.insert(driver);
		}
	}

	public static void persistDestination(EntityManager entityManager) {
		DestinationService destinationService = new DestinationService(entityManager);
		DestinationEntity destination = new DestinationEntity();
		// update
		destination.setId(1L);
		destination.setDestination("Paramaribo");
		destinationService.update(destination);
	}

	public static void deleteDriver(EntityManager entityManager) {
		DriverService driverService = new DriverService(entityManager);
		DriverEntity driver = (DriverEntity) driverService.getById(12L);
		if (driver != null) {
			driverService.delete(driver);
		}
	}

	public static void getToursBy(EntityManager entityManager) {

		TourService service = new TourService(entityManager);
		List<TourEntity> tours = new ArrayList<>();

		// tours by date
//		tours = service.getTourByDate(Date.valueOf(LocalDate.now()));

		// tours by driver
//		tours = service.getTourByDriver("");

		// tours by tour name
//		tours = service.getTourByName("");

//		tours = service.getByYear(2022);
		TourController t = new TourController();
		tours = t.getByYear(2022);
		if (!tours.isEmpty()) {
			for (TourEntity tour : tours) {
				System.out.println(tour/* .getTourName() */);
			}
		}

	}

	public static void getDrivers(EntityManager entityManager) {
		DriverService driverService = new DriverService(entityManager);
		List<DriverEntity> drivers = driverService.getAll();
		if (!drivers.isEmpty()) {
			for (DriverEntity driver : drivers) {
				System.out.println(driver.getName() + driver.getDob());
			}
		}
	}

	public static void insertRoute(EntityManager e) {
//		RouteService service = new RouteService();
//		service.insert();
	}

}
