package com.tourguide.chain;

import com.tourguide.entities.RouteEntity;

public class LongRoute implements Chain {
	private Chain nextInChain;

	public void setNextChain(Chain nextChain) {
		nextInChain = nextChain;
	}

	@Override
	public String execute(RouteEntity route) {
		if (route.getDistance() >= 100 && route.getDistance() < 200) {
//			System.out.println("Minimum liters aan olie nodig: " + 20);
//			System.out.println("Voor deze afstand moet de chauffeur 2 liter coolant meenemen");
			return "Minimum liters aan olie nodig: " + 20
					+ ". Voor deze afstand moet de chauffeur 2 liter coolant meenemen.";
		} else {
			return "Met de administratie checken voor deze abnormale route.";
		}
	}
}
