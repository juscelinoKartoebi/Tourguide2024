package com.tourguide.app;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Application;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class ApplicationConfig extends Application {
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("jersey.config.server.provider.packages", "sr.unasat.library.controller");
		return properties;
	}

}
