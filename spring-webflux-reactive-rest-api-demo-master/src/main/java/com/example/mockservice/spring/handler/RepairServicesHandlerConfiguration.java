package com.example.mockservice.spring.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.mockservice.handler.RepairServicesHandler;

import javax.inject.Inject;
import javax.ws.rs.*;

@Configuration
public class RepairServicesHandlerConfiguration {
	
	@Inject
	private RepairServicesHandler repairServicesHandler;
	
	public static final MediaType APPLICATION_JSON = new MediaType("application", "json");
	
	@Path("/repairs")
	@GET
	@Bean
	public RouterFunction<ServerResponse> getRepairs() {
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.GET("/repairs").and(RequestPredicates.accept(APPLICATION_JSON)), repairServicesHandler::getAllRepairs);
		return route;
	}
	
	@Path("/repairs")
	@POST
	@Bean
	public RouterFunction<ServerResponse> createRepair() {
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.POST("/repairs").and(RequestPredicates.accept(APPLICATION_JSON)), repairServicesHandler::createRepair);
		return route;
	}
	
	@Path("/repairs/{id}")
	@PUT
	@Bean
	public RouterFunction<ServerResponse> updateRepair() {
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.PUT("/repairs/{id}").and(RequestPredicates.accept(APPLICATION_JSON)), repairServicesHandler::updateRepair);
		return route;
	}
	
	@Path("/repairs/{id}")
	@DELETE
	@Bean
	public RouterFunction<ServerResponse> deleteRepair() {
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.DELETE("/repairs/{id}").and(RequestPredicates.accept(APPLICATION_JSON)), repairServicesHandler::deleteRepair);
		return route;
	}
	
	@Path("/repairs/{statusCode}")
	@GET
	@Bean
	public RouterFunction<ServerResponse> getRepairsByStatusCode() {
		RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.GET("/repairs/{statusCode}").and(RequestPredicates.accept(APPLICATION_JSON)), repairServicesHandler::getRepairsByStatusCode);
		return route;
	}
}
