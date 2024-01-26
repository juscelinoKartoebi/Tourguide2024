package com.tourguide.chain;

import com.tourguide.entities.RouteEntity;

public class MidRoute implements Chain {
	private Chain nextInChain;

	public void setNextChain(Chain nextChain) {
		nextInChain = nextChain;
	}

	@Override
	public String execute(RouteEntity route) {
		if (route.getDistance() >= 20 && route.getDistance() < 100) {
//			System.out.println("Minimum liters aan olie nodig: " + 10);
//			System.out.println("Voor deze afstand moet de chauffeur een liter coolant meenemen");
			return "Minimum liters aan olie nodig: " + 10
					+ ". Voor deze afstand moet de chauffeur een liter coolant meenemen.";
		} else {
			return nextInChain.execute(route);
		}
//		return "";
	}
}
