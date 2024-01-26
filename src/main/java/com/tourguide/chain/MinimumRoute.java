package com.tourguide.chain;

import com.tourguide.entities.RouteEntity;

public class MinimumRoute implements Chain {

	private Chain nextInChain;

	public void setNextChain(Chain nextChain) {
		nextInChain = nextChain;
	}

	@Override
	public String execute(RouteEntity route) {
		if (route.getDistance() < 20) {
//			System.out.println("Liters aan olie nodig: " + 2);
			return "Liters aan olie nodig: " + 2;
		} else {
			return nextInChain.execute(route);
		}
//		return "";
	}

}
