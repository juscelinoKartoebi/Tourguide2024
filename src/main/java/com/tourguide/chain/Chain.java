package com.tourguide.chain;

import com.tourguide.entities.RouteEntity;

public interface Chain {
	public void setNextChain(Chain nextChain);

	public String execute(RouteEntity route);
}
