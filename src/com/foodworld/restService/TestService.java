package com.foodworld.restService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestService {
	

	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String addToBasket() {
		return "test";
	}
}
